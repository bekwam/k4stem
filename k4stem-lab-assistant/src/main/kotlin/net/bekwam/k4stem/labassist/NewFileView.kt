package net.bekwam.k4stem.labassist
import javafx.beans.property.SimpleStringProperty
import java.time.ZonedDateTime
import tornadofx.*
class AddLabView : View(){
    val labNameInput = SimpleStringProperty()
    val ownerNameInput = SimpleStringProperty()
    var newLab : Lab? = null
    var tempInv : Inventory? = null
    val ctrl  : ComponentController by inject()
    override val root = form{
        fieldset("New Lab Form (Pt. 1 of 2):"){
            hbox{
               field("Lab Name:"){textfield(labNameInput)}
            }
            hbox{
                field("Lab Owner Name"){textfield(ownerNameInput)}
            }
            separator()
            hbox{
                button("Continue"){
                    action {
                        if (labNameInput.value != null && ownerNameInput.value != null) {
                            tempInv = Inventory(
                                    ZonedDateTime.parse("2018-06-03T10:15:30-07:00[America/Los_Angeles]"),
                                    mutableListOf<Component>()
                            )
                            newLab = Lab(
                                    version = 1,
                                    labName = labNameInput.value,
                                    labOwner = ownerNameInput.value,
                                    inventory = listOf(tempInv!!)
                            )
                            find<ComponentView>().openModal()
                            modalStage!!.close()
                            ctrl.lab = newLab
                        }
                    }
                }

            }
        }

    }
}