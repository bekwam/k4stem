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
 * divider and the voltage_output is the voltage across the capacitor.
 *
 * Note that the positional arguments in the constructor differ from those in the
 * HighPassFilter
 *
 * @author carl
 * @since 1.0
 */
class LowPassFilter(val r : Double, val c : Double) {

    /**
     * voltage_input should be in the desired units for voltage_output (rms, pp, pk)
     */
    fun voltage_output(voltage_input : Double, f : Double) : Triple<Double, Double, Double> {

        val reactance = 1.0 / (2.0 * Math.PI * f * c)

        val impedance = sqrt( pow(r, 2.0) + pow(reactance, 2.0) )

        val current = voltage_input / impedance

        return Triple(reactance, current, current * reactance)
    }
}


