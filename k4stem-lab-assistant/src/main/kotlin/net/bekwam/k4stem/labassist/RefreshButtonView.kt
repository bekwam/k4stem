package net.bekwam.k4stem.labassist

import javafx.geometry.Insets
import tornadofx.*

class RefreshButtonView : Fragment(){
    val ctrl : ComponentController by inject()
    override val root = hbox {
        button("refresh table") {
            action {
                ctrl.refreshFromModel()
            }
        }
        padding = Insets(10.0)
        spacing = 10.0
    }
}