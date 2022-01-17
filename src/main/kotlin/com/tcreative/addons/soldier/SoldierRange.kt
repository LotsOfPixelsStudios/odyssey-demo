package com.tcreative.addons.soldier

import com.tcreative.devtools.tranclate.builder.getEntityAnimationResource
import com.tcreative.devtools.tranclate.builder.getEntityGeometryResource
import com.tcreative.devtools.tranclate.systemaddon.SystemAddon

fun soldierRange(systemAddon: SystemAddon) {
    systemAddon.entity {
        name("soldier_range", "Soldier")
        loadTextures(this)
        resAnimations(getEntityAnimationResource("soldier_npc.animation.json"))
        geometry(getEntityGeometryResource("soldier_npc.geo.json"))

        componentGroups { loadTextureCompGroups(this) }
        components {
            sharedComponents(this)
            typeFamily(arrayListOf("mob", "addon"))
            behRangedAttack {
                priority(1)
            }
            shooter("arrow")
            attack(4)
        }
        events { spawnEvent(this) }
    }
}