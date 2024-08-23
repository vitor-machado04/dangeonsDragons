package org.example

import org.example.model.CharClass
import org.example.model.Race
import org.example.model.Character
import org.example.strategy.CustomAttributeDistribution

fun main() {
    println("Bem-vindo ao Criador de Personagens de D&D!")

    print("Digite o nome do seu personagem: ")
    val name = readLine()!!

    println("Escolha a raça do seu personagem:")
    Race.values().forEachIndexed { index, race -> println("${index + 1}. ${race.description}") }
    val raceChoice = readLine()?.toIntOrNull() ?: 1
    val race = Race.values().getOrNull(raceChoice - 1) ?: Race.HUMAN

    println("Escolha a classe do seu personagem:")
    CharClass.values().forEachIndexed { index, charClass -> println("${index + 1}. ${charClass.description}") }
    val classChoice = readLine()?.toIntOrNull() ?: 1
    val charClass = CharClass.values().getOrNull(classChoice - 1) ?: CharClass.WARRIOR

    val distributionStrategy = CustomAttributeDistribution()

    val character = Character(
        name = name,
        race = race,
        charClass = charClass,
        distributionStrategy = distributionStrategy
    )

    println("\nSeu personagem foi criado com sucesso!")
    println(character)
}