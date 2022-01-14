package com.tcreative.addons

import com.tcreative.addons.soldier.soldierMelee
import com.tcreative.addons.soldier.soldierRange
import com.tcreative.devtools.tranclate.builder.getPackIconResource
import com.tcreative.devtools.tranclate.builder.getWorldResource
import com.tcreative.devtools.tranclate.builder.systemAddon
import com.tcreative.devtools.tranclate.builder.zipper.zipProject

fun main() {
    systemAddon(
        projectName = "Odyssey Demo",
        projectShort = "od",
        world = getWorldResource("template-world"),
        version = arrayListOf(1,0,0),
        packIcon = getPackIconResource("pack.png")
    ) {
        soldierRange(this)
        soldierMelee(this)
    }

    //create a usable mcaddon or mcworld file
    zipProject("1.0", getWorldResource("template-world"))
}