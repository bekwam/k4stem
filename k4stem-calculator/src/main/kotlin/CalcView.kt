import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.layout.Priority
import tornadofx.*
import kotlin.math.pow


//can not do num + num + num
//decimal is still broken
//
class MyView  : View() {
    val vc: ViewController by inject()
    val OBF: OtherButtonFunctions by inject()
    val tfc: TextFieldController by inject()
    var operation: String = ""

    val message = SimpleStringProperty()


    override val root = vbox {
        hbox {
            textfield(vc.dispVal) {
                subscribe<DispVal> {
                    vc.dispVal.value = message.value
                }
                style {
                    prefHeight = 100.px
                    prefWidth = 240.px
                }
            }
            hgrow = Priority.ALWAYS
        }
        hbox {
            gridpane {
                row {
                    button("1") {
                        action {
                            OBF.numButton(1.0)
                            message.value = "${OBF.MDN.value}"
                            fire(DispVal(message))
                            println(OBF.MDNList.value)
                        }
                    }
                    button("2") {
                        action {
                            OBF.numButton(2.0)
                            println(OBF.MDNList.value)
                            message.value = "${vc.currInput.value}"
                            fire(DispVal(message))
                        }
                    }
                    button("3") {
                        action {
                            OBF.numButton(3.0)
                            println(OBF.MDNList.value)
                            message.value = "${vc.currInput.value}"
                            fire(DispVal(message))
                        }
                    }
                    button("+") {
                        action {
                            vc.inputA.value = vc.currInput.value
                            println(vc.inputA.value)
                            operation = OBF.opButton("+")
                            message.value = "${vc.currInput.value}"
                            fire(DispVal(message))
                        }
                        addClass(Styles().opButton)
                    }
                }
                row {
                    button("4") {
                        action {
                            OBF.numButton(4.0)
                            message.value = "${vc.currInput.value}"
                            fire(DispVal(message))
                        }
                    }
                    button("5") {
                        action {
                            OBF.numButton(5.0)
                            message.value = "${vc.currInput.value}"
                            fire(DispVal(message))
                        }
                    }
                    button("6") {
                        action {
                            OBF.numButton(6.0)
                            message.value = "${vc.currInput.value}"
                            fire(DispVal(message))
                        }
                    }
                    button("-") {
                        action {
                            vc.inputA.value = vc.currInput.value
                            operation = OBF.opButton("-")
                            message.value = "${vc.currInput.value}"
                            fire(DispVal(message))
                        }
                        addClass(Styles().opButton)
                    }
                }
                row {
                    button("7") {
                        action {
                            OBF.numButton(7.0)
                            message.value = "${vc.currInput.value}"
                            fire(DispVal(message))
                        }
                    }
                    button("8") {
                        action {
                            OBF.numButton(8.0)
                            message.value = "${vc.currInput.value}"
                            fire(DispVal(message))
                        }
                    }
                    button("9") {
                        action {
                            OBF.numButton(9.0)
                            message.value = "${vc.currInput.value}"
                            fire(DispVal(message))
                        }
                    }
                    button("*") {
                        action {
                            vc.inputA.value = vc.currInput.value
                            operation = OBF.opButton("*")
                            message.value = "${vc.currInput.value}"
                            fire(DispVal(message))
                        }
                        addClass(Styles().opButton)
                    }
                }
                row {
                    button(".") {
                        action {
                            OBF.createMultiDigitNum()
                            vc.decFlag.value = false
                            println(vc.currInput)
                            message.value = "${vc.currInput.value}"
                            fire(DispVal(message))
                        }
                        addClass(Styles().opButton)
                    }
                    button("0") {
                        action {
                            OBF.numButton(0.0)
                            message.value = "${vc.currInput.value}"
                            fire(DispVal(message))
                        }
                    }
                    button("=") {
                        action {
                            if (vc.decFlag.value) {
                                OBF.createMultiDigitNum()
                            } else if (vc.decFlag.value == false) {
                                OBF.createDecimal()
                                vc.decFlag.value = true
                            }
                            vc.inputB.value = vc.currInput.value
                            println(vc.inputB.value)
                            println(OBF.equals(operation))
                            message.value = "${OBF.equals(operation)}"
                            fire(DispVal(message))
                            vc.currInput.value = OBF.equals(operation)
                        }
                        addClass(Styles().opButton)
                    }
                    button("/") {
                        action {
                            vc.inputA.value = vc.currInput.value
                            operation = OBF.opButton("/")
                            message.value = "${vc.currInput.value}"
                            fire(DispVal(message))
                        }
                        addClass(Styles().opButton)
                    }
                }
                row {
                    button("+/-") {
                        action {
                            vc.currInput.value = vc.currInput.value * -1
                            message.value = "${vc.currInput.value}"
                            fire(DispVal(message))
                        }
                        addClass(Styles().opButton)
                    }
                    button("^") {
                        addClass(Styles().opButton)
                    }
                    button("C") {
                        action {
                            OBF.clear()
                        }
                        addClass(Styles().opButton)
                    }
                    button("( )") {
                        addClass(Styles().opButton)
                    }

                }
            }

        }
    }
}