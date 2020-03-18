package nl.janvanrosmalen.batshit

import nl.janvanrosmalen.human.natural.NaturalComparable

interface Part{
    val final : Boolean
    val partNumber : Long
    val resource : Long
    val patch : Long
    val data : List<Byte>
}