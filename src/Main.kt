package io.pivotal

import java.awt.Desktop // Permet d'ouvrir le navigateur par défaut
import java.net.URI // Représente une adresse web
import com.sun.net.httpserver.HttpServer // Serveur HTTP
import com.sun.net.httpserver.HttpHandler // Gestionnaire de requêtes HTTP
import com.sun.net.httpserver.HttpExchange // Représente une requête/réponse HTTP
import java.net.InetSocketAddress // Adresse IP et port du serveur


fun main() {
    val server = HttpServer.create(InetSocketAddress(8080), 0)
    server.createContext("/", MyHandler())
    server.executor = null // creates a default executor
    server.start()
}

class MyHandler : HttpHandler {
    override fun handle(t: HttpExchange) {
        val response = "Hello World"
        t.sendResponseHeaders(200, response.length.toLong())
        val os = t.responseBody
        os.write(response.toByteArray())
        os.close()
    }
}