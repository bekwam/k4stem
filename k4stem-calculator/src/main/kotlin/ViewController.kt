import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class ViewController : Controller(){
    val currInput = SimpleDoubleProperty()
    val inputA = SimpleDoubleProperty()
    val inputB = SimpleDoubleProperty()
    val dispVal = SimpleStringProperty()
    val flag = SimpleBooleanProperty(false)
    val decFlag = SimpleBooleanProperty(true)

}