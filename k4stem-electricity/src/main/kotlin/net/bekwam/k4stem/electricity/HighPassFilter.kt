package net.bekwam.k4stem.electricity

import java.lang.Math.pow
import kotlin.math.sqrt

/**
 * Class to model first-order Low High Filters
 *
 * The High Pass Filter weakens lower frequency signals.
 *
 * A High Pass Filter is a RC circuit with a resistor in series with a capacitor.  The
 * capacitor resists the current as determined by frequency.  The circuit is a voltage
 * divider and the Vout is the voltage across the resistor.
 *
 * Note that the positional arguments in the constructor differ from those in the
 * LowPassFilter
 *
 * @author carl
 * @since 1.0
 */
class HighPassFilter(val c : Double, val r : Double) {

    /**
     * vin should be in the desired units for vout (rms, pp, pk)
     */
    fun vout(vin : Double, f : Double) : Triple<Double, Double, Double> {

        // xc is the reactance
        val xc = 1.0 / (2.0 * Math.PI * f * c)

        // z is the impedance
        val z = sqrt( pow(r, 2.0) + pow(xc, 2.0) )

        // i is the current
        val i = vin / z

        return Triple(xc, i, i * r)
    }
}




