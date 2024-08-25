package org.example.strategy

import org.example.model.Abilities
import org.example.model.Ability

class CustomAttributeDistribution : AttributeDistribution {
    override fun distributeAttributes(points: Int): Abilities {
        var remainingPoints = points
        val scores = mutableMapOf<String, Int>()
        val attributes = listOf("Força", "Destreza", "Constituição", "Inteligência", "Sabedoria", "Carisma")

        println("Você tem $remainingPoints pontos para distribuir entre os atributos.")

        attributes.forEach { attributeName ->
            var score = 8
            var currentCost = calculateCost(score)
            remainingPoints -= currentCost // vitor - Subtrai do total de pontos restantes

            while (true) {
                println("-------------------------------------")
                println("Atribua pontos para $attributeName (atualmente $score, custo $currentCost, mínimo 8, máximo 15).")
                println("-------------------------------------")
                val input = readLine()?.toIntOrNull()

                if (input == null || input < 8 || input > 15) {
                    println("Erro: Valor inválido. Os pontos devem estar entre 8 e 15.")
                    continue
                }

                val newCost = calculateCost(input)
                val costDifference = newCost - currentCost

                if (input > score && remainingPoints < costDifference) {
                    println("Erro: Não há pontos suficientes para atribuir este valor. Pontos restantes: $remainingPoints")
                }
                else if (input >= 8 && input <= 15 && (input == score || remainingPoints >= costDifference)) {
                    remainingPoints -= costDifference
                    score = input
                    currentCost = newCost
                    println("Pontos restantes: $remainingPoints")
                    break
                }
                else {
                    println("Erro: Valor inválido ou insuficiente de pontos. Tente novamente.")
                }
            }
            scores[attributeName] = score // vitor - Salva os valores no mapa
        }

        while (remainingPoints > 0) {
            println("\nAinda há $remainingPoints pontos sobrando.")
            println("Deseja alterar algum atributo para usar os pontos restantes? (sim/não)")
            val continueInput = readLine()?.trim()?.lowercase()

            if (continueInput == "não" || continueInput == "n") {
                println("Erro: Não é possível finalizar a criação do personagem com pontos sobrando.")
            } else if (continueInput == "sim" || continueInput == "s") {
                attributes.forEach { attributeName ->
                    println("$attributeName: ${scores[attributeName]}")
                }
                println("Digite o nome do atributo que você quer alterar:")
                val attributeToChange = readLine()?.trim()

                if (attributeToChange != null && scores.containsKey(attributeToChange)) {
                    val currentScore = scores[attributeToChange] ?: 8
                    val currentCost = calculateCost(currentScore)

                    println("Valor atual de $attributeToChange é $currentScore.")
                    println("Digite o novo valor (mínimo 8, máximo 15):")
                    val newScore = readLine()?.toIntOrNull()

                    if (newScore != null && newScore in 8..15) {
                        val newCost = calculateCost(newScore)
                        val costDifference = newCost - currentCost

                        if (newScore > currentScore && remainingPoints < costDifference) {
                            println("Erro: Não há pontos suficientes para atribuir este valor.")
                        }
                        else {
                            remainingPoints -= costDifference
                            scores[attributeToChange] = newScore
                            println("Pontos restantes: $remainingPoints")
                        }
                    }
                    else {
                        println("Erro: Valor inválido. Deve ser entre 8 e 15.")
                    }
                }
                else {
                    println("Erro: Atributo não encontrado.")
                }
            }
        }

        return Abilities(
            strength = Ability(scores["Força"] ?: 10),
            dexterity = Ability(scores["Destreza"] ?: 10),
            constitution = Ability(scores["Constituição"] ?: 10),
            intelligence = Ability(scores["Inteligência"] ?: 10),
            wisdom = Ability(scores["Sabedoria"] ?: 10),
            charisma = Ability(scores["Carisma"] ?: 10)
        )
    }

    private fun calculateCost(score: Int): Int = when (score) {
        8 -> 0
        9 -> 1
        10 -> 2
        11 -> 3
        12 -> 4
        13 -> 5
        14 -> 7
        15 -> 9
        else -> throw IllegalArgumentException("Score fora do intervalo permitido")
    }
}
