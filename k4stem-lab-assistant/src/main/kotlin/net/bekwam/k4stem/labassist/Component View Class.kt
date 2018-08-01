package net.bekwam.k4stem.labassist

import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.control.Alert
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.util.StringConverter
import tornadofx.*
import java.math.BigDecimal


class ComponentView : View() {
    val ctrl : ComponentViewController by inject()

    override val root = vbox {
        form {
            fieldset("Add Component Form:") {
                hbox {
                    field("Name:") { textfield(ctrl.nameInput) }
                    padding = insets(10)
                    spacing = 10.0
                }
                hbox {
                    field("Description:") { textfield(ctrl.descInput)  }
                    padding = insets(10)
                    spacing = 10.0
                }
                hbox {
                    field("Source:") { textfield(ctrl.sourceInput) }
                    padding = insets(10)
                    spacing = 10.0
                }
                hbox {
                    field("Component Type:") { combobox(property = ctrl.cpInput, values = observableList(ComponentType.RESISTOR, ComponentType.CAPACITOR, ComponentType.IC, ComponentType.POTENTIOMETER, ComponentType.INDUCTOR, ComponentType.UNSPECIFIED, ComponentType.TRANSISTOR))}
                    padding = insets(10)
                    spacing = 10.0
                }
                hbox {
                    field("Quantity:") {
                        textfield(ctrl.numInput) {
                            filterInput {
                                it.controlNewText.isInt()
                            }
                        }


                    }
                    padding = insets(10)
                    spacing = 10.0
                }
                hbox {
                    field("Price:") {
                        textfield(ctrl.editingPrice)
                        {
                            filterInput {
                                it.controlNewText.isDouble()
                            }
                        }
                    }
                    padding = insets(10)
                    spacing = 10.0
                }
                hbox {
                    field("Model Number:") { textfield(ctrl.mnInput)}
                    padding = insets(10)
                    spacing = 10.0
                }
                hbox {
                    field("Value:") { textfield(ctrl.valInput)
                    {
                        filterInput {
                            it.controlNewText.isDouble()
                        }
                    }
                    }
                    padding = insets(10)
                    spacing = 10.0
                }
                separator()
                hbox{
                    button("Save"){
                        action {
                            if (ctrl.nameInput.value != null && ctrl.descInput.value != null && ctrl.sourceInput.value != null && ctrl.cpInput.value != null && ctrl.mnInput.value != null) {
                                ctrl.newComp = (Component(
                                        ctrl.nameInput.value,
                                        ctrl.descInput.value,
                                        ctrl.sourceInput.value,
                                        ctrl.cpInput.value,
                                        ctrl.numInput.value,
                                        ctrl.editingPrice.value.toBigDecimal(),
                                        ctrl.mnInput.value,
                                        ctrl.valInput.value
                                )

                                        )
                                if (ctrl.compctrl.lab != null) {
                                    if (ctrl.itemFlag.value == false) {
                                        ctrl.compctrl.lab!!.value.inventory[0].components.add(ctrl.newComp!!)
                                        ctrl.resetInputs()
                                        modalStage!!.close()
                                    } else if (ctrl.itemFlag.value) {
                                        if (ctrl.indOfC != null) {
                                            ctrl.compctrl.lab!!.value.inventory[0].components[ctrl.indOfC!!] = ctrl.newComp!!
                                            ctrl.resetInputs()
                                            modalStage!!.close()
                                        }
                                    }
                                } else if (ctrl.compctrl.lab == null && AddLabView().newLab != null) {
                                    AddLabView().newLab!!.inventory[0].components.add(ctrl.newComp!!)
                                    ctrl.resetInputs()
                                    modalStage!!.close()
                                } else {
                                    alert(Alert.AlertType.INFORMATION, "Something happend")
                                }

                            }
                        }
                    }
                    button("Close"){
                        action{
                            ctrl.resetInputs()
                            modalStage!!.close()
                        }
                    }
                    padding = insets(20)
                    spacing =10.0
                }
                padding = insets(20)
            }
        }
        style{
            backgroundColor += Color.POWDERBLUE
            font = Font.font("Verdana")
            fontSize = 24.px
        }
        addClass(Styles.normalStyle)
    }

    override fun onDock() {
        println("${params["a"]}")
    }
}
//possibly add a buttonbar?