package org.example.model

class Ability(var score: Int) {
    val modifier: Int
        get() = (score - 10) / 2
}

class Abilities(
    val strength: Ability,
    val dexterity: Ability,
    val constitution: Ability,
    val intelligence: Ability,
    val wisdom: Ability,
    val charisma: Ability
)