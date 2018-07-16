package net.bekwam.k4stem.labassist

import tornadofx.*

class SearchBarFragment : Fragment(){
    val ctrl : ComponentController by inject()
    val CVctrl : ComponentViewController by inject()
    override val root = hbox {
        label("Keywords:")
        textfield(ctrl.keywords)
        button("Search"){
            action {
                var items_toAdd = mutableListOf<Component>()
                if(ctrl.keywords.isNotEmpty.value && ctrl.lab !=null &&  ctrl.lab!!.inventory.size > 0 && ctrl.lab!!.inventory[0].components.isNotEmpty()) {
                    println("Searching For: ${ctrl.keywords.value}")
                    var toks = ctrl.keywords.value.split(" ", ",")
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
                else if(ctrl.keywords.isEmpty().value && ctrl.lab != null && ctrl.lab!!.inventory.size > 0 && ctrl.lab!!.inventory[0].components.isNotEmpty()) {
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
                        ctrl.refreshFromModel()
                    }

                }
                ctrl.itemsList.setAll(items_toAdd)
            }
        }
    }
}