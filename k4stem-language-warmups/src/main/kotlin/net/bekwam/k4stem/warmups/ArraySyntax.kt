package net.bekwam.k4stem.warmups

@SuppressWarnings("unused")
fun main(args : Array<String>) {

    // array of Ints
    val a = arrayOf(1,2,3)

    val b = arrayOf(1,2,3)

    if( a != b ) {
        println("b and a are different objects")
    }

    if( a.contentEquals(b) ) {
        println("b and a have the same contents")
    }

    val c = arrayOf(3,2,1)

    if( !a.contentEquals(c) ) {
        println("c and a may have the same contents, but a different order")
    }

    // array of Ints, but intended for Java compatibility int[]
    val d = intArrayOf(1,2,3)

    // if( a.contentEquals(d) ) println("the same!") ; generates compiler error b/c Array<Int> != IntArray

}