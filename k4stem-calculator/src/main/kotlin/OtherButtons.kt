import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*
import kotlin.math.pow

class OtherButtonFunctions : Controller(){
    val vc  : ViewController by inject()
    val Op : Operations by inject()

    val MDNList = SimpleObjectProperty<MutableList<Double>>()
    val decimal = SimpleDoubleProperty(0.0)
    val MDN = SimpleDoubleProperty(0.0)

    fun createDecimal(){
        decimal.value = vc.currInput.value
        for(n in 0..MDNList.value.size-1){
            decimal.value += MDNList.value[n] * 10.0.pow(-(n+1))
        }
        vc.currInput.value = decimal.value
        MDNList.value.clear()
    }

    fun createMultiDigitNum(){
        val size = MDNList.value.size
        MDN.value = 0.0
        for(n in 0..MDNList.value.size-1){
            MDN.value += MDNList.value[n] * 10.0.pow(size - (n+1))
        }
        vc.currInput.value = MDN.value
        MDNList.value.clear()
    }

    fun clear(){
        MDNList.value.clear()
        MDN.value = 0.0
        decimal.value = 0.0
        vc.currInput.value = null
        vc.inputA.value = null
        vc.dispVal.value = null
        vc.inputB.value = null
    }

    fun equals(operation : String) : Double{
        var out  = 0.0
        if(operation != ""){
            if (operation == "+"){
                out = Op.add(vc.inputA.value, vc.inputB.value)
            }
            else if (operation == "-"){
                out = Op.subtract(vc.inputA.value, vc.inputB.value)
            }
            else if (operation == "/"){
                out = Op.divide(vc.inputA.value, vc.inputB.value)
            }
            else if (operation == "*"){
                out = Op.multiply(vc.inputA.value, vc.inputB.value)
            }
        }
        return out
    }
    fun numButton(a: Double){
        if(vc.flag.value == false){
            vc.flag.value = true
            MDNList.value = mutableListOf(a)
        }
        else{
            MDNList.value.add(a)
        }
        if(vc.decFlag.value){
            val size = MDNList.value.size
            MDN.value = 0.0
            for(n in 0..MDNList.value.size-1){
                MDN.value += MDNList.value[n] * 10.0.pow(size - (n+1))
            }
            vc.currInput.value = MDN.value
        }else{
            decimal.value = vc.currInput.value
            for(n in 0..MDNList.value.size-1){
                decimal.value += MDNList.value[n]* 10.0.pow(-(n+1))
            }
            vc.currInput.value = decimal.value
        }
        println(vc.currInput)
    }

    fun opButton(a  :String) : String{
        if(vc.decFlag.value) {
            createMultiDigitNum()
        }
        else if(vc.decFlag.value == false){
            createDecimal()
            vc.decFlag.value = true
        }

        return a
    }
}