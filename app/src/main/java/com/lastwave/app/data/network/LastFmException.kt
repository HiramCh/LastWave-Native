package com.lastwave.app.data.network

/** Thrown for any Last.fm API-level error (HTTP-200-with-error-body,
 *  network failure, or malformed response). [message] is already the
 *  user-friendly string — show it directly. */
class LastFmException(message: String, val code: Int? = null) : Exception(message)

/** Port of _lfmFriendlyError() in app.js — see last.fm/api/errorcodes. */
object LastFmErrors {
    private val MESSAGES = mapOf(
        2 to "Servicio no válido; contacta al soporte.",
        3 to "Método no válido; actualiza la app.",
        4 to "Error de autenticación: tu clave o secreto de API pueden ser incorrectos. Revísalos en Ajustes.",
        5 to "Formato no válido.",
        6 to "Parámetros no válidos: faltan uno o más campos obligatorios.",
        7 to "Recurso no válido.",
        8 to "La operación falló; inténtalo de nuevo en un momento.",
        9 to "Clave de sesión no válida; vuelve a iniciar sesión.",
        10 to "Clave de API no válida; pega tu clave de last.fm/api/accounts tal cual.",
        11 to "Servicio temporalmente fuera de línea; inténtalo más tarde.",
        13 to "Firma de API no válida; verifica que tu secreto de API esté pegado correctamente y sin espacios extra.",
        14 to "Token no autorizado; primero autoriza la app en el navegador.",
        15 to "Este token caducó; vuelve a iniciar sesión.",
        16 to "Servicio temporalmente no disponible; inténtalo en unos segundos.",
        26 to "Clave de API suspendida; contacta al soporte de Last.fm.",
        29 to "Se superó el límite de solicitudes; espera un momento e inténtalo de nuevo.",
    )

    fun friendlyMessage(code: Int?, rawMessage: String?): String =
        code?.let { MESSAGES[it] } ?: rawMessage ?: "Error de autenticación (código ${code ?: "desconocido"})"
}
