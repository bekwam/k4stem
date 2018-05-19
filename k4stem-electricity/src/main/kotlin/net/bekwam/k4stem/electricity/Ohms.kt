package net.bekwam.k4stem.electricity

/**
 * A file of functions based on Ohm's Law
 *
 * @author carl
 */

fun voltage(i : Double, r : Double) : Double {

    if( r < 0.0 )
        throw IllegalArgumentException("resistance must be positive")

    return i * r
}

fun current(v : Double, r : Double) : Double {

    if( r < 0.0 )
        throw IllegalArgumentException("resistance must be positive")

    if( r == 0.0 )
        return Double.POSITIVE_INFINITY // infinite current

    return v / r
}

fun resistance( v : Double, i : Double ) : Double {

    if (i == 0.0)
        return Double.POSITIVE_INFINITY // infinite resistance

    return v / i
}