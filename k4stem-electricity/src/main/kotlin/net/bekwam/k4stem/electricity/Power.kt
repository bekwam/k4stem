package net.bekwam.k4stem.electricity

/**
 * A file of functions based on calculating Power
 *
 * @author Rob
 * @since 1.0
 */

class Power {
    // (p = i * v) where i = current, v = voltage
    fun power(current : Double, voltage : Double) : Double {
        return current * voltage
    }

    // (P = (i)^2 *r) where i = current, r = resistance
    fun powerFromResistance(current :Double, resistance : Double) : Double {
        return current * power(current, resistance)
    }
}
