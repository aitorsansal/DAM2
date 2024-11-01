package cat.institutmontivi.navegacioniuadaambtipussegurs.dades

import kotlin.random.Random

object FakeDataSource {

    val llistaA: List<Int> = (1..100).toList()
    val llistaB: List<Char> = ('A'..'Z').toList()
    val llistaC: List<String>  =  (1..100).toList()
        .map {
            val element = StringBuilder()
            val lletres = ('A'..'Z').toList()
            for (num in 1..(Random.nextInt(5) + 1))
            {
                element.append(lletres[Random.nextInt(26)].toString())
            }
            element.toString()
        }

}