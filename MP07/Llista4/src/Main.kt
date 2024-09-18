import java.io.File

fun main() {
    var ret = llegeix("Llibres.csv")
    print(ret.size)
//    desa(ret, "newFile.csv")
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
    File(name).useLines {
        lines -> lines.forEachIndexed {
                                      index, s -> if(index != 0)
                                          books.add(converteix(s))
                                            println(index)} }
    println(books.size)
    return books
}

fun desa(list: List<Book>, newFileName: String) {
    var file = File(newFileName)
    file.writeText("idBNE;Autor Personas;Autor Entidades;Título;Descripción y notas;Género/Forma;Depósito Legal;País de publicación;Lengua de publicación;version_digital;texto_OCR;Tema;ISBN;Editorial;Lugar de publicación\n")
    list.forEach { book -> file.appendText(book.csvConverter() + "\n")}
}

fun ompleLlibre(){

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

