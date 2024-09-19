import java.io.File
import java.util.Scanner

fun main() {
    var llibres = llegeix("Llibres.csv")
    val llibre = ompleLlibre()
    println(llibre)
}


fun separa(line: String): MutableList<String> {
    var a = line.split("//")
    var b = a.toMutableList()
    b.removeLast()

    return if (line != "") b else mutableListOf()
}

fun converteix(line: String): Book {
    val splited = line.split(";")
    return Book(
        idBNE = splited[0],
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
        sitioDePublicacion = splited[13]
    )
}

fun llegeix(name: String): MutableList<Book> {
    val books: MutableList<Book> = mutableListOf()
    File(name).useLines { lines -> lines.forEachIndexed { index, s -> if (index != 0) books.add(converteix(s)) } }
    return books
}

fun desa(list: List<Book>, newFileName: String) {
    var file = File(newFileName)
    file.writeText("idBNE;Autor Personas;Autor Entidades;Título;Descripción y notas;Género/Forma;Depósito Legal;País de publicación;Lengua de publicación;version_digital;texto_OCR;Tema;ISBN;Editorial;Lugar de publicación\n")
    list.forEach { book -> file.appendText(book.csvConverter() + "\n") }
}

fun ompleLlibre(): Book {
    val newBook = Book()
    var entry = ""
    val sc = Scanner(System.`in`)
    do {
        printNewBookMenu()
        entry = sc.nextLine().uppercase()
        var newValue = ""
        when (entry) {
            "IDBNE", "1" -> {
                println("Diga'm el IdBNE que vols introduir al llibre: ")
                newValue = sc.nextLine()
                newBook.idBNE = newValue
            }
            "AUTORS", "2" -> {
                println("Entra tots els autors d'aquest llibre. Quan hagis acabat, entra una línia buida.")
                val autors : MutableList<String> = mutableListOf()
                do {
                    newValue = sc.nextLine()
                    if(newValue != "") autors.add(newValue)
                }while(newValue != "")
                newBook.autores.addAll(autors)
            }
            "ENTITATS", "3" -> {
                println("Entra totes les entitats d'aquest llibre. Quan hagis acabat, entra una línia buida.")
                val entitats : MutableList<String> = mutableListOf()
                do {
                    newValue = sc.nextLine()
                    if(newValue != "") entitats.add(newValue)
                }while(newValue != "")
                newBook.entidades.addAll(entitats)
            }
            "TITOL", "TÍTOL", "4" -> {
                println("Diga'm el títol que vols introduir al llibre: ")
                newValue = sc.nextLine()
                newBook.titulo = newValue
            }
            "DESCRIPCIO", "DESCRIPCIÓ", "5" -> {
                println("Entra totes les descripcions i cometnaris d'aquest llibre. Quan hagis acabat, entra una línia buida.")
                val descript : MutableList<String> = mutableListOf()
                do {
                    newValue = sc.nextLine()
                    if(newValue != "") descript.add(newValue)
                }while(newValue != "")
                newBook.descripcion.addAll(descript)
            }
            "GENERE", "GÈNERE", "6" -> {
                println("Diga'm el génere que vols introduir al llibre: ")
                newValue = sc.nextLine()
                newBook.genero = newValue
            }
            "DIPÒSIT", "DIPOSIT", "DIPOSIT LEGAL", "DIPÒSIT LEGAL", "7" -> {
                println("Diga'm el dipòsit legal que vols introduir al llibre: ")
                newValue = sc.nextLine()
                newBook.depositoLegal = newValue
            }
            "PAIS", "PAÍS", "8" -> {
                println("Diga'm el país que vols introduir al llibre: ")
                newValue = sc.nextLine()
                newBook.pais = newValue
            }
            "IDIOMA", "9" -> {
                println("Diga'm el idioma que vols introduir al llibre: ")
                newValue = sc.nextLine()
                newBook.idioma = newValue
            }
            "VERSIO", "VERSIÓ", "VERSIO DIGITAL", "VERSIÓ DIGITAL", "10" -> {
                println("Diga'm el versió digital que vols introduir al llibre: ")
                newValue = sc.nextLine()
                newBook.versionDigital = newValue
            }
            "TEXT", "TEXT OCR", "11" -> {
                println("Diga'm el textOCR que vols introduir al llibre: ")
                newValue = sc.nextLine()
                newBook.textoOCR = newValue
            }
            "ISBN", "12" -> {
                println("Diga'm el ISBN que vols introduir al llibre: ")
                newValue = sc.nextLine()
                newBook.ISBN = newValue
            }
            "TEMA", "13" -> {
                println("Diga'm el tema que vols introduir al llibre: ")
                newValue = sc.nextLine()
                newBook.tema = newValue
            }
            "EDITORIAL", "14" -> {
                println("Diga'm el editorial que vols introduir al llibre: ")
                newValue = sc.nextLine()
                newBook.editorial = newValue
            }
            "LLOC", "LLOC DE PUBLICACIO", "LLOC DE PUBLICACIÓ", "15" -> {
                println("Diga'm el lloc de publicació que vols introduir al llibre: ")
                newValue = sc.nextLine()
                newBook.sitioDePublicacion = newValue
            }
        }

    } while (entry != "TANCAR")
    return newBook
}

fun printNewBookMenu() {
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
    println("Escriu \"Tancar\" per a acabar d'editar el llibre.")
}

fun altaLlibre(book: Book) {

}

fun eliminaLlibre(id: String, list: MutableList<Book>) {
    
}

fun eliminaPosicio(pos: Int) {

}

fun llistaPaisos() {

}

fun llistaPais(pais: String) {

}

fun llistaIdiomes() {

}

fun llistaIdioma(idioma: String) {

}

fun llistaLlibre(id: Int) {

}

fun llistaPos(pos: Int) {

}

fun llistaRang(inici: Int, final: Int) {

}
