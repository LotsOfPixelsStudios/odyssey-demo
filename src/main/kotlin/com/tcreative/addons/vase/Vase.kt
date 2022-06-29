package com.tcreative.addons.vase

import com.tcreative.devtools.stdlib.furnitures.GenericFurniture
import com.tcreative.devtools.tranclate.builder.getResource
import com.tcreative.devtools.tranclate.systemaddon.SystemAddon

fun vase(addon: SystemAddon) {
    GenericFurniture("vase", "Vase", addon) {
        texture = getResource("vase/vase2.png")
        geometry = getResource("vase/vase2.geo.json")
        iconTexture = getResource("vase/vase2_item.png")
        height = 0.6f
        width = 0.2f

        resource {
            animations(getResource("vase/vase2.animation.json"))
        }
        breakable {
            animation("rot0_break", 5f)
        }
    }
}