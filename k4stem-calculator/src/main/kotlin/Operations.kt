import tornadofx.*

class Operations : Controller(){
    val add = {a : Double, b : Double -> a+b}
    val subtract = {a : Double, b : Double -> a-b}
    val multiply = {a : Double, b : Double -> a*b}
    val divide = {a : Double, b : Double -> a/b}
}