package com.tcreative.addons.soldier

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.entity.resource.ResourceEntity
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.beh.entitiy.BehEntityComponentGroups
import com.lop.devtools.monstera.files.beh.entitiy.BehEntityComponents
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject
import com.lop.devtools.monstera.files.beh.entitiy.events.BehEntityEvents
import com.lop.devtools.monstera.files.beh.spawnrules.BehSpawnRules
import com.lop.devtools.monstera.files.beh.spawnrules.PopulationControl
import com.lop.devtools.monstera.files.getResource

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

fun Addon.soldierSpawnRules() {
    val namespace = config.namespace
    BehSpawnRules().apply {
        description("$namespace:soldier_melee", PopulationControl.MONSTER)
        condition {
            spawnOnSurface()
            herd {
                maxSize(5)
                minSize(2)
            }
        }
    }.unsafe.build("soldier_melee", config.paths.behSpawnRules)
    BehSpawnRules().apply {
        description("$namespace:soldier_range", PopulationControl.MONSTER)
        condition {
            spawnOnSurface()
            herd {
                maxSize(3)
                minSize(1)
            }
        }
    }.unsafe.build("soldier_range", config.paths.behSpawnRules)
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