package net.bekwam.k4stem.labassist

import com.beust.klaxon.Klaxon
import javafx.application.Platform
import javafx.scene.control.Alert
import javafx.scene.input.Clipboard
import javafx.scene.input.ClipboardContent
import javafx.scene.paint.Color
import javafx.scene.text.Font
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
                       alert(Alert.AlertType.ERROR,"Error loading file:",it.message){
                           style{
                               backgroundColor += Color.POWDERBLUE
                               font = Font.font("Verdana")
                               fontSize = 24.px
                               textFill = Color.SIENNA
                           }
                       }
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
            item("Save") {
                action {
                    if (ctrl.lab.value != null) {
                        if (ctrl.dirtyFlag.value == true && ctrl.file.value != null) {
                            val newFile = Klaxon()
                                    .fieldConverter(LabAssistantDefaultDate::class, zdtConverter)
                                    .fieldConverter(LabAssistantDefaultPrice::class, bdConverter)
                                    .toJsonString(ctrl.lab.value)
                            ctrl.file.value!!.writeText(newFile)
                            ctrl.dirtyFlag.set(false)
                        } else {
                            alert(Alert.AlertType.CONFIRMATION, "save flag worked"){
                                style{
                                    backgroundColor += Color.POWDERBLUE
                                    font = Font.font("Verdana")
                                    fontSize = 24.px
                                    textFill = Color.SIENNA
                                }
                            }
                        }
                    }
                }
            }
            item("Save As") {
                action {
                    if (ctrl.dirtyFlag.value == true && ctrl.file.value != null) {
                        val fc = FileChooser()
                        val newFileJsonString = Klaxon()
                                .fieldConverter(LabAssistantDefaultDate::class, zdtConverter)
                                .fieldConverter(LabAssistantDefaultPrice::class, bdConverter)
                                .toJsonString(ctrl.lab.value)
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

                    }else{Platform.exit()}
                }

            }
        }


        menu("Edit") {
            val clipboard = Clipboard.getSystemClipboard()
            val content = ClipboardContent()
            item("Cut"){
                fun cut(){
                    if(ctrl.tblItems.selectedItem != null){
                        val text = ctrl.tblItems.selectedItem.toString()
                        content.putString(text)
                        clipboard.setContent(content)
                        ctrl.delete(ctrl.tblItems.selectedItem!!)
                    }
                }
                action {
                    cut()
                }


            }
            item("Copy"){
                fun copy() {
                    if (ctrl.tblItems.selectedItem != null) {
                        val text = ctrl.tblItems.selectedItem.toString()
                        content.putString(text)
                        clipboard.setContent(content)
                        println(text)
                    }
                }
                action {
                    copy()
                }

            }
            item("Paste"){

            }
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
