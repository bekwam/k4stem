package net.bekwam.k4stem.science

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SignificantDigitsStarterTest {

    @Test
    fun defaultAdd() {
        assertEquals( 3.001, add(3.0, 0.0011) )
    }

    @Test
    fun basicAdd() {
        assertEquals( 3.0011, add(3.0, 0.0011, 4) )
    }
}