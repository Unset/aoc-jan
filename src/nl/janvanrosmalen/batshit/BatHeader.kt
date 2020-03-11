package nl.janvanrosmalen.batshit

interface BatHeader {
    val part : Int
    val specialResource : Boolean
    val resourceNumber : Boolean
    val patch : Boolean
    val final : Boolean
    val padding : Boolean
}