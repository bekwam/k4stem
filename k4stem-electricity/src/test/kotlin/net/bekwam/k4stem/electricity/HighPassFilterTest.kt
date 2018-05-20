package net.bekwam.k4stem.electricity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.text.DecimalFormat
import kotlin.math.roundToInt

/**
 * Class for testing the HighPassFilter class
 *
 * @author carl
 * @since 1.0
 */
class HighPassFilterTest {

    @Test
    fun basicComp() {

        // r= approx 10k, c=0.47uF series RC circuit
        val hpf = HighPassFilter(0.000000047, 9900.0 )

        // sine wave w. vin=1.768Vrms, 100Hz
        val retval = hpf.vout( 1.768, 100.0 )

        // xc = 33.86kOhms
        assertEquals( 33863, retval.first.roundToInt() )

        val df = DecimalFormat("#.######")

        // i = 50uA
        assertEquals( "0.00005", df.format(retval.second) )

        // vout = 496mV
        assertEquals( "0.496119", df.format(retval.third) )
    }
}
