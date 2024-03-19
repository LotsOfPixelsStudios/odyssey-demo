package com.tcreative.addons.soldier.components

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject
import com.lop.devtools.monstera.files.res.sounds.SoundCategory
import com.lop.devtools.monstera.files.res.sounds.SoundEvent

fun Entity.soldierComponents() {
    addon.sounds {
        soundsDefinitions {
            newSoundDef("mob.${getIdentifier()}.ambient") {
                category = SoundCategory.NEUTRAL
                sound("sounds/mob/villager/yes1")
                sound("sounds/mob/villager/yes2")
                sound("sounds/mob/villager/yes3")
                sound("sounds/mob/villager/idle1")
                sound("sounds/mob/villager/idle2")
                sound("sounds/mob/villager/idle3")
                sound("sounds/mob/villager/haggle1")
            }
        }
        categorySounds {
            individualEventSounds {
                entitySounds {
                    soundEventGroup(getIdentifier()) {
                        event(SoundEvent.AMBIENT) {
                            sound("mob.villager.haggle")
                            pitch(0.8)
                        }
                        event(SoundEvent.HIT) {
                            sound("mob.villager.hit")
                            pitch(0.9)
                        }
                    }
                }
            }
        }
    }
    behaviour {
        components {
            ambientSoundInterval {
                value = 2
                range = 4
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
            behEquipItem {
                priority = 2
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
                entityTypes {
                    type {
                        sprintSpeedMultiplier = 1.2f
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