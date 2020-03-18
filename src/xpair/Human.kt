package xpair

//interface Human {
//
//    val human : String get() = toString()
//
//}
//
//interface HumanBuilding{
//
//    fun buildHuman(builder : StringBuilder) : StringBuilder
//
//}
//
//
//fun Any?.getHuman() : String = when(this){
//        null -> ""
//        is Human -> human
//        is HumanBuilding -> buildHuman(StringBuilder()).toString()
//        is Iterable<*> -> StringBuilder().also{this.forEach {item -> it.append(item)}}.toString()
//        else -> toString()
//}
//
//fun StringBuilder.appendHuman(it : Any?) : StringBuilder = when(it){
//    null -> this
//    is Human -> append(it.human)
//    is HumanBuilding -> it.buildHuman(this)
//    else -> append(it)
//}
