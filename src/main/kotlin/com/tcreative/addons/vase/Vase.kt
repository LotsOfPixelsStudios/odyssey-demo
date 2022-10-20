package com.tcreative.addons.vase

import com.tcreative.devtools.stdlib.furnitures.furniture
import com.tcreative.devtools.tranclate.builder.getResource
import com.tcreative.devtools.tranclate.systemaddon.Addon

fun vase(addon: Addon) {
    furniture("vase", "Vase", addon) {
        texture = getResource("vase/vase2.png")
        geometry = getResource("vase/vase2.geo.json")
        icon {
            eggByFile(getResource("vase/vase2_item.png"))
        }
        height = 0.6f
        width = 0.2f

        resource {
            animation(getResource("vase/vase2.animation.json"))
        }
        breakable {
            animation("rot0_break", 1f)
            loot(1f) {
                pool(rolls = 3) {
                    entry(type = "item", name = "stick", 1) {
                        functionSetCounts(1, 2)
                    }
                    entry(type = "item", name = "bone", 3) {
                        functionSetCounts(3, 4)
                    }
                    entry(type = "item", name = "apple", 2) {
                        functionSetCounts(1, 2)
                    }
                    entry(type = "item", name = "bread", 3) {
                        functionSetCounts(4, 4)
                    }
                    entry(type = "item", name = "stone_sword", 1) {}
                    entry(type = "item", name = "iron_ingot", 3) {
                        functionSetCounts(3, 8)
                    }
                }
            }
        }
    }
}