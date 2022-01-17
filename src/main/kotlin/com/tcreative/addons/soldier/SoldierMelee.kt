package com.tcreative.addons.soldier

import com.tcreative.devtools.tranclate.builder.getEntityAnimationResource
import com.tcreative.devtools.tranclate.builder.getEntityGeometryResource
import com.tcreative.devtools.tranclate.systemaddon.SystemAddon

fun soldierMelee(systemAddon: SystemAddon) {
    systemAddon.entity {
        name("soldier_melee", "Soldier")
        loadTextures(this)
        resAnimations(getEntityAnimationResource("soldier_npc.animation.json"))
        geometry(getEntityGeometryResource("soldier_npc.geo.json"))

        componentGroups { loadTextureCompGroups(this) }
        components {
            sharedComponents(this)
            typeFamily(arrayListOf("mob", "addon"))
            delayedAttack {
                priority(1)
            }
            attack(5)
        }
        events { spawnEvent(this) }
    }
}