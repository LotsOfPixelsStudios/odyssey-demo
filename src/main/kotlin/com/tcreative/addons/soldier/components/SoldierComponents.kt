package com.tcreative.addons.soldier.components

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

fun Entity.soldierComponents() {
    behaviour {
        components {
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
                despawnFromDistance { }
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
}