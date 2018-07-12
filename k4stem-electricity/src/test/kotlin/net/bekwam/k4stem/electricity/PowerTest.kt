package net.bekwam.k4stem.electricity

import com.sun.org.apache.xalan.internal.lib.ExsltMath.power
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

/**
 * Class for testing Ohms functions
 *
 * @author Rob
 * @since 1.0
 */
class PowerTest {

    @Test
    fun calcPower() {
        //5.0 amps * 10V = 50 Watts
        assertEquals(50.0, power(5.0,10.0))
    }

    @Test
    fun calcPowerFromResistance(){
        assertEquals(100.0, Power().powerFromResistance(10.0, 1.0))
    }

    @Test
    fun negativeResistanceError() {
        assertThrows( IllegalArgumentException::class.java, { Power().powerFromResistance( 0.005, -1000.0 ) })
    }

}