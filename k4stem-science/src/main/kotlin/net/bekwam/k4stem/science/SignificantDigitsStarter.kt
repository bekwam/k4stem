package net.bekwam.k4stem.science

import java.math.BigDecimal
import java.math.RoundingMode

const val DEFAULT_SIG_DIGITS = 3

fun add( num1 : Double, num2 : Double, sigDigits : Int = DEFAULT_SIG_DIGITS ) : Double {
    val bd1 = BigDecimal(num1)
    val bd2 = BigDecimal(num2)
    return bd1
            .add(bd2)
            .setScale(sigDigits, RoundingMode.HALF_UP)
            .toDouble()
}