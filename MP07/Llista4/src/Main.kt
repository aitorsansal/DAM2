import java.io.File
import java.util.Scanner

fun main() {
    var llibres = llegeix("Llibres.csv")
    println(llibres)
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

fun eliminaLlibre(id: String, list: MutableList<Book>) : String {
    val bookToRemove =  list.find { it.idBNE == id }
    if(bookToRemove != null) {
        list.remove(bookToRemove)
        return "El llibre amb nom ${bookToRemove.titulo} s'ha eliminat de la llista."
    }
    else
        return "No hi havía cap llibre amb la id entrada."

}

fun eliminaPosicio(pos: Int, list: MutableList<Book>) : String {
    if(pos < list.size && pos > 0) {
        val book = list.removeAt(pos)
        return "S'ha eliminat el llibre amb títol ${book.titulo} de la llista."
    }
    else{
        return "La posició entrada no existeix en la llistra de llibres."
    }
}

fun llistaPaisos(list: MutableList<Book>) : String {
    val countries = list.map { it.pais }.distinct()
    return "Els països disponibles en els llibres són els següents: \n" + countries.joinToString(", ")
}

val countryNamesToAbbreviations = mapOf(
    "SPAIN" to "sp",
    "PERU" to "pe",
    "ITALY" to "it",
    "BELGIUM" to "be",
    "PORTUGAL" to "po",
    "NIGER" to "ne",
    "ESWATINI" to "sz",
    "ANTIGUA AND BARBUDA" to "ag",
    "MEXICO" to "mx",
    "FRANCE" to "fr",
    "GUINEA-BISSAU" to "gw",
    "CUBA" to "cu",
    "PHILIPPINES" to "ph",
    "CHILE" to "cl",
    "PITCAIRN ISLANDS" to "pn",
    "UNITED STATES" to "us",
    "COCOS (KEELING) ISLANDS" to "cc",
    "URUGUAY" to "uy",
    "SAINT BARTHÉLEMY" to "bl",
    "GUATEMALA" to "gt",
    "PUERTO RICO" to "pr",
    "COOK ISLANDS" to "ck",
    "ICELAND" to "is",
    "POLAND" to "pl",
    "GEORGIA" to "ge",
    "MAURITANIA" to "mr",
    "AUSTRALIA" to "au",
    "RUSSIA" to "ru",
    "IRELAND" to "ie",
    "DENMARK" to "dk",
    "ARGENTINA" to "ar",
    "UNITED ARAB EMIRATES" to "ae",
    "DOMINICAN REPUBLIC" to "dr",
    "VENEZUELA" to "ve",
    "HUNGARY" to "hu",
    "GREECE" to "gr",
    "LAOS" to "la",
    "NORTH KOREA" to "nk",
    "COSTA RICA" to "cr",
    "EGYPT" to "eg",
    "ECUADOR" to "ec",
    "MONACO" to "mc",
    "HAITI" to "ht",
    "BOLIVIA" to "bo",
    "NAMIBIA" to "na",
    "SLOVENIA" to "si",
    "HONG KONG" to "hk",
    "MYANMAR" to "mm",
    "ANGOLA" to "ao",
    "BURKINA FASO" to "bf",
    "NORWAY" to "no",
    "LATVIA" to "lv",
    "FINLAND" to "fi",
    "PARAGUAY" to "py",
    "SOUTH AFRICA" to "za",
    "CHINA" to "cn",
    "JAPAN" to "jp",
    "SOUTH KOREA" to "kr",
    "INDIA" to "in",
    "SAUDI ARABIA" to "sa"
)

fun llistaPais(pais: String, list: MutableList<Book>) : String{
    val abv = countryNamesToAbbreviations[pais] ?: return "No hi ha cap llibre amb aquest país"
    val correctBooks = list.filter{it.pais == abv}
    return "Els llibres del país $pais són: \n " + correctBooks.joinToString(separator = "\n\n")
}

fun llistaIdiomes(list: MutableList<Book>) : String {
    val idiomes = list.map { it.idioma }.distinct()
    return "Els països disponibles en els llibres són els següents: \n" + idiomes.joinToString(", ")
}

fun llistaIdioma(idioma: String) {

}

fun llistaLlibre(id: Int) {

}

fun llistaPos(pos: Int) {

}

fun llistaRang(inici: Int, final: Int) {

}
