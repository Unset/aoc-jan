package nl.janvanrosmalen.batshit

import xpair.Trio
import xpair.foldMap
import xpair.swap

object BatShit {

    fun readPart(data : List<Byte>) : Part?{
        return readPart(Cursor.create(data) ?: return null)
    }


    fun readThreeNumbers(nonZero : Trio<Boolean>, cursor: ReadCursor<Byte>?) : Pair<ReadCursor<Byte>?, Trio<Long>>? {
        return nonZero.foldMap(cursor) {
            acc, b -> readCompactNumber(b, acc)?.swap()
        }

    }

    fun readPart(cursor: ReadCursor<Byte>) : Part?{
        val (headerLong, postHeader) = readVlq(cursor) ?: return null
        val header = parseHeader(headerLong)


        val (paddingCursor, newNumbers) = readThreeNumbers(Triple(header.resource, header.patch, header.part), postHeader) ?: return null
        val (resource, patch, part) = newNumbers

        val (paddingSize, _) = readCompactNumber(header.padding, paddingCursor) ?: return null

        val (dataCursor, actualSkipped) = paddingCursor.skip(paddingSize)

        if (actualSkipped != paddingSize) return null

        return object : Part {
            override val final: Boolean
                get() = header.final
            override val partNumber: Long
                get() = part
            override val resource
                get() = resource
            override val patch: Long
                get() = patch
            override val data
                get() = dataCursor?.export().orEmpty()

        }


    }

    fun readCompactNumber(nonZero : Boolean, cursor: ReadCursor<Byte>?) : Pair<Long, ReadCursor<Byte>?>?{
        return if (nonZero){
            (readVlq(cursor) ?: return null).takeIf {it.first != 0L} ?: return null
        } else {
            Pair(0, cursor)
        }
    }


    fun readVlq(cursor : ReadCursor<Byte>?) : Pair<Long, ReadCursor<Byte>?>? {
        val (head, tail) = (cursor ?: return null).takePositiveInt()
        return when (head) {
            0x80 -> null
            in 0x00..0x7F -> Pair(head.toLong(), tail)
            else -> readFollowUp(tail ?: return null, head.toLong())
        }

    }

    tailrec fun readFollowUp(cursor : ReadCursor<Byte>, buffer : Long) : Pair<Long, ReadCursor<Byte>?>? {
        val (head, tail) = cursor.takePositiveInt()
        return if (head >= 0x80) {
            Pair((buffer shl 7) + head - 0x80, tail)
        } else {
            readFollowUp(tail ?: return null, (buffer shl 7) + head)
        }
    }

    val PART = 0x01L
    val RESOURCE = 0x08L
    val PATCH = 0x10L
    val FINAL = 0x20L
    val PADDING = 0x40L

    fun parseHeader(number: Long): BatHeader = object : BatHeader {
        override val part: Boolean = (number and PART) != 0L
        override val specialResource: Boolean = (number and 0x04) != 0L
        override val resource: Boolean = (number and RESOURCE) != 0L
        override val patch: Boolean = (number and PATCH) != 0L
        override val final: Boolean = (number and FINAL) != 0L
        override val padding: Boolean = (number and PADDING) != 0L
    }

    fun writeSinglePart(resource : Long, patch : Long, payload : List<Byte>, target: Int = 8) : List<Byte>?{

        val numbers = createCompactVlq(resource) + createCompactVlq(patch)

        val paddingSize = target - 1L - numbers.size - payload.size

        if (paddingSize < 0 ) return null

        val header = FINAL + compact(resource, RESOURCE) + compact(patch, PATCH) + compact(paddingSize, PADDING)

        val paddingVlq = createCompactVlq(paddingSize)

        val zeroes = Array<Byte>(paddingSize.toInt() - paddingVlq.size) {0}

        return listOf(header.toByte()) + numbers + paddingVlq + zeroes + payload



    }

    fun compact(number: Long, flag : Long) : Long {
        return if (number!=0L) flag else 0L
    }


    fun createCompactVlq(number : Long) : List<Byte>{
        return if (number == 0L)  emptyList() else createVlq(number)
    }

    fun createVlq(number : Long) : List<Byte> {
        if (number < 0x80){
            return listOf(number.toByte())
        } else {
            throw Exception()
        }

    }
}

fun ByteArray.toHexString() = joinToString("") { "%02x".format(it) }

fun main(args : Array<String>) {
    val message = "A"

    val encoded = BatShit.writeSinglePart(3,5,message.toByteArray().toList())

    println(encoded?.toByteArray()?.toHexString() ?: "Encoding failed!")

    val res = BatShit.readPart(encoded!!)

    print("${res?.resource}/${res?.patch}#${res?.partNumber}=${res?.data?.toByteArray()?.toString(charset = Charsets.UTF_8)}")






}