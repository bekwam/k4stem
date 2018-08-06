package net.bekwam.k4stem.labassist


import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.control.TableView
import javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import tornadofx.*
import java.time.ZonedDateTime


/**************************************************

An inventory management app for an electronics lab

 **************************************************/


class MainView : View("K4Stem Lab Assistant") {
    val lastChangedDate = SimpleObjectProperty<ZonedDateTime>()
    val status : TaskStatus by inject()
    val ctrl : ComponentController by inject()
    val CVctrl : ComponentViewController by inject()




    override val root = vbox {
        addClass(Styles.normalStyle)
        add( find<MenuFragment>() )
        vbox {
            hbox {
               add(find<SearchBarFragment>())

                hbox {
                    add(find<TilePaneFragment>())

                    hboxConstraints {
                            hGrow = Priority.ALWAYS
                        }

                    }
                    hboxConstraints {
                        hGrow = Priority.ALWAYS
                    }
                hboxConstraints {
                    hGrow = Priority.NEVER
                }
                padding = Insets(10.0)
                spacing = 60.0

            }

            separator()
            hbox {
                add(find<AddSubtractButtonView>())
            }
            ctrl.tblItems = tableview(ctrl.itemsList){
                column("Name", Component::nameProp)
                column("Description", Component::descProp)
                column("Source", Component::sourceProp)
                column("Type", Component::compProp)
                column("#", Component::numProp)
                column("Price", Component::priceProp)
                column("Model Number", Component::mnProp)
                column("Value", Component::valueProp)

                onMouseClicked = EventHandler {
                    if (it.clickCount == 2) {
                        if (ctrl.tblItems.selectedItem != null) {
                            val itemToEdit = ctrl.tblItems.selectedItem!!
                            CVctrl.itemFlag.set(true)
                            CVctrl.indOfC = ctrl.itemsList.indexOf(itemToEdit)
                            CVctrl.editCompoment(itemToEdit)
                        }
                    }
                    }


                prefWidth = 667.0
                prefHeight = 376.0
                columnResizePolicy = CONSTRAINED_RESIZE_POLICY
                vboxConstraints {
                    vGrow = Priority.ALWAYS
                }
            }
            hbox {
                add(find<RefreshButtonView>())
                spacing = 10.0
                hbox (4.0){
                    progressbar(status.progress)
                    label(status.message)
                    visibleWhen { status.running }
                    alignment = Pos.CENTER_LEFT

                }

            }


            vboxConstraints {
                vGrow = Priority.ALWAYS
            }
            padding = Insets(10.0)
            spacing = 10.0

        }
        style{
            backgroundColor += Color.POWDERBLUE
        }
    }


}
