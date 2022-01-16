package com.tcreative.addons

import com.tcreative.addons.soldier.soldierMelee
import com.tcreative.addons.soldier.soldierRange
import com.tcreative.devtools.tranclate.builder.addon
import com.tcreative.devtools.tranclate.builder.getPackIconResource
import com.tcreative.devtools.tranclate.builder.getWorldResource
import com.tcreative.devtools.tranclate.builder.zipper.zipProject

fun main() {
    addon(
        projectName = "Odyssey Demo",
        projectShort = "od",
        world = getWorldResource("odyssey"),
        version = arrayListOf(1,0,0),
        packIcon = getPackIconResource("pack.png")
    ) {
        soldierRange(this)
        soldierMelee(this)
    }

    //create a usable mcaddon or mcworld file
    zipProject("1.0", getWorldResource("odyssey"))
}