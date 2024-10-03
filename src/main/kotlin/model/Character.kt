package org.example.model

import org.example.strategy.AttributeDistribution

class Character(
    val name: String,
    val race: Race,
    val charClass: CharClass,
    val abilities: Abilities
) {
    var level: Int = 1
    val hitPoints: Int = calculateInitialHitPoints()

    private fun calculateInitialHitPoints(): Int {
        return charClass.hitDice + abilities.constitution.modifier
    }

    override fun toString(): String {
        return """
            |Nome: $name
            |Raça: ${race.description}
            |Classe: ${charClass.description}
            |Nível: $level
            |Pontos de Vida: $hitPoints
            |Força: ${abilities.strength.score} (Modificador: ${abilities.strength.modifier})
            |Destreza: ${abilities.dexterity.score} (Modificador: ${abilities.dexterity.modifier})
            |Constituição: ${abilities.constitution.score} (Modificador: ${abilities.constitution.modifier})
            |Inteligência: ${abilities.intelligence.score} (Modificador: ${abilities.intelligence.modifier})
            |Sabedoria: ${abilities.wisdom.score} (Modificador: ${abilities.wisdom.modifier})
            |Carisma: ${abilities.charisma.score} (Modificador: ${abilities.charisma.modifier})
        """.trimMargin()
    }
}
