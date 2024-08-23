package org.example.model

enum class CharClass(val hitDice: Int, val description: String) {
    WARRIOR(10, "Guerreiro"),  // Guerreiros normalmente usam um d10 para PV
    MAGE(6, "Mago"),           // Magos normalmente usam um d6 para PV
    ROGUE(8, "Ladino"),        // Ladinos normalmente usam um d8 para PV
    CLERIC(8, "Clérigo")       // Clérigos normalmente usam um d8 para PV
}