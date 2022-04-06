package com.tcreative.addons.soldier

import com.tcreative.devtools.tranclate.Props
import com.tcreative.devtools.tranclate.addon.animcontrollers.AnimationControllers
import com.tcreative.devtools.tranclate.addon.beh.entites.BehEntityComponentGroups
import com.tcreative.devtools.tranclate.addon.beh.entites.BehEntityComponents
import com.tcreative.devtools.tranclate.addon.beh.entites.data.Subject
import com.tcreative.devtools.tranclate.addon.beh.entites.events.BehEntityEvents
import com.tcreative.devtools.tranclate.addon.beh.spawnrules.PopulationControl
import com.tcreative.devtools.tranclate.addon.molang.Query
import com.tcreative.devtools.tranclate.builder.getEntityTextureResource
import com.tcreative.devtools.tranclate.builder.spawnRules
import com.tcreative.devtools.tranclate.systemaddon.entityapi.AddonEntity

fun loadTextures(addonEntity: AddonEntity) {
    addonEntity.resource {
        textures(
            arrayListOf(
                getEntityTextureResource("soldier_npc_0.png"),
                getEntityTextureResource("soldier_npc_1.png"),
                getEntityTextureResource("soldier_npc_2.png"),
                getEntityTextureResource("soldier_npc_3.png")
            ), Query.variant
        )
    }
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

fun soldierSpawnRules() {
    spawnRules("soldier_melee") {
        description("${Props.projectShort}:soldier_melee", PopulationControl.MONSTER)
        condition {
            spawnOnSurface()
            herd {
                maxSize(5)
                minSize(2)
            }
        }
    }
    spawnRules("soldier_range") {
        description("${Props.projectShort}:soldier_range", PopulationControl.MONSTER)
        condition {
            spawnOnSurface()
            herd {
                maxSize(3)
                minSize(1)
            }
        }
    }
}

fun sharedComponents(components: BehEntityComponents) {
    components.collisionBox {
        height = 1.8f
        width = 0.6f
    }
    components.physics { }
    components.pushable { }
    components.navigationWalk {
        avoidDamageBlocks = true
        avoidWater = true
        canPathOverWater = false
    }
    components.behEquipItem { }
    components.movement {
        value = 0.2f
    }
    components.jumpStatic { }
    components.movementBasic { }
    components.health {
        value = 20
    }
    components.despawn {
        despawnFromDistance()
    }
    components.scale {
        value = 1.05f
    }
    components.behNearestAttackableTarget {
        priority = 1
        entityTypes {
            type {
                filters {
                    isFamily(subject = Subject.OTHER, value = "monster")
                }
            }
        }
        mustReach = false
        mustSee = true
    }
    components.behHurtByTarget {
        priority = 0
    }
    components.behRandomStroll {
        priority = 4
    }
    components.behLookAtPlayer {
        priority = 5
        lookDistance = 20f
    }
    components.behHurtByTarget {
        priority = 0
    }
    components.behMoveToLand {
        priority = 2
    }
}

fun sharedResAnimControllers(animationControllers: AnimationControllers) {
    animationControllers.animController("general") {
        initialState = "default"
        animStates {
            animState("default") {
                animation = arrayListOf("base_pose", "default", "move")
            }
        }
    }
}