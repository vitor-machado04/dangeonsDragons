package org.example.strategy

import org.example.model.Abilities
import org.example.model.Ability

class CustomAttributeDistribution : AttributeDistribution {
    override fun distributeAttributes(points: Int): Abilities {

        return Abilities(
            strength = Ability(10),
            dexterity = Ability(10),
            constitution = Ability(10),
            intelligence = Ability(10),
            wisdom = Ability(10),
            charisma = Ability(10)
        )
    }
}
