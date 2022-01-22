package com.tcreative.addons.soldier

import com.tcreative.devtools.tranclate.addon.molang.Query
import com.tcreative.devtools.tranclate.addon.molang.and
import com.tcreative.devtools.tranclate.builder.getEntityAnimationResource
import com.tcreative.devtools.tranclate.builder.getEntityGeometryResource
import com.tcreative.devtools.tranclate.systemaddon.SystemAddon

fun soldierMelee(systemAddon: SystemAddon) {
    systemAddon.entity {
        name("soldier_melee", "Soldier")
        loadTextures(this)
        resAnimations(getEntityAnimationResource("soldier_npc.animation.json"))
        geometry(getEntityGeometryResource("soldier_npc.geo.json"))
        resScripts {
            preAnim(arrayListOf("variable.tcos0 = (Math.cos(query.modified_distance_moved * 38.17) * query.modified_move_speed / variable.gliding_speed_value) * 57.3;"))
        }
        resAnimController {
            sharedResAnimControllers(this)
            animController("attack") {
                initialState("default")
                animStates {
                    animState("default") {
                        transitions { transition("attack", Query.isDelayedAttacking) }
                    }
                    animState("attack") {
                        animation(arrayListOf("attack"))
                        transitions {
                            transition(
                                "default",
                                Query.allAnimationsFinished and "!${Query.isDelayedAttacking}"
                            )
                        }
                    }
                }
            }
        }

        componentGroups { loadTextureCompGroups(this) }
        components {
            sharedComponents(this)
            typeFamily(arrayListOf("mob", "addon"))
            delayedAttack {
                priority(1)
                speedMultiplier(1.5f)
                reachMultiplier(2.0f)
            }
            attack(5)
            equipment {
                table("soldier_melee") {
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