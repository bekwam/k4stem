package net.bekwam.k4stem.labassist
import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {
   init{
    tableView{
        backgroundColor = multi(Color.SIENNA,Color.BEIGE)
    }
       tabHeaderBackground{
           backgroundColor = multi(Color.DARKSALMON)
       }
    }
}