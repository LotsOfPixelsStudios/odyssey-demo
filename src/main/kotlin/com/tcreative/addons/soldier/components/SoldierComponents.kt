package com.tcreative.addons.soldier.components

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.sound.Sound
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject
import com.lop.devtools.monstera.files.getResource
import com.lop.devtools.monstera.files.res.sounds.SoundEvent

fun Entity.soldierComponents() {
    behaviour {
        sound("mob.$name.ambient") {
            onEvent(SoundEvent.AMBIENT)
            sound(arrayListOf(
                getResource("sounds/haggle1.fsb") to Sound.SoundDefData(),
                getResource("sounds/haggle2.fsb") to Sound.SoundDefData(),
                getResource("sounds/idle1.fsb") to Sound.SoundDefData(),
                getResource("sounds/idle2.fsb") to Sound.SoundDefData(),
                getResource("sounds/idle3.fsb") to Sound.SoundDefData(),
            ))
        }
        sound("mob.$name.hit") {
            onEvent(SoundEvent.HURT)
            importSound("mob.villager.hit")
            pitch = 0.9 to 0.9
        }
        components {
            ambientSoundInterval {
                value = 3
                range = 6
                eventName = "ambient"
            }
            collisionBox {
                height = 1.8
                width = 0.6
            }
            physics { }
            pushable { }
            navigationWalk {
                avoidDamageBlocks = true
                avoidWater = true
                canPathOverWater = false
            }
            movement {
                value = 0.2
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
                entityType {
                    sprintSpeedMultiplier = 1.2f
                    filters {
                        isFamily(subject = Subject.OTHER, value = "monster")
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