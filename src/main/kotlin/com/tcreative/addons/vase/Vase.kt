package com.tcreative.addons.vase

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.getResource
import com.lop.devtools.stdlib.furnitures.furniture
import com.tcreative.addons.Odysee

fun vase(addon: Addon) {
    furniture("vase", "Vase", addon) {
        texture = getResource("vase/vase2.png")
        geometry = getResource("vase/vase2.geo.json")
        icon {
            eggByFile(getResource("vase/vase2_item.png"), addon)
        }
        height = 0.6f
        width = 0.2f

        resource {
            animation(getResource("vase/vase2.animation.json"))
        }
        breakable {
            animation("rot0_break", 1f)
            loot(1f) {
                pool {
                    rolls(3)
                    entry {
                        type = "item"
                        identifier = "stick"
                        weight = 1
                        functions {
                            functionSetCounts(1, 2)
                        }
                    }
                    entry {
                        type = "item"
                        identifier = "bone"
                        weight = 3
                        functions {
                            functionSetCounts(3, 4)
                        }
                    }
                    entry {
                        type = "item"
                        identifier = "apple"
                        weight = 2
                        functions {
                            functionSetCounts(1, 2)
                        }
                    }
                    entry {
                        type = "item"
                        identifier = "bread"
                        weight = 3
                        functions {
                            functionSetCounts(4, 4)
                        }
                    }
                    entry {
                        type = "item"
                        identifier = "iron_ingot"
                        weight = 3
                        functions {
                            functionSetCounts(3, 8)
                        }
                    }
                    entry {
                        type = "item"
                        identifier = "stone_sword"
                        weight = 1
                    }
                }
            }
        }
    }
}