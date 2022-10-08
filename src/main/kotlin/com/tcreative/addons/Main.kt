package com.tcreative.addons

import com.tcreative.addons.soldier.soldierMelee
import com.tcreative.addons.soldier.soldierRange
import com.tcreative.addons.soldier.soldierSpawnRules
import com.tcreative.addons.vase.vase
import com.tcreative.devtools.tranclate.builder.getResource
import com.tcreative.devtools.tranclate.builder.zipper.zipWorld
import com.tcreative.devtools.tranclate.systemaddon.addon

fun main(args: Array<String>) {
    val prop = addon(
        projectName = "Odyssey Demo",
        projectShort = "od",
        world = getResource("world/odyssey"),
        version = arrayListOf(1, 1, 0),
        packIcon = getResource("general/pack.png")
    ) {
        manifestMinEnginVersion = arrayListOf(1, 18, 0)

        soldierRange(this)
        soldierMelee(this)
        soldierSpawnRules()

        vase(this)
    }

    if (args.contains("zip-world")) {
        //create a usable mcaddon or mcworld file
        zipWorld(
            getResource("world/odyssey"),
            properties = prop,
            targetName = "odyssey"
        )
    }
}