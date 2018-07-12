package net.bekwam.k4stem.electricity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.text.DecimalFormat
import kotlin.math.roundToInt

/**
 * Class for testing the LowPassFilter class
 *
 * @author carl
 * @since 1.0
 */
class LowPassFilterTest {

    @Test
    fun basicComp() {

        // r=10k, c=0.47uF series RC circuit
        val lpf = LowPassFilter(10_000.0, 0.000000047 )

        // sine wave w. vin=1.768Vrms, 1kHz
        val retval = lpf.voltage_output( 1.768, 1_000.0 )

        // xc = 3.386kOhms
        assertEquals( 3386, retval.first.roundToInt() )

        val df = DecimalFormat("#.######")

        // i = 167uA
        assertEquals( "0.000167", df.format(retval.second) )

        // vout = 567mV
        assertEquals( "0.567063", df.format(retval.third) )
    }

    @Test
    fun secondComp() {

        val lpf = LowPassFilter(10_000.0, 0.000000047 )

        val retval = lpf.voltage_output( 1.768, 10_000.0 ) // increased f, more reactance, smaller vout

        val df = DecimalFormat("#.######")
        assertEquals( "0.059835", df.format(retval.third) )
    }
}
