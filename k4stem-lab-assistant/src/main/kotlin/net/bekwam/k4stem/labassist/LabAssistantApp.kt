package net.bekwam.k4stem.labassist

import javafx.scene.Scene
import tornadofx.*

/**************************************************

  An inventory management app for an electronics lab

**************************************************/

class MainView : View("K4Stem Lab Assistant") {
    override val root = vbox { label("Content goes here")}
}

class LabAssistantApp : App(MainView::class) {
    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 800.0, 600.0)
}

fun main(args : Array<String>) {
    launch<LabAssistantApp>(args)
}