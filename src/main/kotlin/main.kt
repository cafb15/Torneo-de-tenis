import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val input = Scanner(System.`in`)
    println("Ingresa la cantidad de jugadores del torneo:")

    val quantityPlayers: Int = input.nextInt()

    when {
        quantityPlayers == 0 -> println("Un torneo tiene que tener jugadores")
        quantityPlayers % 2 != 0 -> println("Ingrese un número par")
        else -> startGame(getPlayers(quantityPlayers))
    }
}

fun getPlayers(quantity: Int): ArrayList<Player> {
    val players = ArrayList<Player>()

    for (i in 1..quantity) {
        players.add(Player("Jugador $i", (0..100).random()))
    }

    return players
}

fun startGame(players: ArrayList<Player>) {
    do {
        val quantityPlayers = players.size
        var playersForDelete = quantityPlayers / 2

        println()
        println("Jugadores del torneo: ${titleRound(quantityPlayers)}")

        players.forEach {
            println(it)
        }

        println()

        for (i in 0..quantityPlayers) {
            val playerOne = players[i]
            val playerTwo = players[i + 1]

            if (resultGame(playerOne.skill, playerTwo.skill)) {
                players.removeAt(i + 1)
            } else {
                players.removeAt(i)
            }

            playersForDelete--

            if (playersForDelete == 0) {
                break
            }
        }
    } while (players.size > 1)

    println("El ganador del torneo es ${players[0]}")
}

fun resultGame(skillOne: Int, skillTwo: Int): Boolean {
    val number = (0..100).random()

    var playerOne = skillOne
    var playerTwo = skillTwo

    do {
        playerOne += (0..number).random()
        playerTwo += (0..number).random()
    } while (playerOne == playerTwo)

    return playerOne > playerTwo
}

fun titleRound(quantityPlayers: Int): String {
    return when(quantityPlayers) {
        2 -> "Final"
        4 -> "Semininal"
        8 -> "Cuartos de final"
        16 -> "Octavos de final"
        else -> "Ronda $quantityPlayers"
    }
}