package net.bekwam.k4stem.electricity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

/**
 * Class for testing Ohms functions
 *
 * @author carl
 * @since 1.0
 */
class OhmsTest {

    @Test
    fun calcVoltage() {
        // 5mA * 1kOhm = 5V
        assertEquals( 5.0, Ohms().voltage(0.005, 1000.0 ) )
    }

    @Test
    fun negativeResistanceError() {
        assertThrows( IllegalArgumentException::class.java, { Ohms().voltage( 0.005, -1000.0 ) })
        assertThrows( IllegalArgumentException::class.java, { Ohms().current( 5.0, -1000.0 ) })
    }

    @Test
    fun calcCurrent() {
        // 5V / 1kOhm = 5mA
        assertEquals( 0.005, Ohms().current(5.0, 1000.0))
    }

    @Test
    fun infiniteCurrent() {
        assertEquals( Double.POSITIVE_INFINITY, Ohms().current(5.0, 0.0))
    }

    @Test
    fun calcResistance() {
        // 5V / 5mA = 1kOhm
        assertEquals( 1000.0, Ohms().resistance(5.0, 0.005))
    }

    @Test
    fun infiniteResistance() {
        assertEquals( Double.POSITIVE_INFINITY, Ohms().resistance(5.0, 0.0))
    }

}