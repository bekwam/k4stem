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
                    ctrl.file.value = createTempFile("tempFile", ".json")
                    ctrl.dirtyFlag.set(true)
                    find<AddLabView>().openModal()

                }
            }
            item("Open") {
                action {
                    val fl = ctrl.fileSelection()
                    ctrl.file.value = fl[0]
                    val f = ctrl.file.value
                    runAsync {
                        updateMessage("Loading Components...")
                        updateProgress(0.4, 1.0)
                       var f2 = ctrl.readFile(f)
                        ctrl.parse(f2)
                    } ui {
                        ctrl.dirtyFlag.value = true
                        ctrl.refreshFromModel()
                    } fail{
                       alert(Alert.AlertType.ERROR,"Error loading file:",it.message)
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

                    ctrl.file.value = null
                    ctrl.dirtyFlag.set(false)

                    title = "K4Stem Lab Assistant"
                    ctrl.lab.value = null
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
                            ctrl.file.value!!.writeText(newFile)
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
