package com.tasklist

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import io.ktor.server.request.*

@Serializable
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val completed: Boolean = false
)

val taskList = mutableListOf<Task>()
var nextId = 1

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        })
    }

    routing {
        route("/api/tasks") {
            // GET endpoint - returns all tasks
            get {
                call.respond(taskList)
            }

            // POST endpoint - creates a new task
            post {
                val newTask = call.receive<Task>()
                val taskWithId = newTask.copy(id = nextId++)
                taskList.add(taskWithId)
                call.respond(taskWithId)
            }
        }
    }
}
