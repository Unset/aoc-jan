package nl.janvanrosmalen.batshit

interface BatHeader {
    val part : Boolean
    val specialResource : Boolean
    val resource : Boolean
    val patch : Boolean
    val final : Boolean
    val padding : Boolean
}