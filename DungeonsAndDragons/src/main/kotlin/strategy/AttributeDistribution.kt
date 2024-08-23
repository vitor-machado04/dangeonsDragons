package org.example.strategy

import org.example.model.Abilities

interface AttributeDistribution {
    fun distributeAttributes(points: Int): Abilities
}
