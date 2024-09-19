import java.io.File
import java.util.Scanner

fun main() {
    var ret = llegeix("Llibres.csv")
    print(ret.size)
    desa(ret, "newFile.csv")
}



fun separa(line:String) : List<String> {
    var a = line.split("//")
    var b = a.toMutableList()
    b.removeLast()

    return if(line != "") b.toList() else emptyList()
}

fun converteix(line:String) : Book {
    val splited = line.split(";")
    return Book(idBNE = splited[0],
                autores = separa(splited[1]),
                entidades = separa(splited[2]),
                titulo = splited[3],
                descripcion = separa(splited[4]),
                genero = splited[5],
                depositoLegal = splited[6],
                pais = splited[7],
                idioma = splited[8],
                textoOCR = splited[9],
                tema = splited[10],
                ISBN = splited[11],
                editorial = splited[12],
                sitioDePublicacion = splited[13])
}

fun llegeix(name:String) : MutableList<Book> {
    val books : MutableList<Book> = mutableListOf()
    File(name).useLines { lines -> lines.forEachIndexed {index, s -> if(index != 0) books.add(converteix(s))} }
    return books
}

fun desa(list: List<Book>, newFileName: String) {
    var file = File(newFileName)
    file.writeText("idBNE;Autor Personas;Autor Entidades;Título;Descripción y notas;Género/Forma;Depósito Legal;País de publicación;Lengua de publicación;version_digital;texto_OCR;Tema;ISBN;Editorial;Lugar de publicación\n")
    list.forEach { book -> file.appendText(book.csvConverter() + "\n")}
}

fun ompleLlibre() : Book{
    val newBook = Book()
    var entry = ""
    val sc = Scanner(System.`in`)
    do {
        printNewBookMenu()
        entry = sc.nextLine().uppercase()
        when (entry)
        {
            "IDBNE", "1" -> println()
            "AUTORS", "2" -> println()
            "ENTITATS", "3" -> println()
            "TITOL", "TÍTOL", "4" -> println()
            "DESCRIPCIO", "DESCRIPCIÓ", "5" -> println()
            "GENERE", "GÈNERE", "6" -> println()
            "DIPÒSIT", "DIPOSIT", "DIPOSIT LEGAL", "DIPÒSIT LEGAL", "7" -> println()
            "PAIS", "PAÍS", "8" -> println()
            "IDIOMA", "9" -> println()
            "VERSIO", "VERSIÓ", "VERSIO DIGITAL", "VERSIÓ DIGITAL", "10" -> println()
            "TEXT", "TEXT OCR", "11" -> println()
            "ISBN", "12" -> println()
            "TEMA", "13" -> println()
            "EDITORIAL", "14" -> println()
            "LLOC", "LLOC DE PUBLICACIO", "LLOC DE PUBLICACIÓ",  "15" -> println()
        }

    } while (entry != "Tancar" && entry != "tancar")
    return newBook
}

fun printNewBookMenu(){
    println("Diga'm que vols emplenar ara: ")
    println("1. IdBNE")
    println("2. Autors")
    println("3. Entitats")
    println("4. Títol")
    println("5. Descripció i comentaris")
    println("6. Gènere")
    println("7. Dipòsit legal")
    println("8. País")
    println("9. Idioma")
    println("10. Versió Digital")
    println("11. Text OCR")
    println("12. ISBN")
    println("13. Tema")
    println("14. Editorial")
    println("15. Lloc de Publicació")
    println()
    println("Escriu \"Tancar per a acabar d'editar el llibre.\"")
}

fun altaLlibre(book:Book){

}
fun eliminaLlibre(id:String){

}
fun eliminaPosicio(pos:Int) {

}
fun llistaPaisos(){

}
fun llistaPais(pais:String){

}
fun llistaIdiomes(){

}
fun llistaIdioma(idioma:String){

}
fun llistaLlibre(id:Int){

}
fun llistaPos(pos:Int){

}
fun llistaRang(inici:Int, final:Int){

}

