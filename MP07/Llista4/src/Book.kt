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


    val countryAbbreviationToCountryNames = mapOf(
        "sp" to "España",
        "pe" to "Perú",
        "it" to "Italia",
        "be" to "Bélgica",
        "po" to "Portugal",
        "ne" to "Níger",
        "sz" to "Eswatini",
        "ag" to "Antigua y Barbuda",
        "mx" to "México",
        "fr" to "Francia",
        "gw" to "Guinea-Bisáu",
        "cu" to "Cuba",
        "ph" to "Filipinas",
        "cl" to "Chile",
        "pn" to "Islas Pitcairn",
        "us" to "Estados Unidos",
        "cc" to "Islas Cocos",
        "uy" to "Uruguay",
        "bl" to "San Bartolomé",
        "gt" to "Guatemala",
        "pr" to "Puerto Rico",
        "ck" to "Islas Cook",
        "is" to "Islandia",
        "pl" to "Polonia",
        "ge" to "Georgia",
        "mr" to "Mauritania",
        "au" to "Australia",
        "ru" to "Rusia",
        "ie" to "Irlanda",
        "dk" to "Dinamarca",
        "ar" to "Argentina",
        "ae" to "Emiratos Árabes Unidos",
        "dr" to "República Dominicana",
        "ve" to "Venezuela",
        "hu" to "Hungría",
        "gr" to "Grecia",
        "la" to "Laos",
        "nk" to "Corea del Norte",
        "cr" to "Costa Rica",
        "eg" to "Egipto",
        "ec" to "Ecuador",
        "mc" to "Mónaco",
        "ht" to "Haití",
        "bo" to "Bolivia",
        "na" to "Namibia",
        "si" to "Eslovenia",
        "hk" to "Hong Kong",
        "mm" to "Birmania",
        "ao" to "Angola",
        "bf" to "Burkina Faso",
        "no" to "Noruega",
        "lv" to "Letonia",
        "fi" to "Finlandia",
        "py" to "Paraguay",
        "za" to "Sudáfrica",
        "cn" to "China",
        "jp" to "Japón",
        "kr" to "Corea del Sur",
        "in" to "India",
        "sa" to "Arabia Saudita"
    )

    val languageAbbreviationsToNames = mapOf(
        "spa" to "Español",
        "ita" to "Italiano",
        "cat" to "Catalán",
        "it" to "Italiano",
        "lat" to "Latín",
        "be" to "Bielorruso",
        "fre" to "Francés",
        "ger" to "Alemán",
        "sp" to "Español",
        "fr" to "Francés",
        "su" to "Sundanés",
        "eng" to "Inglés",
        "heb" to "Hebreo",
        "cc" to "Chino (simplificado)",
        "mul" to "Multilingüe",
        "grc" to "Griego antiguo",
        "sz" to "Suazi",
        "dut" to "Neerlandés",
        "" to "Desconocido",
        "at" to "Austriaco",
        "por" to "Portugués",
        "gw" to "Guinea-Bisáu",
        "baq" to "Euskera",
        "spaspa" to "Español (variante)",
        "xx" to "Desconocido",
        "swe" to "Sueco",
        "tgl" to "Tagalo",
        "ast" to "Asturiano",
        "glg" to "Gallego",
        "ge" to "Georgiano",
        "mx" to "Español (México)",
        "ara" to "Árabe",
        "mut" to "Multilingüe",
        "ne" to "Nepalí",
        "bel" to "Bielorruso",
        "ph" to "Filipino",
        "chi" to "Chino",
        "grr" to "Griego moderno",
        "jpn" to "Japonés",
        "fra" to "Francés",
        "zxx" to "Sin lenguaje hablado",
        "pa" to "Panjabi",
        "rus" to "Ruso",
        "gre" to "Griego",
        "map" to "Mapudungun",
        "pol" to "Polaco",
        "latspa" to "Español (Latinoamérica)",
        "und" to "Desconocido",
        "dan" to "Danés",
        "gem" to "Alemán (general)",
        "au" to "Austronesio",
        "pl" to "Polaco",
        "xr" to "Desconocido",
        "pe" to "Peruano",
        "ilo" to "Ilocano",
        "phi" to "Filipino",
        "frefre" to "Francés (variante)",
        "rom" to "Rumano",
        "gbr" to "Inglés (Gran Bretaña)",
        "ta" to "Tamil",
        "fin" to "Finlandés",
        "slv" to "Esloveno",
        "vie" to "Vietnamita",
        "ceb" to "Cebuan",
        "phl" to "Filipino",
        "epo" to "Esperanto",
        "pag" to "Pangasinense",
        "oto" to "Otomí",
        "que" to "Quechua",
        "may" to "Malayo",
        "arm" to "Armenio",
        "fan" to "Fang",
        "ing" to "Inglés",
        "hun" to "Húngaro",
        "rum" to "Rumano",
        "nor" to "Noruego",
        "cha" to "Chawis"
    )

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
        sb.append("País: $pais (${if(countryAbbreviationToCountryNames.containsKey(pais)) countryAbbreviationToCountryNames[pais] else "-"})\n")
        sb.append("Idioma: $idioma (${if(languageAbbreviationsToNames.containsKey(idioma)) languageAbbreviationsToNames[pais] else "-"})\n")
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