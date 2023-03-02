package com.tcreative.addons.soldier

import com.tcreative.devtools.tranclate.Props.namespace
import com.tcreative.devtools.tranclate.addon.beh.entites.BehEntityComponentGroups
import com.tcreative.devtools.tranclate.addon.beh.entites.BehEntityComponents
import com.tcreative.devtools.tranclate.addon.beh.entites.data.Subject
import com.tcreative.devtools.tranclate.addon.beh.entites.events.BehEntityEvents
import com.tcreative.devtools.tranclate.addon.beh.spawnrules.PopulationControl
import com.tcreative.devtools.tranclate.addon.molang.Query
import com.tcreative.devtools.tranclate.builder.getResource
import com.tcreative.devtools.tranclate.builder.spawnRules
import com.tcreative.devtools.tranclate.systemaddon.entityapi.AddonEntity
import com.tcreative.devtools.tranclate.systemaddon.entityapi.Entity
import com.tcreative.devtools.tranclate.systemaddon.entityapi.resource.AddonEntityRes
import com.tcreative.devtools.tranclate.systemaddon.entityapi.resource.ResourceEntity

fun loadTextures(addonEntity: Entity) {
    with(addonEntity) {
        resource {
            textureLayer(
                arrayListOf(
                    getResource("entity/textures/soldier_npc_0.png"),
                    getResource("entity/textures/soldier_npc_1.png"),
                    getResource("entity/textures/soldier_npc_2.png"),
                    getResource("entity/textures/soldier_npc_3.png")
                ), { Query.variant }
            )
        }
    }
}

fun loadTextureCompGroups(behEntityComponentGroups: BehEntityComponentGroups) {
    with(behEntityComponentGroups) {
        for (i in 0..3)
            componentGroup("texture_variant_$i") {
                variant(i)
            }
    }

}

fun spawnEvent(behEntityEvents: BehEntityEvents) {
    with(behEntityEvents) {
        defaultBornEvent {
            randomize {
                for (i in 0..3)
                    randomComp {
                        weight = 1
                        add {
                            componentGroup = "texture_variant_$i"
                        }
                    }
            }
        }
    }
}

fun soldierSpawnRules() {
    spawnRules("soldier_melee") {
        description("$namespace:soldier_melee", PopulationControl.MONSTER)
        condition {
            spawnOnSurface()
            herd {
                maxSize(5)
                minSize(2)
            }
        }
    }
    spawnRules("soldier_range") {
        description("$namespace:soldier_range", PopulationControl.MONSTER)
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
    with(components) {
        collisionBox {
            height = 1.8f
            width = 0.6f
        }
        physics { }
        pushable { }
        navigationWalk {
            avoidDamageBlocks = true
            avoidWater = true
            canPathOverWater = false
        }
        behEquipItem { }
        movement {
            value = 0.2f
        }
        jumpStatic { }
        movementBasic { }
        health {
            value = 20
        }
        despawn {
            despawnFromDistance()
        }
        scale {
            value = 1.05f
        }
        behNearestAttackableTarget {
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
        behHurtByTarget {
            priority = 0
        }
        behRandomStroll {
            priority = 4
        }
        behLookAtPlayer {
            priority = 5
            lookDistance = 20f
        }
        behHurtByTarget {
            priority = 0
        }
        behMoveToLand {
            priority = 2
        }
    }
}

fun sharedResAnimControllers(ent: ResourceEntity) {
    with(ent) {
        animationController("general") {
            initialState = "default"
            animStates {
                animState("default") {
                    animation = arrayListOf("base_pose", "default", "move")
                }
            }
        }
    }
}