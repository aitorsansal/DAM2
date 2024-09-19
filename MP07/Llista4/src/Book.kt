class Book(var idBNE : String = "",
           var autores: MutableList<String> = mutableListOf(),
           var entidades: MutableList<String> = mutableListOf(),
           var titulo: String = "",
           var descripcion: MutableList<String> = mutableListOf(),
           var genero: String = "",
           var depositoLegal: String = "",
           var pais: String = "",
           var idioma: String = "",
           var versionDigital: String = "",
           var textoOCR: String = "",
           var ISBN: String = "",
           var tema: String = "",
           var editorial: String = "",
           var sitioDePublicacion: String = "") {


    override fun toString(): String {
        val sb = StringBuilder("")
        sb.append("idBNE: $idBNE\n")
        sb.append("Autores: ")
        autores.forEach({ sb.append("$it //") })
        sb.append("\n")
        sb.append("Entidades: ")
        entidades.forEach({ sb.append("$it //") })
        sb.append("\n")
        sb.append("Título: $titulo\n")
        sb.append("Descripción y notas: ")
        descripcion.forEach({ sb.append("$it //") })
        sb.append("\n")
        sb.append("Género: $genero\n")
        sb.append("Depósito Legal: $depositoLegal\n")
        sb.append("País: $pais\n")
        sb.append("Idioma: $idioma\n")
        sb.append("Versión Digital: $versionDigital\n")
        sb.append("Texto_OCR: $textoOCR\n")
        sb.append("ISBN: $ISBN\n")
        sb.append("Tema: $tema\n")
        sb.append("Editorial: $editorial\n")
        sb.append("Sitio de publicación: $sitioDePublicacion")

        return sb.toString()
    }

    fun csvConverter() : String{
        val sb = StringBuilder()
        sb.append("$idBNE;")
        autores.forEach({ sb.append("$it //") })
        sb.append(";")
        entidades.forEach({ sb.append("$it //") })
        sb.append(";")
        sb.append("$titulo;")
        descripcion.forEach({ sb.append("$it //") })
        sb.append(";")
        sb.append("$genero;")
        sb.append("$depositoLegal;")
        sb.append("$pais;")
        sb.append("$idioma;")
        sb.append("$versionDigital;")
        sb.append("$textoOCR;")
        sb.append("$ISBN;")
        sb.append("$tema;")
        sb.append("$editorial;")
        sb.append("$sitioDePublicacion;")
        return sb.toString()
    }
}