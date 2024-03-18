package com.tcreative.addons.soldier

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.getResource
import com.tcreative.addons.soldier.components.loadSoldierAnimations
import com.tcreative.addons.soldier.components.loadVariants
import com.tcreative.addons.soldier.components.soldierComponents
import java.awt.Color

fun Addon.soldierRange() {
    entity("soldier_range", "Soldier") {
        loadVariants()
        loadSoldierAnimations()
        soldierComponents()
        resource {
            geometryLayer(getResource("entity/geometries/soldier_npc.geo.json"))
            components {
                spawnEgg {
                    eggByColor(Color.BLACK, Color.BLUE)
                }
                scripts {
                    preAnimationEntry("variable.tcos0 = (Math.cos(query.modified_distance_moved * 38.17) * query.modified_move_speed / variable.gliding_speed_value) * 57.3;")
                }
            }
        }
        behaviour {
            components {
                typeFamily {
                    familyData = arrayListOf("mob", "addon")
                }
                behRangedAttack {
                    priority = 1
                    attackRadius = 15
                }
                shooter {
                    def = "arrow"
                }
                attack {
                    damage = 5
                }
                equipment {
                    table("soldier_range") {
                        pool {
                            rolls(1)
                            entry {
                                type = "item"
                                identifier = "bow"
                                weight = 1
                            }
                        }
                    }
                }
                loot {
                    table("soldier_range") {
                        pool {
                            entry {
                                type = "item"
                                identifier = "arrow"
                                weight = 10
                                functions {
                                    functionSetCount(4)
                                }
                            }
                            entry {
                                type = "item"
                                identifier = "bone"
                                weight = 8
                                functions {
                                    functionSetCount(2)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}