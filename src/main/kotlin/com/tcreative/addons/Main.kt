package com.tcreative.addons

import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.addon.addon
import com.lop.devtools.monstera.addon.dev.zipper.zipWorld
import com.lop.devtools.monstera.files.getResource
import com.lop.devtools.monstera.loadConfig
import com.tcreative.addons.soldier.soldierMelee
import com.tcreative.addons.soldier.soldierRange
import com.tcreative.addons.vase.vase
import com.tcreative.addons.vase.vaseBlock

fun main(args: Array<String>) {
    val conf = addon(loadConfig().getOrElse {
        it.printStackTrace()
        return
    }) {
        config.packIcon = getResource("general/pack.png")

        soldierRange()
        soldierMelee()

        vase(this)
        vaseBlock()
    }

    if (args.contains("zip-world")) {
        //create a usable mcaddon or mcworld file
        zipWorld(conf, targetName = "odyssey")
    }
}