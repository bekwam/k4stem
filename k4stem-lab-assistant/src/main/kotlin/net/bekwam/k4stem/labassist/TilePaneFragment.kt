package net.bekwam.k4stem.labassist

import javafx.geometry.Pos
import tornadofx.*

class TilePaneFragment : Fragment() {
    override val root = tilepane {
        checkbox("Resistor", resCheck)
        checkbox("Capacitor", capCheck)
        checkbox("Inductor", indCheck)
        checkbox("IC", icCheck)
        checkbox("Potentiometer", potCheck)
        checkbox("Transistor", transCheck)
        checkbox("Check All", allCheck)
        prefColumns = 2
        prefTileWidth = 150.0
        tileAlignment = Pos.CENTER_LEFT
        vgap = 4.0
        hgap = 2.0
    }
}