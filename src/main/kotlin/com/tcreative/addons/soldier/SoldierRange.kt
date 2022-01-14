package com.tcreative.addons.soldier

import com.tcreative.devtools.tranclate.addon.beh.entites.data.Subject
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
            typeFamily(arrayListOf("mob", "addon"))
            physics()
            pushable()
            navigationWalk {
                avoidDamageBlocks(true)
                avoidWater(true)
                canPathOverWater(false)
            }
            behRangedAttack {
                priority(1)
            }
            shooter("arrow")
            movement()
            behNearestAttackableTarget {
                priority(1)
                entityTypes {
                    type {
                        filters {
                            isFamily(subject = Subject.OTHER, value = "monster")
                        }
                    }
                }
            }
        }
        events { spawnEvent(this) }
    }
}