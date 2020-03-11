package nl.janvanrosmalen.batshit

import nl.janvanrosmalen.human.natural.NaturalComparable


class ByteScanner(val bytes: ByteArray, var pos: Int = 0) {


    fun parsePart(): Part? {
        val header = parseHeader() ?: return null
        if (header.specialResource) return null
        val resource = readCompactedVlq(header.resourceNumber) ?: return null
        val patch = readCompactedVlq(header.patch) ?: return null
        val part = if (header.part == 3) {
            readVlq()?.takeIf { it >= 3 } ?: return null
        }
        else {
            header.part.toLong()
        }

        if (header.padding){readPadding() ?: return null}

        return object : Part {
            override val final = header.final
            override val resource = NaturalIdentifier(resource)
            override val patch = patch
            override val partNumber = part
            override val data = readTheRest()
        }
    }

    fun readPadding() : Unit? {
        val paddingBegin = pos
        val paddingSize = readVlq()?.takeIf {it > 0 && it <= (Int.MAX_VALUE - paddingBegin)}?.toInt() ?: return null

        while(pos < paddingBegin + paddingSize){
            if (readPositive() != 0) return null
        }
        return Unit
    }

    fun readTheRest() : ByteArray {
        return bytes.drop(pos).toByteArray()
    }


    fun readCompactedVlq(extended : Boolean) : Long? = if (extended){
        readVlq()?.takeIf{it > 0}
    } else 0L

    fun readPositive(): Int? {
        return (bytes.getOrNull(pos) ?: return null).toPositiveInt().also { pos += 1 }
    }

    fun readVlq(): Long? {
        val first = readPositive() ?: return null
        when (first) {
            0x80 -> return null
            in 0x00..0x7F -> return first.toLong()
            else -> {
                var buffer = first - 0x80L
                repeat(8) {
                    val follow = readPositive() ?: return null
                    if (follow < 0x80) {
                        return (buffer shl 7) + follow
                    } else {
                        buffer = (buffer shl 7) + (follow - 0x80)
                    }
                }
                return null
            }
        }

    }
    fun parseHeader() : BatHeader?{
        return parseHeader(readVlq() ?: return null)
    }

    companion object {
        fun parseHeader(number: Long): BatHeader = object : BatHeader {
            override val part: Int = (number and 0x03).toInt()
            override val specialResource: Boolean = (number and 0x04) != 0L
            override val resourceNumber: Boolean = (number and 0x08) != 0L
            override val patch: Boolean = (number and 0x10) != 0L
            override val final: Boolean = (number and 0x20) != 0L
            override val padding: Boolean = (number and 0x40) != 0L
        }


    }

}
