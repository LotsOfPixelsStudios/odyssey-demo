package com.tcreative.addons.vase

import com.tcreative.devtools.tranclate.builder.getResource
import com.tcreative.devtools.tranclate.systemaddon.Addon

fun Addon.vaseBlock() {
    block {
        name("vase_1", "Small Vase")
        geometry(getResource("vase/vase1.geo.json"))
        texture(getResource("vase/vase1_1.png"))
    }
}