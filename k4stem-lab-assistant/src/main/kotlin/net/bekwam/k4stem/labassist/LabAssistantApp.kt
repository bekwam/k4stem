package net.bekwam.k4stem.labassist
import javafx.scene.Scene
import tornadofx.*
class LabAssistantApp : App(MainView::class, Styles::class) {
    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 1200.0, 900.0)
    init{
        reloadStylesheetsOnFocus()
    }
}





//
