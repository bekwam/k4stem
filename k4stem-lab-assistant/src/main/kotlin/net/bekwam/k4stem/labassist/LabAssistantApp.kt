package net.bekwam.k4stem.labassist

import javafx.scene.Scene
import javafx.scene.web.WebEngine
import javafx.stage.FileChooser
import javafx.stage.Stage
import tornadofx.*
import java.io.File

/**************************************************

  An inventory management app for an electronics lab

**************************************************/

class MainView : View("K4Stem Lab Assistant") {
    override val root = borderpane {
        label("Content goes here")
        top = menubar {
                menu("File") {
                    item("New")
                    item("Open") {
                    action {
                        val filterFiles = arrayOf(FileChooser.ExtensionFilter("Files", "*.txt",".*kt"))
                        val file = chooseFile("Select File", filterFiles, FileChooserMode.Single)
                        fun fileNamer(file: File?): String {
                            if (file != null) {
                                return "K4Stem Lab Assistant - $file"
                            } else {
                                return "K4Stem Lab Assistant"
                            }
                        }
                        title = "${fileNamer(file[0])}"
                    }
                }
                    menu("Open Recent") {}
                    item("Close")
                    separator()
                    item("Save")
                    item("Save As")
                    separator()
                    item("Preferences")
                    separator()
                    item("Exit")
                }
                menu("Edit") {
                    item("Cut")
                    item("Copy")
                    item("Paste")
                }
                menu("View")
                menu("Help") {
                    item("Wiki") {
                        action {
                            hostServices.showDocument("https://github.com/bekwam/k4stem/wiki")
                        }
                    }
                    item("About")
                }
        }
    }
}






class LabAssistantApp : App(MainView::class) {
    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 800.0, 600.0)
}


fun main(args : Array<String>) {
    launch<LabAssistantApp>(args)
}