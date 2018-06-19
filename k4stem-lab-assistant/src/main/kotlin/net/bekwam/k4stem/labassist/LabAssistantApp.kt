package net.bekwam.k4stem.labassist

import com.beust.klaxon.Klaxon
import com.beust.klaxon.json
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.web.WebEngine
import javafx.stage.FileChooser
import javafx.stage.Stage
import tornadofx.*
import java.io.File

/**************************************************

  An inventory management app for an electronics lab

**************************************************/

class MainView : View("K4Stem Lab Assistant") {
    var file: File? = null
    var lab: Lab? = null
    override val root = borderpane {
        label("Content goes here")
        top = menubar {
            menu("File") {
                item("New")
                item("Open") {
                    action {
                        val filterFiles = arrayOf(FileChooser.ExtensionFilter("Files", "*.json"))
                        val files = chooseFile("Select File", filterFiles, FileChooserMode.Single)
                        fun fileNamer(file: File?): String {
                            if (file != null) {
                                return "K4Stem Lab Assistant - $file"
                            } else {
                                return "K4Stem Lab Assistant"
                            }
                        }
                        if (files.size > 0) {
                            try {
                                file = files[0]
                                val contents = files[0].readText()
                                lab = Klaxon()
                                        .fieldConverter(LabAssistantDefaultDate::class, zdtConverter)
                                        .fieldConverter(LabAssistantDefaultPrice::class, bdConverter)
                                        .parse<Lab>(contents)
                                alert(Alert.AlertType.INFORMATION, "${lab!!.inventory[0].components[0].componentType}")
                            } catch (exc: Exception) {
                                alert(Alert.AlertType.ERROR, exc.message!!)
                            }


                            title = "${fileNamer(files[0])}"
                        } else {
                            title = "K4Stem Lab Assistant"
                        }
                    }
                }
                menu("Open Recent") {}
                item("Close")
                separator()
                item("save") {
                    action {
                        val newFile = Klaxon()
                                .fieldConverter(LabAssistantDefaultDate::class, zdtConverter)
                                .fieldConverter(LabAssistantDefaultPrice::class, bdConverter)
                                .toJsonString(lab!!)
                        file!!.writeText(newFile)
                    }
                }
                // println("Result: ${newFile.toJsonString()}")


                item("Save As"){

                }
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