package com.tcreative.addons

import com.lop.devtools.monstera.files.res.entities.comp.ResEntitySpawnEgg
import java.awt.Color

object Odyssey {
    private val orange = Color(241, 144, 101)
    private val orangeVariant = Color(243, 166, 131)
    
    private val blue = Color(85, 110, 230)
    private val blueVariant = Color(119, 139, 235)
    
    val friendlySpawnEgg: ResEntitySpawnEgg.() -> Unit = {
        eggByColor(orange, blueVariant)
    }
    
    val hostileSpawnEgg: ResEntitySpawnEgg.() -> Unit = {
        eggByColor(blue, orangeVariant)
    }
}