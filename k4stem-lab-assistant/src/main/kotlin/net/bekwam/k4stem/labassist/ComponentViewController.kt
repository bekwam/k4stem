package net.bekwam.k4stem.labassist

import javafx.beans.property.*
import tornadofx.*
import java.math.BigDecimal

class ComponentViewController : Controller(){

    val nameInput = SimpleStringProperty()
    val descInput = SimpleStringProperty()
    val sourceInput = SimpleStringProperty()
    val cpInput = SimpleObjectProperty<ComponentType>()
    val numInput = SimpleIntegerProperty()
    val priceInput = SimpleObjectProperty<BigDecimal>()
    val editingPrice = SimpleStringProperty()
    val mnInput = SimpleStringProperty()
    val valInput = SimpleDoubleProperty()
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
        cpInput.value = null
        numInput.value = 0
        editingPrice.value = "0.0"
        mnInput.value = null
        valInput.value = 0.0
    }
}