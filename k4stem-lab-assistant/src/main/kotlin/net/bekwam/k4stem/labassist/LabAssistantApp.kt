package net.bekwam.k4stem.labassist
import javafx.scene.Scene
import tornadofx.*
import java.nio.file.Path
import java.nio.file.Paths

class LabAssistantApp : App(MainView::class, Styles::class) {
    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 1700.0, 1000.0)
    override val configPath = Paths.get( System.getProperty("user.home"), ".k4stem.properties" )
    init{
        reloadStylesheetsOnFocus()
    }
}







//
