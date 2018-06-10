package net.bekwam.k4stem.warmups

import kotlin.math.sqrt

class RCFilterCircuit {

    val order : String
    val cutoffFrequency : Double

    constructor( r : Double, c : Double ) {
        order = "First"
        cutoffFrequency = 1 / (2 * Math.PI * r * c )
    }

    constructor( r1 : Double, c1 : Double, r2 : Double, c2 : Double ) {
        order = "Second"
        cutoffFrequency = 1 / (2 * Math.PI * sqrt( r1 * c1 * r2 * c2 ) )
    }
}

fun main(args : Array<String>) {

    val circuit = RCFilterCircuit( 10_000.0, 0.000000047 )

    println("${circuit.order} Order Circuit fc=${circuit.cutoffFrequency}")

    val circuit1 = RCFilterCircuit( 10_000.0, 0.000000047, 10_000.0, 0.0000000047 )

    println("${circuit1.order} Order Circuit fc=${circuit1.cutoffFrequency}")
}