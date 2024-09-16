import java.util.*

fun main() {
    EX1()
}

fun EX1(){
    val sc = System.`in`
    println("Diga'm una frase acabada amb punt per contar els seus caràcters:")
    var char = sc.read().toChar()
    var counter = 0
    while(char != '.')
    {
        counter++
        char = sc.read().toChar()
    }
    println("Hi ha $counter caràcters abans del punt.")
}

fun EX2(){
    val sc = System.`in`
    println("Diga'm una frase acabada amb punt per contar els seus caràcters a partir de la primera \"a\":")
    var char = sc.read().toChar()
    var counter = 0
    var count = false
    while(char != '.')
    {
        if(char == 'a' || char == 'A')
            count = true
        if(count)
            counter++
        char = sc.read().toChar()
    }
    println("Hi ha $counter caràcters abans del punt.")
}

fun EX3(){
    val sc = Scanner(System.`in`)
    var first = sc.nextInt()
    var second = sc.nextInt()
    var cont: Boolean
    do {
        cont = first < second
        first = second
        second = sc.nextInt()
    }
    while(cont && second != 0)
    if(cont)
        println("La sequència és creixent")
    else
        println("La sequència no és creixent")

}

fun EX4(){
    val sc = Scanner(System.`in`)
    var value = sc.nextInt()
    var cont = value > 0
    while(value != 0 && cont)
    {
        cont = value > 0
        value = sc.nextInt()
    }
    if(cont)
        println("tots positius")
    else
        println("hi ha negatius")
}

fun EX5(){
    val sc = Scanner(System.`in`)
    var first = sc.nextInt()
    var second = sc.nextInt()
    var third = sc.nextInt()
    var cont = third < first+second
    var counter = 1
    while(cont)
    {
        first = second
        second = third
        third = sc.nextInt()
        cont = third < first+second
        counter++
    }
    println("La seqüència ha acabat amb els dos valors anteriors $first i $second tinguent com a tercer $third en $counter iteracions")
}

fun EX6(){
    val sc = System.`in`
    val sb = StringBuilder()
    var char = sc.read().toChar()
    while(char != '.')
    {
        if(char != ' ') sb.append(char)
        char = sc.read().toChar()
    }
    println(sb)
}

fun EX7(){
    val sc = Scanner(System.`in`)
    println("Diga'm quants números entraràs")
    val n = sc.nextInt()
    var mult3 = 0
    var mult5 = 0
    for (i in 1..n)
    {
        val num = sc.nextInt()
        if(num == 0) continue
        if(num % 3 == 0) mult3++
        if(num % 5 == 0) mult5++
    }
    println("Múltiples de 3: $mult3. Múltiples de 5: $mult5")
}

fun EX8(){
    val sc = Scanner(System.`in`)
    println("Diga'm quants números entraràs:")
    val n = sc.nextInt()
    var pos = 1
    var small = sc.nextInt()
    var num = small
    for(i in 2..n)
    {
        val nxtNum = sc.nextInt()
        if(small > nxtNum){
            small = nxtNum
            num = nxtNum
            pos = i
        }
    }
    println("El número més petit és el $num en la posició $pos")
}