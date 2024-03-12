package com.tcreative.addons

import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.addon.addon
import com.lop.devtools.monstera.addon.dev.zipper.zipWorld
import com.lop.devtools.monstera.files.getResource
import com.tcreative.addons.soldier.soldierMelee
import com.tcreative.addons.soldier.soldierRange
import com.tcreative.addons.soldier.soldierSpawnRules
import com.tcreative.addons.vase.vase
import com.tcreative.addons.vase.vaseBlock

fun main(args: Array<String>) {
    val conf = Config(
        projectName = "Odyssey Demo",
        world = getResource("world/od_world"),
        version = arrayListOf(1, 1, 0),
        targetMcVersion = arrayListOf(1, 20, 60)
    ).apply {
        packIcon = getResource("general/pack.png")
    }

    addon(conf) {
        soldierRange(this)
        soldierMelee(this).also {
            it.behaviour {
                soldierSpawnRules()
            }
        }

        vase(this)
        vaseBlock()
    }

    if (args.contains("zip-world")) {
        //create a usable mcaddon or mcworld file
        zipWorld(conf, targetName = "odyssey")
    }
}