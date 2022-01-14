package com.tcreative.addons.soldier

import com.tcreative.devtools.tranclate.addon.beh.entites.BehEntityComponentGroups
import com.tcreative.devtools.tranclate.addon.beh.entites.events.BehEntityEvents
import com.tcreative.devtools.tranclate.addon.molang.Query
import com.tcreative.devtools.tranclate.builder.getEntityTextureResource
import com.tcreative.devtools.tranclate.systemaddon.entityapi.AddonEntity

fun loadTextures(addonEntity: AddonEntity) {
    addonEntity.textures(
        arrayListOf(
            getEntityTextureResource("soldier_npc_0.png"),
            getEntityTextureResource("soldier_npc_1.png"),
            getEntityTextureResource("soldier_npc_2.png"),
            getEntityTextureResource("soldier_npc_3.png")
        ), Query.variant
    )
}

fun loadTextureCompGroups(behEntityComponentGroups: BehEntityComponentGroups) {
    for (i in 0..3)
        behEntityComponentGroups.componentGroup("texture_variant_$i") {
            variant(i)
        }
}

fun spawnEvent(behEntityEvents: BehEntityEvents) {
    behEntityEvents.defaultBornEvent {
        randomize {
            for (i in 0..3)
                randomComp {
                    weight(1)
                    add {
                        componentGroup("texture_variant_$i")
                    }
                }
        }
    }
}