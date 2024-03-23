package com.tcreative.addons.soldier.components

import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.files.getResource

fun Entity.loadSoldierAnimations() {
    resource {
        animation(getResource("entity/animations/soldier_npc.animation.json"))
        animationController("general") {
            initialState = "default"
            state("default") {
                animations("base_pose", "default", "move")
            }
        }
    }
}