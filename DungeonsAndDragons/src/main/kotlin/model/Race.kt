package org.example.model

enum class Race(val description: String, val abilityBonuses: Map<String, Int>) {
    DWARF("Anão", mapOf("Constituição" to 2)),
    ELF("Elfo", mapOf("Destreza" to 2)),
    HUMAN("Humano", mapOf("Força" to 1, "Destreza" to 1, "Constituição" to 1, "Inteligência" to 1, "Sabedoria" to 1, "Carisma" to 1)),
    HALFLING("Halfling", mapOf("Destreza" to 2));

    fun applyRacialBonuses(abilities: Abilities) {
        abilityBonuses.forEach { (ability, bonus) ->
            when (ability) {
                "Força" -> abilities.strength.score += bonus
                "Destreza" -> abilities.dexterity.score += bonus
                "Constituição" -> abilities.constitution.score += bonus
                "Inteligência" -> abilities.intelligence.score += bonus
                "Sabedoria" -> abilities.wisdom.score += bonus
                "Carisma" -> abilities.charisma.score += bonus
            }
        }
    }
}