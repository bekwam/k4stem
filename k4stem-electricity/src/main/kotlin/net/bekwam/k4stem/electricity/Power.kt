package net.bekwam.k4stem.electricity

/**
 * A file of functions based on calculating Power
 *
 * @author Rob
 * @since 1.0
 */

/**
 * p = i * v
 * @ param i - current
 * @ param v - voltage
 */
fun power(i : Double, v : Double) : Double {
    return i * v
}

/**
 * P = (i)^2 *r
 * @ param i - current
 * @ param r - resistance
 */
fun powerFromResistance(i :Double, r : Double) : Double {
    return i * voltage(i, r)
}
