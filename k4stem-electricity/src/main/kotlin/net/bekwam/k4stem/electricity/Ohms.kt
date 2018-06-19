package net.bekwam.k4stem.electricity

/**
 * A file of functions based on Ohm's Law
 *
 * @author carl
 * @since 1.0
 */


class Ohms {

    /**
     * V = IR where resistance must be >= 0.0
     */
    @Throws(IllegalArgumentException::class)
    fun voltage(current : Double, resistance : Double) : Double{

        if (resistance < 0.0)
            throw IllegalArgumentException("resistance must be positive")

        return current * resistance
    }

    /**
     * I = V/R where resistance must be >= 0.0
     *
     * Will return infinite current (POSITIVE_INFINITY constant) if
     * no resistance (r = 0.0)
     */
    @Throws(IllegalArgumentException::class)
    fun current(voltage : Double, resistance : Double) : Double {

        if (resistance < 0.0)
            throw IllegalArgumentException("resistance must be positive")

        return if (resistance == 0.0) Double.POSITIVE_INFINITY else (voltage / resistance)
    }

    /**
     * R = V/I
     *
     * Will return infinite resistance (POSITIVE_INFINITY constant) if
     * no current (i = 0.0)
     */
    fun resistance(voltage : Double, current : Double ) : Double {

        return if (current == 0.0) Double.POSITIVE_INFINITY else (voltage / current)

    }

}
