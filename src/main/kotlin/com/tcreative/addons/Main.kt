package com.tcreative.addons

import com.tcreative.addons.soldier.soldierMelee
import com.tcreative.addons.soldier.soldierRange
import com.tcreative.addons.soldier.soldierSpawnRules
import com.tcreative.devtools.tranclate.builder.getResource
import com.tcreative.devtools.tranclate.builder.zipper.zipProject
import com.tcreative.devtools.tranclate.systemaddon.addon

fun main() {
    addon(
        projectName = "Odyssey Demo",
        projectShort = "od",
        world = getResource("world/odyssey"),
        version = arrayListOf(1,1,0),
        packIcon = getResource("general/pack.png")
    ) {
        packageAddon = true
        soldierRange(this)
        soldierMelee(this)
        soldierSpawnRules()
    }

    //create a usable mcaddon or mcworld file
    zipProject("1.1", getResource("world/odyssey"))
}