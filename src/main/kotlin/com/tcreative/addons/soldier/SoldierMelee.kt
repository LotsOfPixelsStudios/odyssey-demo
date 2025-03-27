package com.tcreative.addons.soldier

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.addon.molang.and
import com.lop.devtools.monstera.files.getResource
import com.tcreative.addons.Odyssey
import com.tcreative.addons.soldier.components.loadSoldierAnimations
import com.tcreative.addons.soldier.components.loadVariants
import com.tcreative.addons.soldier.components.soldierComponents
import com.tcreative.addons.soldier.components.soldierSpawnRule

fun Addon.soldierMelee() {
    entity("soldier_melee", "§cSoldier (Melee)§r") {
        loadVariants()
        loadSoldierAnimations()
        soldierSpawnRule()
        soldierComponents()
        resource {
            geometryLayer(getResource("entity/geometries/soldier_npc.geo.json"))
            components {
                spawnEgg("§cSpawn Soldier (Melee)§r", Odyssey.hostileSpawnEgg)
                scripts {
                    preAnimationEntry("variable.tcos0 = (Math.cos(query.modified_distance_moved * 38.17) * query.modified_move_speed / variable.gliding_speed_value) * 57.3;")
                }
            }
            animationController("attack_controller") {
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
            components {
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
        }
    }
}