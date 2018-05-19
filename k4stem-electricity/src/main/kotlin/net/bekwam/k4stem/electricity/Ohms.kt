package net.bekwam.k4stem.electricity

/**
 * A file of functions based on Ohm's Law
 *
 * @author carl
 * @since 1.0
 */

/**
 * V = IR
 *
 * @param i - current
 * @param r - resistance; must be >=0.0
 * @throws IllegalArgumentException if r <0.0
 */
fun voltage(i : Double, r : Double) : Double {

    if( r < 0.0 )
        throw IllegalArgumentException("resistance must be positive")

    return i * r
}

/**
 * I = V/R
 *
 * Will return infinite current (POSITIVE_INFINITY constant) if
 * no resistance (r=0.0)
 *
 * @param v - voltage
 * @param r - resistance; must be >=0.0
 * @throws IllegalArgumentException if r <0.0
 */
fun current(v : Double, r : Double) : Double {

    if( r < 0.0 )
        throw IllegalArgumentException("resistance must be positive")

    if( r == 0.0 )
        return Double.POSITIVE_INFINITY // infinite current

    return v / r
}

/**
 * R = V/I
 *
 * Will return infinite resistance (POSITIVE_INFINITY constant) if
 * no current (i=0.0)
 *
 * @param v - voltage
 * @param i - current
 */
fun resistance( v : Double, i : Double ) : Double {

    if (i == 0.0)
        return Double.POSITIVE_INFINITY // infinite resistance

    return v / i
}