package com.tcreative.addons.soldier.components

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.files.beh.spawnrules.PopulationControl

fun Entity.soldierSpawnRule() {
    behaviour {
        spawnRule {
            populationControl(PopulationControl.MONSTER)
            condition {
                spawnsOnSurface()
                herd {
                    maxSize = 5
                    minSize = 2
                }
                weight(70)
                permuteType {
                    weight = 30
                    entityType = addon.config.namespace + ":soldier_range"
                }
                permuteType {
                    weight = 70
                    entityType = addon.config.namespace + ":soldier_melee"
                }
            }
        }
    }
}