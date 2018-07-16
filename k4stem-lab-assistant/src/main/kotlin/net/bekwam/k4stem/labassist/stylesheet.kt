package net.bekwam.k4stem.labassist
import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val tableStyle by cssclass()
    }
   init{
    tableStyle{
            cellHeight = Dimension(50.0, Dimension.LinearUnits.px)
            cellWidth = Dimension(15.0,Dimension.LinearUnits.px)
    }
   }
}