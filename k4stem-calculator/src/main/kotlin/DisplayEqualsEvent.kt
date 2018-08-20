import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class DispVal(val disp : SimpleStringProperty) : FXEvent()
class TextFieldController : Controller(){
    init{
        val disp = SimpleStringProperty()
        subscribe<DispVal> {
            fire(DispVal(disp))
        }
    }
}