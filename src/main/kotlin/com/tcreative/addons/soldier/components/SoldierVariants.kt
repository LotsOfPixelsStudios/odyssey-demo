package com.tcreative.addons.soldier.components

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.getResource

fun Entity.loadVariants() {
    resource {
        textureLayer(
            arrayListOf(
                getResource("entity/textures/soldier_npc_0.png"),
                getResource("entity/textures/soldier_npc_1.png"),
                getResource("entity/textures/soldier_npc_2.png"),
                getResource("entity/textures/soldier_npc_3.png"),
            ), Query.variant
        )
    }
    behaviour {
        for (i in 0..3)
            componentGroup("texture_variant_$i") {
                variant {
                    value = i
                }
            }
        eventSpawned {
            randomize {
                for (i in 0..3)
                    randomComp {
                        weight = 1
                        add {
                            componentGroups = arrayListOf("texture_variant_$i")
                        }
                    }
            }
        }
    }
}