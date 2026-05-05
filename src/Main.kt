package io.pivotal

import java.awt.Desktop // Permet d'ouvrir le navigateur par défaut
import java.net.URI // Représente une adresse web
import com.sun.net.httpserver.HttpServer // Serveur HTTP
import com.sun.net.httpserver.HttpHandler // Gestionnaire de requêtes HTTP
import com.sun.net.httpserver.HttpExchange // Représente une requête/réponse HTTP
import java.net.InetSocketAddress // Adresse IP et port du serveur
import java.io.File
import java.io.InputStream


fun main() {
    val server = HttpServer.create(InetSocketAddress(8080), 0)
    server.createContext("/bootstrap.css", MyHandler("src/templates/css/bootstrap.css"))
    server.createContext("/bootstrap.js", MyHandler("src/templates/js/bootstrap.js"))
    server.createContext("/bootstrap.b.js", MyHandler("src/templates/js/bootstrap.bundle.js"))
    server.createContext("/", MyHandler("src/templates/pages/accueil.html"))

    //server.executor = null // creates a default executor
    server.start()
}

fun readFile(page: String): String {
    val inputString = File(page).readText()
    return inputString
}

fun useRegex(page: String, variables : Map<String, String>): String {
    val regex = "\\{\\{(.*?)}}".toRegex()  // Capture tout entre {{ et }}

    variables.forEach {
        page.replace(regex) {

        }
    }
    return regex

}


class MyHandler(val page: String) : HttpHandler {
    override fun handle(t: HttpExchange) {
        val response = readFile(page)
        t.sendResponseHeaders(200, response.length.toLong())
        val os = t.responseBody
        os.write(response.toByteArray())
        os.close()
    }
}


/*
fun renderPageWithRegex(template: String, variables: Map<String, String>): String {
    val regex = "\\{\\{(.*?)}}".toRegex()  // Capture tout entre {{ et }}
    return regex.replace(template) { matchResult ->
        val key = matchResult.groupValues[1]
        variables[key] ?: matchResult.value // si variable non trouvée, on laisse la balise
    }
}
 */