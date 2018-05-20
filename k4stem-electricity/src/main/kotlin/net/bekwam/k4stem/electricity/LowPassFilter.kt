package net.bekwam.k4stem.electricity

import java.lang.Math.pow
import kotlin.math.sqrt

/**
 * Class to model first-order Low Pass Filters
 *
 * The Low Pass Filter weakens higher frequency signals.
 *
 * A Low Pass Filter is a RC circuit with a resistor in series with a capacitor.  The
 * capacitor resists the current as determined by frequency.  The circuit is a voltage
 * divider and the Vout is the voltage across the capacitor.
 *
 * @author carl
 * @since 1.0
 */
class LowPassFilter(val r : Double, val c : Double) {

    /**
     * vin should be in the desired units for vout (rms, pp, pk)
     */
    fun vout(vin : Double, f : Double) : Triple<Double, Double, Double> {

        // xc is the reactance
        val xc = 1.0 / (2.0 * Math.PI * f * c)

        // z is the impedance
        val z = sqrt( pow(r, 2.0) + pow(c, 2.0) )

        // i is the current
        val i = vin / z

        return Triple(xc, i, i * xc)
    }
}


