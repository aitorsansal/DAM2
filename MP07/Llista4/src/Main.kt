import java.io.File
import java.util.Scanner

var list : MutableList<Book> = mutableListOf()

fun main() {
    list = llegeix("Llibres.csv")
    startMenu()
}

fun startMenu(){
    val sc = Scanner(System.`in`)
    var read: String
    do {
        startMenuPrint()
        read = sc.nextLine().uppercase()
        when(read)
        {
            "1" -> {
                println("Introdueix el nom (sense terminació) del nou fitxer on vols guardar la llista de llibres: ")
                val fileName = sc.nextLine().trim() + ".csv"
                desa(fileName)
                println("S'ha generat un nou fitxer a ${System.getProperty("user.dir")}")
            } //save books
            "2" -> {
                println()
                val newBook = ompleLlibre()
                newBookSavingMenuFun(sc, altaLlibre(newBook), newBook)
            } //fill book
            "3" -> {
                println("Escriu el idBNE del llibre que vulguis borrar: ")
                val idToRemove = sc.nextLine()
                println(eliminaLlibre(idToRemove))
            } //remove by idBNE
            "4" -> {
                var working = false
                var pos : Int = 0
                while(!working)
                {
                    println("Escriu la posició del llibre que vulguis borrar (1-${list.size}: ")
                    try {
                        pos = sc.nextLine().toInt()
                        working = true
                    } catch (e: Exception) {working = false}
                }
                println(eliminaPosicio(pos-1))
            } //remove by position
            "5" -> {
                println(llistaPaisos())
            } //show countries
            "6" -> {
                println("Diga'm el país per el qual vols buscar els llibres: (nom o sigles)")
                println(llistaPais(sc.nextLine().uppercase()))
            } //show books by country
            "7" -> {
                println(llistaIdiomes())
            } //show idioms
            "8" -> {
                println("Diga'm l'idioma per el qual vols buscar els llibres: (nom o sigles)")
                println(llistaIdioma(sc.nextLine().uppercase()))
            } //show books by idiom
            "9" -> {
                println("Escriu el idBNE del llibre que vulguis veure: ")
                var idToRemove = sc.nextLine()
                println(llistaLlibre(idToRemove))
            } //show book by idBNE
            "10" -> {
                var working = false
                var pos = 0
                while(!working)
                {
                    println("Escriu la posició del llibre que vulguis veure (1-${list.size}): ")
                    try {
                        pos = sc.nextLine().toInt()
                        working = true
                    } catch (e: Exception) {working = false}
                }
                println(llistaPos(pos-1))
            } //show book by position
            "11" -> {
                var working = false
                var pos1 = 0
                var pos2 = 0
                while(!working)
                {
                    println("Escriu la primera posició dels llibres que vulguis veure(1-${list.size}): ")
                    try {
                        pos1 = sc.nextLine().toInt()
                        working = pos1 >= 1 && pos1 < list.size
                    } catch (e: Exception) {working = false}
                }
                working = false
                while(!working)
                {
                    println("Escriu la segona posició dels llibres que vulguis veure(${pos1+1}-${list.size}): ")
                    try {
                        pos2 = sc.nextLine().toInt()
                        working = true
                    } catch (e: Exception) {working = false}
                }
                println(llistaRang(pos1-1, pos2))
            } //show books between range
            "", "TANCAR" -> println("Tancant procés. Que vagi bé!")
            else -> println("La opció entrada no és correcte")
        }
    } while(read.isNotEmpty() && read != "TANCAR")
}
fun startMenuPrint(){
    println("-------------------------------------------------------")
    println("Sel·lecciona quina funció vols fer: (introdueix el número de la funció)")
    println("1. Desar la llista en un nou fitxer")
    println("2. Omple un llibre")
    println("3. Eliminar un llibre per idBNE")
    println("4. Eliminar un llibre per posició")
    println("5. Llistar els països dels llibres")
    println("6. Llistar els llibres d'un país")
    println("7. Llistar els idiomes dels llibres")
    println("8. Llistar els llibres en un idioma")
    println("9. Mostrar l'informació d'un llibre per idBNE")
    println("10. Mostrar l'informació d'un llibre per posició")
    println("11. Mostrar l'informació dels llibres dins un rang")
    println("Per a sortir d'aquest menú, deixa la línia en blanc o escriu \"tancar\"")
    println("-------------------------------------------------------")
}

fun newBookSavingMenuFun(sc : Scanner, result: Pair<Boolean, Int>, newBook: Book){
    if(result.first) {
        var read : String
        println("Un llibre amb el mateix idBNE ja existeix. Sel·lecciona què fer a continuació: ")
        do {
            println("1. Substituir el llibre existent")
            println("2. Entrar un nou idBNE per el llibre creat")
            println("3. Cancelar operació. (Es perdrán les dades del nou llibre entrat)")
            read = sc.nextLine().uppercase()
        } while (read != "1" && read != "2" && read != "3")
        when (read) {
            "1" -> {
                list[result.second] = newBook
                println("El llibre s'ha substituït correctament")
            }

            "2" -> {
                println("Escriu el nou idBNE per el llibre creat: ")
                newBook.idBNE = sc.nextLine()
                newBookSavingMenuFun(sc, altaLlibre(newBook), newBook)
            }

            "3" -> {
                println("L'operació s'ha cancelat i les dades entrades s'han perdut.")
            }
        }
    }
    else{
        println("S'ha afegit el nou llibre a la llista. ")
        list.add(newBook)
    }
}

fun separa(line: String): MutableList<String> {
    val a = line.split("//")
    val b = a.toMutableList()
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

fun desa(newFileName: String) {
    val file = File(newFileName)
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

fun altaLlibre(book: Book) : Pair<Boolean, Int> {
    if(list.any{ it.idBNE == book.idBNE}) {
        val indexOfBook = list.indexOf(list.first { it.idBNE == book.idBNE })
        list[indexOfBook] = book
        return Pair(true, indexOfBook)
    } else {
        return Pair(false, -1)
    }
}

fun eliminaLlibre(id: String) : String {
    val bookToRemove =  list.find { it.idBNE == id }
    if(bookToRemove != null) {
        list.remove(bookToRemove)
        return "El llibre amb nom ${bookToRemove.titulo} s'ha eliminat de la llista."
    }
    else
        return "No hi havía cap llibre amb la id entrada."

}

fun eliminaPosicio(pos: Int) : String {
    if(pos < list.size && pos >= 0) {
        val book = list.removeAt(pos)
        return "S'ha eliminat el llibre amb títol ${book.titulo} de la llista."
    }
    else{
        return "La posició entrada no existeix en la llistra de llibres."
    }
}

fun llistaPaisos() : String {
    val countries = list.map { it.pais }.distinct()
    return "Els països disponibles en els llibres són els següents: \n" + countries.joinToString(", ")
}

val countryNamesToAbbreviation = mapOf(
    "SPAIN" to "sp", "ESPAÑA" to "sp", "ESPANYA" to "sp",
    "PERU" to "pe", "PERÚ" to "pe",
    "ITALY" to "it", "ITALIA" to "it", "ITÀLIA" to "it",
    "BELGIUM" to "be", "BÉLGICA" to "be", "BÈLGICA" to "be",
    "PORTUGAL" to "po", "PORTUGAL" to "po",
    "NIGER" to "ne", "NÍGER" to "ne",
    "ESWATINI" to "sz", "ESWATINI" to "sz",
    "ANTIGUA AND BARBUDA" to "ag", "ANTIGUA Y BARBUDA" to "ag", "ANTIGUA I BARBUDA" to "ag",
    "MEXICO" to "mx", "MÉXICO" to "mx", "MÈXIC" to "mx",
    "FRANCE" to "fr", "FRANCIA" to "fr", "FRANÇA" to "fr",
    "GUINEA-BISSAU" to "gw", "GUINEA-BISÁU" to "gw", "GUINEA-BISSAU" to "gw",
    "CUBA" to "cu",
    "PHILIPPINES" to "ph", "FILIPINAS" to "ph", "FILIPINES" to "ph",
    "CHILE" to "cl",
    "PITCAIRN ISLANDS" to "pn", "ISLAS PITCAIRN" to "pn", "ILLES PITCAIRN" to "pn",
    "UNITED STATES" to "us", "ESTADOS UNIDOS" to "us", "ESTATS UNITS" to "us",
    "COCOS ISLANDS" to "cc", "ISLAS COCOS" to "cc", "ILLES COCOS" to "cc",
    "URUGUAY" to "uy",
    "SAINT BARTHELEMY" to "bl", "SAN BARTOLOMÉ" to "bl", "SANT BARTOMEU" to "bl",
    "GUATEMALA" to "gt",
    "PUERTO RICO" to "pr", "PUERTO RICO" to "pr",
    "COOK ISLANDS" to "ck", "ISLAS COOK" to "ck", "ILLES COOK" to "ck",
    "ICELAND" to "is", "ISLANDIA" to "is", "ISLÀNDIA" to "is",
    "POLAND" to "pl", "POLONIA" to "pl", "POLÒNIA" to "pl",
    "GEORGIA" to "ge", "GEORGIA" to "ge",
    "MAURITANIA" to "mr", "MAURITANIA" to "mr",
    "AUSTRALIA" to "au",
    "RUSSIA" to "ru", "RUSIA" to "ru", "RÚSSIA" to "ru",
    "IRELAND" to "ie", "IRLANDA" to "ie", "IRLANDA" to "ie",
    "DENMARK" to "dk", "DINAMARCA" to "dk", "DINAMARCA" to "dk",
    "ARGENTINA" to "ar",
    "UNITED ARAB EMIRATES" to "ae", "EMIRATOS ÁRABES UNIDOS" to "ae", "EMIRATS ÀRABS UNITS" to "ae",
    "DOMINICAN REPUBLIC" to "dr", "REPÚBLICA DOMINICANA" to "dr", "REPÚBLICA DOMINICANA" to "dr",
    "VENEZUELA" to "ve",
    "HUNGARY" to "hu", "HUNGRÍA" to "hu", "HONGRIA" to "hu",
    "GREECE" to "gr", "GRECIA" to "gr", "GRÈCIA" to "gr",
    "LAOS" to "la",
    "NORTH KOREA" to "nk", "COREA DEL NORTE" to "nk", "COREA DEL NORD" to "nk",
    "COSTA RICA" to "cr",
    "EGYPT" to "eg", "EGIPTO" to "eg", "EGIPTE" to "eg",
    "ECUADOR" to "ec",
    "MONACO" to "mc", "MÓNACO" to "mc", "MÒNACO" to "mc",
    "HAITI" to "ht", "HAITÍ" to "ht",
    "BOLIVIA" to "bo",
    "NAMIBIA" to "na",
    "SLOVENIA" to "si", "ESLOVENIA" to "si", "ESLOVÈNIA" to "si",
    "HONG KONG" to "hk",
    "MYANMAR" to "mm", "BIRMANIA" to "mm",
    "ANGOLA" to "ao",
    "BURKINA FASO" to "bf",
    "NORWAY" to "no", "NORUEGA" to "no", "NORUEGA" to "no",
    "LATVIA" to "lv", "LETONIA" to "lv", "LETÒNIA" to "lv",
    "FINLAND" to "fi", "FINLANDIA" to "fi", "FINLÀNDIA" to "fi",
    "PARAGUAY" to "py",
    "SOUTH AFRICA" to "za", "SUDÁFRICA" to "za", "SUD-ÀFRICA" to "za",
    "CHINA" to "cn", "CHINA" to "cn", "XINA" to "cn",
    "JAPAN" to "jp", "JAPÓN" to "jp", "JAPÓ" to "jp",
    "SOUTH KOREA" to "kr", "COREA DEL SUR" to "kr", "COREA DEL SUD" to "kr",
    "INDIA" to "in", "INDIA" to "in", "ÍNDIA" to "in",
    "SAUDI ARABIA" to "sa", "ARABIA SAUDITA" to "sa", "ARÀBIA SAUDITA" to "sa"
)

fun llistaPais(pais: String) : String{
    var correctBooks : List<Book>
    if(!countryNamesToAbbreviation.containsValue(pais)){
        val abv = countryNamesToAbbreviation[pais] ?: return "No hi ha cap registre amb aquest país."
        correctBooks = list.filter{it.pais == abv}
    }
    else
        correctBooks = list.filter{it.pais == pais}
    return  if(correctBooks.isNotEmpty())"Els llibres del país ${pais.lowercase()} són: \n " + correctBooks.joinToString(separator = "\n\n")
            else "No hi ha cap llibre amb el país seleccionat."
}

fun llistaIdiomes() : String {
    val idiomes = list.map { it.idioma }.distinct()
    return "Els països disponibles en els llibres són els següents: \n" + idiomes.joinToString(", ")
}

val languageNamesToAbbreviations = mapOf(
    // Spanish, Catalan, and English translations for each abbreviation
    "ESPAÑOL" to "spa", "CASTELLANO" to "spa", "SPANISH" to "spa", "CASTELLÀ" to "spa",
    "ITALIANO" to "ita", "ITALIÀ" to "ita", "ITALIAN" to "ita",
    "CATALÁN" to "cat", "CATALÀ" to "cat", "CATALAN" to "cat",
    "LATÍN" to "lat", "LLATÍ" to "lat", "LATIN" to "lat",
    "FRANCÉS" to "fre", "FRANCÈS" to "fre", "FRENCH" to "fre",
    "ALEMÁN" to "ger", "ALEMANY" to "ger", "GERMAN" to "ger",
    "INGLÉS" to "eng", "ANGLÈS" to "eng", "ENGLISH" to "eng",
    "HEBREO" to "heb", "HEBREU" to "heb", "HEBREW" to "heb",
    "GRIEGO" to "grc", "GREC" to "grc", "GREEK" to "grc",
    "NEERLANDÉS" to "dut", "NEERLANDÈS" to "dut", "DUTCH" to "dut",
    "PORTUGUÉS" to "por", "PORTUGUÈS" to "por", "PORTUGUESE" to "por",
    "TAGALO" to "tgl", "TAGALOG" to "tgl",
    "ASTURIANO" to "ast", "ASTURIÀ" to "ast", "ASTURIAN" to "ast",
    "GALLEGO" to "glg", "GALLEC" to "glg", "GALICIAN" to "glg",
    "CHINO" to "chi", "XINÈS" to "chi", "CHINESE" to "chi",
    "JAPONÉS" to "jpn", "JAPONÈS" to "jpn", "JAPANESE" to "jpn",
    "RUSO" to "rus", "RUS" to "rus", "RUSSIAN" to "rus",
    "POLACO" to "pol", "POLONÈS" to "pol", "POLISH" to "pol",
    "RUMANO" to "rom", "RUMANÈS" to "rom", "ROMANIAN" to "rom",
    "FINLANDÉS" to "fin", "FINLANDÈS" to "fin", "FINNISH" to "fin",
    "NORUEGO" to "nor", "NORUEC" to "nor", "NORWEGIAN" to "nor",
    "SUECO" to "swe", "SUEC" to "swe", "SWEDISH" to "swe",
    "GRIEGO MODERNO" to "grr", "GREC MODERN" to "grr", "GREEK MODERN" to "grr",
    "ÁRABE" to "ara", "ÀRAB" to "ara", "ARABIC" to "ara",
    "HÚNGARO" to "hun", "HONGARÈS" to "hun", "HUNGARIAN" to "hun",
    "DESCONOCIDO" to "unk", "DESCONEGUT" to "unk", "UNKNOWN" to "unk"
)

fun llistaIdioma(idioma: String) : String {
    val abv = languageNamesToAbbreviations[idioma] ?: return "No hi ha cap registre amb aquest idioma."
    val foundBooks = list.filter{it.idioma == abv}
    return  if(foundBooks.isNotEmpty())"Els llibres escrits en ${idioma.lowercase()} són: \n " + foundBooks.joinToString(separator = "\n\n")
    else "No hi ha cap llibre amb l'idioma seleccionat."
}


fun llistaLlibre(id: String) : String {
    val foundBook = list.first {it.idBNE == id }
    return if(foundBook != null) foundBook.toString() else "No hi ha cap llibre amb l'idBNE $id"
}

fun llistaPos(pos: Int) : String {
    if(pos < list.size && pos >= 0) {
        val book = list[pos]
        return book.toString()
    }
    else{
        return "La posició entrada no existeix en la llistra de llibres."
    }
}

fun llistaRang(inici: Int, final: Int) : String { //TODO: when entering the position, rest one so it's in range
    if(inici < list.size && inici >= 0 && inici < final) {
        val finalRang = if(final < list.size) final else list.size
        val books = list.subList(inici, finalRang)
        return "S'han torbat els següents llibres: \n" + books.joinToString(separator = "\n\n")
    }
    else{
        return "El rang entrat no és correcte."
    }
}
