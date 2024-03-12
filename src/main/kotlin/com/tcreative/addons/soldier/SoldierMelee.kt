package com.tcreative.addons.soldier

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.addon.molang.and
import com.lop.devtools.monstera.files.getResource

fun soldierMelee(systemAddon: Addon): Entity {
    return systemAddon.entity("soldier_melee", "Soldier") {
        loadTextures(this)
        resource {
            animation(getResource("entity/animations/soldier_npc.animation.json"))
            geometryLayer(getResource("entity/geometries/soldier_npc.geo.json"))
            components {
                scripts {
                    preAnimationEntry("variable.tcos0 = (Math.cos(query.modified_distance_moved * 38.17) * query.modified_move_speed / variable.gliding_speed_value) * 57.3;")
                }
            }
            sharedResAnimControllers(this)
            animationController("attack") {
                initialState = "default"
                state("default") {
                    transition("attack", Query.isDelayedAttacking)
                }
                state("attack") {
                    transition("default", !Query.isDelayedAttacking and Query.allAnimationsFinished)
                    animations("attack")
                }
            }
        }

        behaviour {
            loadTextureCompGroups()
            components {
                sharedComponents(this)
                typeFamily {
                    familyData = arrayListOf("mob", "addon")
                }
                behDelayedAttack {
                    priority = 1
                    speedMultiplier = 1.5f
                    reachMultiplier = 3.5f
                }
                attack {
                    damage = 7
                }
                equipment {
                    table("soldier_melee") {
                        pool {
                            rolls(1)
                            entry {
                                type = "item"
                                identifier = "minecraft:golden_sword"
                                weight = 1
                            }
                        }
                    }
                }
            }
            spawnEvent()
        }
    }
}