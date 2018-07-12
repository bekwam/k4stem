package net.bekwam.k4stem.labassist


import com.beust.klaxon.Klaxon
import javafx.application.Platform
import javafx.beans.property.*
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.control.TableView
import javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File
import java.time.ZonedDateTime


/**************************************************

An inventory management app for an electronics lab

 **************************************************/

class MainView : View("K4Stem Lab Assistant") {
    var file: File? = null
    val lastChangedDate = SimpleObjectProperty<ZonedDateTime>()
    var tblItems: TableView<Component> by singleAssign()
    val keywords = SimpleStringProperty()
    val ctrl : ComponentController by inject()
    val CVctrl : ComponentViewController by inject()




    override val root = vbox {
        menubar {
            menu("File") {
                item("New") {
                    action {
                        file = createTempFile("tempFile", ".json")
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
                                file = files[0]
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

                        file = null
                        ctrl.dirtyFlag.set(false)

                        title = "K4Stem Lab Assistant"
                        ctrl.lab = null
                    }
                }
                separator()
                item("save") {
                    action {
                        if (ctrl.lab != null) {
                            if (ctrl.dirtyFlag.value == true && file != null) {
                                val newFile = Klaxon()
                                        .fieldConverter(LabAssistantDefaultDate::class, zdtConverter)
                                        .fieldConverter(LabAssistantDefaultPrice::class, bdConverter)
                                        .toJsonString(ctrl.lab!!)
                                file!!.writeText(newFile)
                                ctrl.dirtyFlag.set(false)
                            } else {
                                alert(Alert.AlertType.CONFIRMATION, "save flag worked")
                            }
                        }
                    }
                }
                item("Save As") {
                    action {
                        if (ctrl.dirtyFlag.value == true && file != null) {
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
        vbox {
            hbox {
                hbox {
                    label("Keywords:")
                    textfield(keywords)
                    button("Search"){
                        action {
                            var items_toAdd = mutableListOf<Component>()
                            if(keywords.isNotEmpty.value && ctrl.lab !=null &&  ctrl.lab!!.inventory.size > 0 && ctrl.lab!!.inventory[0].components.isNotEmpty()) {
                                println("Searching For: ${keywords.value}")
                                var toks = keywords.value.split(" ", ",")
                                ctrl.lab!!.inventory[0].components.forEach{c ->
                                    toks.forEach {
                                        if (c.name.contains(it, true) || (c.componentType.toString().contains(it, true))) {
                                            items_toAdd.add(c)
                                        }
                                    }
                                    if (resCheck.value && (c.componentType == ComponentType.RESISTOR)&& !items_toAdd.contains(c)) {
                                        items_toAdd.add(c)
                                    }
                                    if (capCheck.value && (c.componentType == ComponentType.CAPACITOR)&& !items_toAdd.contains(c) ) {
                                        items_toAdd.add(c)
                                    }
                                    if (icCheck.value && (c.componentType == ComponentType.IC) && !items_toAdd.contains(c)) {
                                        items_toAdd.add(c)
                                    }
                                    if (indCheck.value && (c.componentType == ComponentType.INDUCTOR) && !items_toAdd.contains(c)) {
                                        items_toAdd.add(c)
                                    }
                                    if (transCheck.value && (c.componentType == ComponentType.TRANSISTOR) && !items_toAdd.contains(c)) {
                                        items_toAdd.add(c)
                                    }
                                    if (potCheck.value && (c.componentType == ComponentType.POTENTIOMETER) && !items_toAdd.contains(c)) {
                                        items_toAdd.add(c)
                                    }
                                    if(allCheck.value){items_toAdd.add(c)}
                                }
                            }
                            else if(keywords.isEmpty().value && ctrl.lab != null && ctrl.lab!!.inventory.size > 0 && ctrl.lab!!.inventory[0].components.isNotEmpty()) {
                                if (resCheck.value || capCheck.value ||icCheck.value ||indCheck.value ||transCheck.value || potCheck.value || allCheck.value) {
                                    ctrl.lab!!.inventory[0].components.forEach { c ->
                                        if (resCheck.value && (c.componentType == ComponentType.RESISTOR)) {
                                            items_toAdd.add(c)
                                        }
                                        if (capCheck.value && (c.componentType == ComponentType.CAPACITOR)) {
                                            items_toAdd.add(c)
                                        }
                                        if (icCheck.value && (c.componentType == ComponentType.IC)) {
                                            items_toAdd.add(c)
                                        }
                                        if (indCheck.value && (c.componentType == ComponentType.INDUCTOR)) {
                                            items_toAdd.add(c)
                                        }
                                        if (transCheck.value && (c.componentType == ComponentType.TRANSISTOR)) {
                                            items_toAdd.add(c)
                                        }
                                        if (potCheck.value && (c.componentType == ComponentType.POTENTIOMETER)) {
                                            items_toAdd.add(c)
                                        }
                                        if(allCheck.value){
                                            items_toAdd.add(c)
                                        }
                                    }
                                }
                                else {
                                    refreshFromModel()
                                }

                            }
                            ctrl.itemsList.setAll(items_toAdd)
                        }
                    }
                }


                hbox {
                    tilepane {
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

                        hboxConstraints {
                            hGrow = Priority.ALWAYS
                        }

                    }
                    hboxConstraints {
                        hGrow = Priority.ALWAYS
                    }
                }
                hboxConstraints {
                    hGrow = Priority.NEVER
                }
                padding = Insets(10.0)
                spacing = 60.0

            }

            separator()
            hbox{
                button("+"){
                    action {
                        if(ctrl.lab!=null) {
                            CVctrl.itemFlag.set(false)
                            CVctrl.resetInputs()
                            find<ComponentView>(params= mapOf("a" to "b")).openWindow()
                        }
                        else{
                            alert(Alert.AlertType.INFORMATION,"No Inventory Open")
                        }
                    }
                    style{
                        backgroundColor = multi(Color.LIGHTGOLDENRODYELLOW)
                    }
                }
                button("-"){
                    action{
                        if (tblItems.selectedItem != null) {
                            val selectedItem = tblItems.selectedItem!!
                            ctrl.delete(selectedItem)

                        }
                    }
                    style{
                        backgroundColor = multi(Color.LIGHTGOLDENRODYELLOW)
                    }
                }
                spacing = 10.0
                padding = Insets(10.0)
            }
            tblItems = tableview(ctrl.itemsList) {
                column("Name", Component::nameProp)
                column("Description", Component::descProp)
                column("Source", Component::sourceProp)
                column("Type", Component::compProp)
                column("#", Component::numProp)
                column("Price", Component::priceProp)
                column("Model Number", Component::mnProp)
                column("Value", Component::valueProp)

                onMouseClicked = EventHandler {
                    if (it.clickCount == 2) {
                        if (tblItems.selectedItem != null) {
                            val itemToEdit = tblItems.selectedItem!!
                            CVctrl.itemFlag.set(true)
                            CVctrl.indOfC = ctrl.itemsList.indexOf(itemToEdit)
                            CVctrl.editCompoment(itemToEdit)
                        }
                    }

                }

                prefWidth = 667.0
                prefHeight = 376.0
                columnResizePolicy = CONSTRAINED_RESIZE_POLICY
                vboxConstraints {
                    vGrow = Priority.ALWAYS
                }



            }
            hbox {
                button("refresh table") {
                    action {
                        refreshFromModel()
                    }
                }
                padding = Insets(10.0)
                spacing = 10.0
            }
            vboxConstraints {
                vGrow = Priority.ALWAYS
            }
            padding = Insets(10.0)
            spacing = 10.0

        }
        style{
            backgroundColor= multi(Color.BURLYWOOD,Color.SIENNA)
        }
    }

    private fun refreshFromModel() {
        if(ctrl.lab!=null) {
            ctrl.itemsList.clear()
            for (inv in ctrl.lab!!.inventory) {
                for (comp in inv!!.components) {
                    ctrl.itemsList.add(Component(comp.name, comp.description, comp.source, comp.componentType, comp.numOnHand, comp.price, comp.modelNumber, comp.valueComp))
                }
            }
        }
        else {
            alert(Alert.AlertType.INFORMATION, "No New Information")
        }
    }

}
