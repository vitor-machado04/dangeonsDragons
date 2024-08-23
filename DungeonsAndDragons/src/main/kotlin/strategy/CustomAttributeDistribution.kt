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
            remainingPoints -= currentCost // vitor - subtraí do total de pontos restantes

            while (true) {
                println("-------------------------------------")
                println("Atribua pontos para $attributeName (atualmente $score, custo $currentCost, mínimo 8, máximo 15).")
                println("-------------------------------------")
                val input = readLine()?.toIntOrNull()

                if (input == null || input < 8 || input > 15) {
                    println("Erro: Valor inválido. Os pontos devem estar entre 8 e 15.")
                    continue
                }

                val newCost = calculateCost(input) // vitor - nova pontuação
                val costDifference = newCost - currentCost // vitor - diferença do custo da pont. atual e a nova

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
            scores[attributeName] = score // vitor - valores salvos no mapa
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

