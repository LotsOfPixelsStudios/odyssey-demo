package com.tcreative.addons.soldier

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.addon.molang.and
import com.lop.devtools.monstera.files.getResource

fun soldierMelee(systemAddon: Addon) {
    systemAddon.entity("soldier_melee", "Soldier") {
        loadTextures(this)
        resource {
            animation(getResource("entity/animations/soldier_npc.animation.json"))
            geometryLayer(getResource("entity/geometries/soldier_npc.geo.json"))
            components {
                scripts {
                    preAnim(arrayListOf("variable.tcos0 = (Math.cos(query.modified_distance_moved * 38.17) * query.modified_move_speed / variable.gliding_speed_value) * 57.3;"))
                }
            }
            sharedResAnimControllers(this)
            animationController("attack_debug") {
                initialState = "default"
                animStates {
                    animState("default") {
                        transitions { transition("attack") { Query.isDelayedAttacking } }
                    }
                    animState("attack") {
                        animation = arrayListOf("attack")
                        transitions {
                            transition("default") {
                                !Query.isDelayedAttacking and Query.allAnimationsFinished
                            }
                        }
                    }
                }
            }
        }

        behaviour {
            componentGroups { loadTextureCompGroups(this) }
            components {
                sharedComponents(this)
                typeFamily(arrayListOf("mob", "addon"))
                delayedAttack {
                    priority = 1
                    speedMultiplier = 1.5f
                    reachMultiplier = 3.5f
                }
                attack {
                    damage = 7
                }
                equipItem()
                equipment {
                    table(addon, "soldier_melee") {
                        pool(rolls = 1) {
                            entry(type = "item", name = "minecraft:golden_sword", weight = 1) {

                            }
                        }
                    }
                }
            }
            events { spawnEvent(this) }
        }
    }
}