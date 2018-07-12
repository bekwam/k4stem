package net.bekwam.k4stem.labassist

import com.beust.klaxon.Klaxon
import javafx.application.Platform
import javafx.scene.control.Alert
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File

/**
 * Contains View-code for the menuing part of the MainView screen
 *
 * @author carl
 */
class MenuFragment : Fragment() {

    val ctrl : ComponentController by inject()

    override val root =
        menubar {
        menu("File") {
            item("New") {
                action {
                    ctrl.file = createTempFile("tempFile", ".json")
                    ctrl.dirtyFlag.set(true)
                    find<AddLabView>().openModal()

                }
            }
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
                            ctrl.file = files[0]
                            val contents = files[0].readText()
                            ctrl.lab = Klaxon()
                                    .fieldConverter(LabAssistantDefaultDate::class, zdtConverter)
                                    .fieldConverter(LabAssistantDefaultPrice::class, bdConverter)
                                    .parse<Lab>(contents)
                        } catch (exc: Exception) {
                            alert(Alert.AlertType.ERROR, exc.message!!)
                        }


                        title = "${fileNamer(files[0])}"
                        ctrl.dirtyFlag.set(true)

                    } else {
                        title = "K4Stem Lab Assistant"
                    }
                }
            }
            menu("Open Recent") {
                action{
                    val aaa = FileChooser()
                    aaa.showOpenDialog(primaryStage)
                }
            }

            item("Close") {
                action {

                    ctrl.file = null
                    ctrl.dirtyFlag.set(false)

                    title = "K4Stem Lab Assistant"
                    ctrl.lab = null
                }
            }
            separator()
            item("save") {
                action {
                    if (ctrl.lab != null) {
                        if (ctrl.dirtyFlag.value == true && ctrl.file != null) {
                            val newFile = Klaxon()
                                    .fieldConverter(LabAssistantDefaultDate::class, zdtConverter)
                                    .fieldConverter(LabAssistantDefaultPrice::class, bdConverter)
                                    .toJsonString(ctrl.lab!!)
                            ctrl.file!!.writeText(newFile)
                            ctrl.dirtyFlag.set(false)
                        } else {
                            alert(Alert.AlertType.CONFIRMATION, "save flag worked")
                        }
                    }
                }
            }
            item("Save As") {
                action {
                    if (ctrl.dirtyFlag.value == true && ctrl.file != null) {
                        val fc = FileChooser()
                        val newFileJsonString = Klaxon()
                                .fieldConverter(LabAssistantDefaultDate::class, zdtConverter)
                                .fieldConverter(LabAssistantDefaultPrice::class, bdConverter)
                                .toJsonString(ctrl.lab!!)
                        val extFilt = FileChooser.ExtensionFilter("json", "*.json")
                        fc.extensionFilters.add(extFilt)
                        fc.showSaveDialog(primaryStage).writeText(newFileJsonString)
                        ctrl.dirtyFlag.set(false)
                    }

                }
            }
            separator()
            item("Preferences")
            separator()
            item("Exit") {
                action {
                    if (ctrl.dirtyFlag.value) {
                        confirm("You Have Unsaved Files",
                                "Do you wish to proceed?")
                        { Platform.exit() }

                    }
                }

            }
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
