package net.bekwam.k4stem.labassist

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonValue
import com.beust.klaxon.KlaxonException
import java.math.BigDecimal
import java.time.ZonedDateTime

data class Lab (var version : Int,
           var labName  : String,
           var labOwner : String,
           var inventory: List<Inventory>)

class Inventory @JvmOverloads constructor(
               @LabAssistantDefaultDate val lastChanged : ZonedDateTime,
               var components: List<Component>)

@Target(AnnotationTarget.FIELD)
annotation class LabAssistantDefaultDate

data class Component @JvmOverloads constructor(
                val name : String,
                val description : String,
                val source : String,
                val componentType : ComponentType = ComponentType.UNSPECIFIED,
                var numOnHand : Int,
                @LabAssistantDefaultPrice val price : BigDecimal,
                val modelNumber : String,
                val valueComp : Double)

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
