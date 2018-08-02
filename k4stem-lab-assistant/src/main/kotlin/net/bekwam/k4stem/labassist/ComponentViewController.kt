package net.bekwam.k4stem.labassist

import javafx.beans.property.*
import tornadofx.*
import java.math.BigDecimal

class ComponentViewController : Controller(){

    val nameInput = SimpleStringProperty(null)
    val descInput = SimpleStringProperty(null)
    val sourceInput = SimpleStringProperty(null)
    val cpInput = SimpleObjectProperty<ComponentType>(null)
    val numInput = SimpleIntegerProperty(0)
    val priceInput = SimpleObjectProperty<BigDecimal>(null)
    val editingPrice = SimpleStringProperty("0.0")
    val mnInput = SimpleStringProperty(null)
    val valInput = SimpleDoubleProperty(0.0)
    var newComp : Component? = null
    val compctrl : ComponentController by inject()
    val itemFlag = SimpleBooleanProperty()
    var indOfC : Int? = null

    fun editCompoment(c : Component){
        nameInput.value = (c.name)
        descInput.value = (c.description)
        sourceInput.value = (c.source)
        cpInput.value = (c.componentType)
        numInput.value = (c.numOnHand)
        editingPrice.value = (c.price).toString()
        mnInput.value = (c.modelNumber)
        valInput.value = (c.valueComp)
        find<ComponentView>().openModal()
    }
    fun resetInputs(){
        nameInput.value = null
        descInput.value = null
        sourceInput.value = null
        cpInput.value = ComponentType.UNSPECIFIED
        numInput.value = 0
        editingPrice.value = "0.0"
        mnInput.value = null
        valInput.value = 0.0
    }
}