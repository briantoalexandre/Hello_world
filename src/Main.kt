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
    server.createContext("/", MyHandler("src/templates/pages/accueil.html"))
    //server.executor = null // creates a default executor
    server.start()
}

fun readFile(page: String): String {
    val inputString = File(page).readText()
    return inputString
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
