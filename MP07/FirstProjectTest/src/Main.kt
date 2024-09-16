import java.util.*

fun main() {
    EX13()
}

fun EX1(){
    val sc = Scanner(System.`in`)
    println("Dona'm tres valors i et diré quin és més gran.")
    print("Escriu el primer valor: ")
    val n1 = sc.nextInt()
    print("Escriu el segon valor: ")
    val n2 = sc.nextInt()
    print("Escriu el tercer valor: ")
    val n3 = sc.nextInt()
    println("El valor més gran és ${GetHigherOfThree(n1, n2, n3)}.")

}
fun GetHigherOfThree(n1: Int, n2: Int, n3: Int): Int {
    var n = n1
    if(n < n2) n = n2
    if(n < n3) n = n3
    return n
}

fun EX2(){
    val sc = Scanner(System.`in`)
    println("Dona'm dos valors per calcular el valor absolut de la diferència.")
    print("Escriu el primer valor: ")
    val n1 = sc.nextInt()
    print("Escriu el segon valor: ")
    val n2 = sc.nextInt()
    print("El valor absolut de $n1 i $n2 és ${GetAbsDifference(n1, n2)}")
}
fun GetAbsDifference(n1: Int, n2: Int): Int {
    var n = n1 - n2
    if(n < 0) n = -n
    return n
}

fun EX3(){
    val sc = Scanner(System.`in`)
    println("Dona'm dos valors no negatius superiors a zero i et diré si són divisors entre ells.")
    print("Escriu el primer valor: ")
    val n1 = sc.nextInt()
    print("Escriu el segon valor: ")
    val n2 = sc.nextInt()
    if(n1 < 0 || n2 < 0) println("Algun dels valors entrats no és correcte.")
    else
    {
        if(n1%n2.toDouble() == 0.0 || n2%n1.toDouble() == 0.0) println("Els valors $n1 i $n2 són divisors entre ells")
        else println("Els valors $n1 i $n2 no són divisors entre ells")
    }
}

fun EX4(){
    val sc = Scanner(System.`in`)
    println("Diga'm un any i et diré si és de traspàs o no.")
    val any = sc.nextInt()
    print("L'any $any ${if(EsTraspas(any)) "és de traspàs" else "no és de traspàs"}")

}
fun EsTraspas(any: Int) = any % 4 == 0 && (any % 100 == 0 || any % 400 == 0)

fun EX5(){
    val sc = Scanner(System.`in`)
    println("Dona'm la quantitat de segons i t'ho desglossaré.")
    val sec = sc.nextInt()
    println("$sec segons desglossat és equivalent al següent: ${ToTimeStamp(sec)}")
}
fun ToTimeStamp(sec:Int):String{
    var sec = sec
    val days = sec / 86400
    sec %= 86400
    val hours = sec / 3600
    sec %= 3600
    val minutes = sec / 60
    sec %= 60
    val seconds = sec % 60
    return String.format(Locale.US, "%02d, %02d : %02d : %02d", days, hours, minutes, seconds)
}

fun EX6(){
    val sc = Scanner(System.`in`)
    println("Dona'm una hora en format hh:mm:ss i li sumaré un segon.")
    val hour = sc.nextLine()
    val hours = hour.split(":")[0].toInt()
    val min = hour.split(":")[1].toInt()
    val sec = hour.split(":")[2].toInt()
    if(min < 0 || min > 59 || sec < 0 || sec > 59 || hours < 0 || hours > 0) println("La hora és incorrecte")
    else
    println("La teva hora $hour amb un segon més, és ${AddSec(hours, min,sec)}")

}
fun AddSec(hours:Int, min:Int, sec:Int):String{
    val secs = sec + min*60 + hours*3600 + 1
    return ToTimeStamp(secs).split(",")[1]
}

fun EX7(){
    val sc = Scanner(System.`in`)
    println("Diga'm la quantitat a pagar:")
    val toPay = sc.nextDouble()
    println("Diga'm quantitat que has pagat:")
    val payed = sc.nextDouble()
    if(payed < toPay) println("La quantitat pagada no és suficient.")
    else
    {
        println("Se't retornarà ${ReturnChange(payed - toPay)}")
    }
}
fun ReturnChange(changeDouble: Double) : String{
    var change = (changeDouble*100).toInt()
    val eur2 = change / 200
    change %=200
    val eur1 = change / 100
    change %=100
    val cen50 = change / 50
    change %= 50
    val cen20 = change / 20
    change %= 20
    val cen10 = change / 10
    change %= 10
    val cen5 = change / 5
    change %= 5
    val cen2 = change / 2
    change %= 2
    val cen1 = change
    return  if(changeDouble > 0)
                (if(eur2 > 0)"$eur2 monedes de 2€, " else "") +
                (if(eur1 > 0)"$eur1 monedes de 1€, " else "") +
                (if(cen50 > 0)"$cen50 monedes de 50cen, " else "") +
                (if(cen20 > 0) "$cen20 monedes de 20cen, " else "") +
                (if(cen10 > 0)"$cen10 monedes de 10cen, " else "") +
                (if(cen5 > 0)"$cen5 monedes de 5cen, " else "") +
                (if(cen2 > 0) "$cen2 monedes de 2cen, " else "") +
                (if(cen1 > 0)"$cen1 monedes de 1cen, " else "")
            else "res. No hi ha canvi a retornar"
}

fun EX8(){
    val sc = Scanner(System.`in`)
    println("Diga'm el número límit de la multiplicació:")
    val lim = sc.nextInt()
    for (i in 1..lim) {
        for (j in 0..10)
            println("$i x $j = ${i*j}")
    }
}

fun EX9(){
    val sc = Scanner(System.`in`)
    println("Diga'm la quantitat de línies a escriure")
    val lin = sc.nextInt()
    for (i in 1..lin) {
        var sum = 0
        for(j in 1..i-1)
        {
            print("$j + ")
            sum += j
        }
        sum += i
        println("$i = $sum")
    }
}

fun EX10(){
    val sc = Scanner(System.`in`)
    print("Dona'm el primer valor: ")
    var primer = sc.nextInt()
    print("Dona'm el segon valor: ")
    var segon = sc.nextInt()
    while(segon != 0)
    {
       var aux = segon
       segon = primer%segon
       primer = aux
    }
    print("El MCD és $primer")
}

fun EX11(){
    val sc = Scanner(System.`in`)
    print("Dona'm la quantitat de nombres de la seqüència de Fibonacci vols. ")
    var quantity = sc.nextInt()
    var first = 1
    var second = 1
    for(i in 0..<quantity){
        when (i) {
            0 -> print(1)
            1 -> print(1)
            else -> {
                val value = first + second
                print(value)
                first = second
                second = value
            }
        }
        print(" ")
    }
}

fun EX12(){
    val sc = Scanner(System.`in`)
    print("Dona'm el radi amb el que voldràs que faci els càlculs: ")
    val rad = sc.nextDouble()
    sc.nextLine()
    var resposta = ""
    while(resposta != "longitud" && resposta != "area" && resposta != "volum"){
        println("Diga'm qué vols que calculi amb el radi: \n La longitud (longitud) \n L'àrea (area) \n El volum (volum)")
        resposta = sc.nextLine()
    }
    when(resposta){
        "longitud" -> println("La longitud de la circumferència amb radi $rad és ${2*3.14f*rad}")
        "area" -> println("L'àrea del cercle amb radi $rad és ${3.14f*rad*rad}")
        "volum" -> println("El volum d'una esfera amb radi $rad és ${4/3*3.14f*rad*rad*rad}")
    }

}

fun EX13(){
    val sc = Scanner(System.`in`)
    print("Entra una lletra minúscula perquè la transformi a majúscula: ")
    val char = System.`in`.read()
    if(char in 97..122)
        println("La teva lletra en majúscula és ${(char - 32).toChar()}")
    else
        println("El caràcter entrat no era una lletra minúscula")
}