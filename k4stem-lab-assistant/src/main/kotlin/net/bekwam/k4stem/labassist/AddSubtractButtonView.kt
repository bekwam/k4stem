package net.bekwam.k4stem.labassist

import javafx.geometry.Insets
import javafx.scene.control.Alert
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.*

class AddSubtractButtonView : Fragment(){
    val ctrl : ComponentController by inject()
    val CVctrl : ComponentViewController by inject()
    override val root =hbox{
        button("+"){
            action {
                if(ctrl.lab.value!=null) {
                    CVctrl.itemFlag.set(false)
                    CVctrl.resetInputs()
                    find<ComponentView>().openWindow()
                }
                else{
                    alert(Alert.AlertType.INFORMATION,"No Inventory Open")
                    }
                }
            style{
                backgroundColor = multi(Color.LIGHTGOLDENRODYELLOW)
            }
        }
        button("-"){
            action{
                if (ctrl.tblItems.selectedItem != null) {
                    val selectedItem = ctrl.tblItems.selectedItem!!
                    ctrl.delete(selectedItem)

                }
            }
            style{
                backgroundColor = multi(Color.LIGHTGOLDENRODYELLOW)
            }
        }
        spacing = 10.0
        padding = Insets(10.0)
    }
}