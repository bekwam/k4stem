package net.bekwam.k4stem.labassist

import com.beust.klaxon.*
import javafx.beans.property.*
import javafx.scene.control.Alert
import javafx.scene.control.TableView
import javafx.scene.input.Clipboard
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.stage.FileChooser
import java.math.BigDecimal
import java.time.ZonedDateTime
import tornadofx.*
import java.io.File

data class Lab (var version : Int,
           var labName  : String,
           var labOwner : String,
           var inventory: List<Inventory>)

class Inventory @JvmOverloads constructor(
        @LabAssistantDefaultDate val lastChanged : ZonedDateTime,
        var components: MutableList<Component>)

@Target(AnnotationTarget.FIELD)
annotation class LabAssistantDefaultDate

data class Component @JvmOverloads constructor(
        val name : String,
        val description : String= "",
        val source : String= "",
        val componentType : ComponentType = ComponentType.UNSPECIFIED,
        var numOnHand : Int,
        @LabAssistantDefaultPrice val price : BigDecimal,
        val modelNumber : String= "",
        val valueComp : Double){
    @Json(ignored = true)
    val nameProp = SimpleStringProperty(name)
    @Json(ignored=true)
    val descProp = SimpleStringProperty(description)
    @Json(ignored=true)
    val sourceProp = SimpleStringProperty(source)
    @Json(ignored=true)
    val compProp = SimpleObjectProperty<ComponentType>(componentType)
    @Json(ignored=true)
    val numProp = SimpleIntegerProperty(numOnHand)
    @Json(ignored=true)
    val priceProp = SimpleObjectProperty<BigDecimal>(price)
    @Json(ignored=true)
    val mnProp = SimpleStringProperty(modelNumber)
    @Json(ignored=true)
    val valueProp = SimpleDoubleProperty(valueComp)
}

class ComponentController : Controller() {
    var itemsList = observableList<Component>()
    var lab = SimpleObjectProperty<Lab>()
    val dirtyFlag = SimpleBooleanProperty()
    val saveAsFlag = SimpleBooleanProperty()
    var file = SimpleObjectProperty<File>(null)
    val keywords = SimpleStringProperty()
    var tblItems: TableView<Component> by singleAssign()
    var OpenRecent = mutableListOf<File>()

    val api : Rest by inject()

    fun delete(c: Component) {
        itemsList.remove(c)
        lab.value.inventory[0].components.remove(c)

    }

    fun refreshFromModel() {
        if (lab.value != null) {
            itemsList.clear()
            for (inv in lab.value.inventory) {
                for (comp in inv.components) {
                    itemsList.add(Component(comp.name, comp.description, comp.source, comp.componentType, comp.numOnHand, comp.price, comp.modelNumber, comp.valueComp))
                }
            }
        } else {
            alert(Alert.AlertType.INFORMATION, "No New Information")
        }
    }

    fun fileSelection() : List<File> {
        val filterFiles = arrayOf(FileChooser.ExtensionFilter("Files", "*.json"))
        val files = chooseFile("Select File", filterFiles, FileChooserMode.Single)
        return files
    }
    fun readFile(file: File) : String{
        return file.readText()
    }

    fun fileNamer(file: File?): String {
        if (file != null) {
            return "K4Stem Lab Assistant - $file"
        } else {
            return "K4Stem Lab Assistant"
        }
    }
    fun parse(file : String){
        if (file != "") {
            lab.value = Klaxon()
                    .fieldConverter(LabAssistantDefaultDate::class, zdtConverter)
                    .fieldConverter(LabAssistantDefaultPrice::class, bdConverter)
                    .parse<Lab>(file)
                   // dirtyFlag.set(true)
        }
        else{
            lab.value = null
        }
    }

    fun saveAs(){
        if (file.value != null) {
            val fc = FileChooser()
            val newFileJsonString = Klaxon()
                    .fieldConverter(LabAssistantDefaultDate::class, zdtConverter)
                    .fieldConverter(LabAssistantDefaultPrice::class, bdConverter)
                    .toJsonString(lab.value)
            val extFilt = FileChooser.ExtensionFilter("json", "*.json")
            fc.extensionFilters.add(extFilt)
            fc.showSaveDialog(primaryStage).writeText(newFileJsonString)
            dirtyFlag.set(false)
        }
    }

    fun save(){
        if (lab.value != null) {
            if (file.value != null) {
                val newFile = Klaxon()
                        .fieldConverter(LabAssistantDefaultDate::class, zdtConverter)
                        .fieldConverter(LabAssistantDefaultPrice::class, bdConverter)
                        .toJsonString(lab.value)
                file.value!!.writeText(newFile)
                dirtyFlag.set(false)
            }
        }
    }

}
@Target (AnnotationTarget.FIELD)
annotation class LabAssistantDefaultPrice



enum class ComponentType {
    RESISTOR,
    CAPACITOR,
    INDUCTOR,
    IC,
    TRANSISTOR,
    POTENTIOMETER,
    UNSPECIFIED
}
val zdtConverter = object: Converter {
    override fun canConvert(cls: Class<*>)
            = cls == ZonedDateTime::class.java

    override fun toJson(value: Any): String
            = """{"date" : "$value"}"""

    override fun fromJson(jv: JsonValue) =
            if (jv.string != null) {
                ZonedDateTime.parse(jv.string)
            } else {
                throw KlaxonException("can't parse zoned date: ${jv.string}")
            }
}
val bdConverter = object: Converter {
    override fun canConvert(cls: Class<*>)
            = cls == BigDecimal::class.java

    override fun fromJson(jv: JsonValue) =
            if (jv.string != null){
                BigDecimal(jv.string)
            }
            else{
                throw KlaxonException("can't parse big decimal: ${jv.string}")
            }

    override fun toJson(value: Any) :String
            = """{"price" : "$value"}"""
}