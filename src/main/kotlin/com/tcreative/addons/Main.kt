package com.tcreative.addons

import com.lop.devtools.monstera.addon.addon
import com.lop.devtools.monstera.addon.dev.zipper.zipWorld
import com.lop.devtools.monstera.config
import com.lop.devtools.monstera.files.getResource
import com.tcreative.addons.soldier.soldierMelee
import com.tcreative.addons.soldier.soldierRange
import com.tcreative.addons.soldier.soldierSpawnRules
import com.tcreative.addons.vase.vase

fun main(args: Array<String>) {
    val config = config("Odyssey Demo") {
        projectShort = "od"
        world = getResource("world/od_world")
        version = arrayListOf(1, 2, 0)
        packIcon = getResource("general/pack.png")
    }

    addon(config) {
        manifestMinEnginVersion = arrayListOf(1, 18, 0)

        soldierRange(this)
        soldierMelee(this)
        soldierSpawnRules()

        vase(this)
    }

    if (args.contains("zip-world")) {
        //create a usable mcaddon or mcworld file
        zipWorld(
            config,
            targetName = "odyssey"
        )
    }
}