package cat.institutmontivi.navegaciambtipussegurs.navegacio

import kotlinx.serialization.Serializable

@Serializable
object Principal

@Serializable
object LlistaDeNumeros

@Serializable
object LlistaDeLletres

@Serializable
data class Element(val text:String)