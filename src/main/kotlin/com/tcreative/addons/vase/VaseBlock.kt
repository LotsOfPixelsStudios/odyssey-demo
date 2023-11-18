package com.tcreative.addons.vase

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.getResource

fun Addon.vaseBlock() {
    block("vase_1", "Small Vase") {
        geometry(getResource("vase/vase1.geo.json"))
        texture(getResource("vase/vase1_1.png"))
    }
}