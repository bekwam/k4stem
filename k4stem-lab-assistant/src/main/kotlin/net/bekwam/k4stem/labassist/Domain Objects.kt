package net.bekwam.k4stem.labassist

import java.math.BigDecimal
import java.time.ZonedDateTime

class Lab (version : Int,
           labName  : String,
           labOwner : String,
           inventory: List<Inventory>)

class Inventory(lastChanged : ZonedDateTime,
                component: List<Component>)

class Component(name : String,
                description : String,
                source : String,
                componentType : ComponentType = ComponentType.UNSPECIFIED,
                numOnHand : Int,
                price : BigDecimal,
                modelNumber : String,
                value : Double)


enum class ComponentType {
    RESISTOR,
    CAPACITOR,
    INDUCTOR,
    IC,
    TRANSISTOR,
    POTENTIOMETER,
    UNSPECIFIED
}