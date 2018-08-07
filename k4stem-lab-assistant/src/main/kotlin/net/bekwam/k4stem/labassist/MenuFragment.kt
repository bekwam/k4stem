package net.bekwam.k4stem.labassist

import com.beust.klaxon.Klaxon
import javafx.application.Platform
import javafx.scene.control.Alert
import javafx.scene.control.TextField
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

    val ctrl: ComponentController by inject()

    override val root =
            menubar {
                menu("File") {
                    item("New") {
                        action {
                            ctrl.file.value = createTempFile()
                            ctrl.dirtyFlag.value = true
                            ctrl.saveAsFlag.value = true
                            find<AddLabView>().openModal()

                        }
                    }
                    item("Open") {
                        action {
                            val fl = ctrl.fileSelection()
                            ctrl.file.value = fl[0]
                            val f = ctrl.file.value
                            /* fun addOpenRecent(f : File) {
                        val tempList = app.config["recentFiles"] as MutableList<File>
                        if (tempList.isNotEmpty()) {
                            ctrl.OpenRecent = app.config["recentFiles"] as MutableList<File>
                            if (ctrl.OpenRecent.contains(f)) {
                                val indexF = ctrl.OpenRecent.indexOf(f)
                                ctrl.OpenRecent.removeAt(indexF)
                            }
                            if (ctrl.OpenRecent.size < 10) {
                                    ctrl.OpenRecent.add(0, f)
                                } else {
                                    ctrl.OpenRecent.add(0, f)
                                    ctrl.OpenRecent.removeAt(10)
                                }
                        }
                        else{
                            ctrl.OpenRecent = mutableListOf(f)
                        }
                        app.config["recentFiles"] = ctrl.OpenRecent
                    }*/
                            runAsync {
                                updateMessage("Loading Components...")
                                updateProgress(0.4, 1.0)
                                // addOpenRecent(f)
                                val f2 = ctrl.readFile(f)
                                ctrl.parse(f2)
                            } ui {
                                ctrl.dirtyFlag.value = true
                                ctrl.refreshFromModel()
                            } fail {
                                alert(Alert.AlertType.ERROR, "Error loading file:", it.message) {
                                    style {
                                        backgroundColor += Color.POWDERBLUE
                                        font = Font.font("Verdana")
                                        fontSize = 24.px
                                        textFill = Color.SIENNA
                                    }
                                }
                            }
                            println(app.config["recentFiles"])
                            println(ctrl.OpenRecent)
                        }
                    }


                    menu("Open Recent") {

                    }

                    item("Close") {
                        action {

                            ctrl.file.value = null
                            ctrl.dirtyFlag.set(false)

                            title = "K4Stem Lab Assistant"
                            ctrl.itemsList.clear()
                            ctrl.lab.value = null

                        }
                    }
                    separator()
                    item("Save") {
                        action {
                            if(ctrl.saveAsFlag.value){
                                ctrl.saveAs()
                        }
                            else{
                                ctrl.save()
                            }
                        }
                    }
                    item("Save As") {
                        action {
                            ctrl.saveAs()
                            ctrl.saveAsFlag.value = false
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

                            } else {
                                Platform.exit()
                            }
                        }

                    }
                }



                    menu("Edit") {
                        item("Cut") {
                            action {
                                //
                                // who has focus? for now, just honoring TextFields
                                // like the Keyboards TextField
                                //
                                // * The copy menuItem is not available to the details
                                // panel
                                //
                                if( scene != null ) {
                                    val focusedNode = scene!!.focusOwner
                                    if( focusedNode is TextField) {
                                        focusedNode.cut()
                                    }
                                }
                            }
                        }
                        item("Copy") {
                            action {
                                if( scene != null ) {
                                    val focusedNode = scene!!.focusOwner
                                    if( focusedNode is TextField) {
                                        focusedNode.copy()
                                    }
                                }
                            }

                        }
                        item("Paste") {
                            action {
                                if (scene != null) {
                                    val focusedNode = scene!!.focusOwner
                                    if (focusedNode is TextField) {
                                        focusedNode.paste()
                                    }
                                }
                            }
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

