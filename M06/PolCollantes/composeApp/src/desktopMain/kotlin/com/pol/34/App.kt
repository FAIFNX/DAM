package com.pol.`34`

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.cash.sqldelight.db.SqlDriver
import dev.xtec.data.Database
import dev.xtec.data.Games
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import polcollantes.composeapp.generated.resources.Res
import polcollantes.composeapp.generated.resources.compose_multiplatform
import polcollantes.composeapp.generated.resources.imagen

object DatabaseConfig {
    val name: String = "Games.db"
    val development: Boolean = true
}

//Insert
fun insertarDatos(database: Database) {
    val games = database.gameQueries.selectAll().executeAsList()
    if (games.isEmpty()) {
        database.gameQueries.insert("Minecraft")
        database.gameQueries.insert("League of Legends")
    } else {
        database.gameQueries.deleteAll()
        database.gameQueries.insert("Minecraft")
        database.gameQueries.insert("League of Legends")
    }

    val players = database.playerQueries.selectAll().executeAsList()
    if (players.isEmpty()) {
        database.playerQueries.insert("Pol", "Collantes", 1)
    } else {
        database.playerQueries.deleteAll()
        database.playerQueries.insert("Pol", "Collantes", 1)
    }
}


@Composable
@Preview
fun App(sqlDriver: SqlDriver) {
    MaterialTheme {
        val dataBase = Database(sqlDriver)
        Database.Schema.create(sqlDriver)
        val controller = rememberNavController()
        insertarDatos(dataBase)
        NavHost(controller, startDestination = HomeRoute){
            composable<HomeRoute> { HomeScreen(controller) }
            composable<GameRoute> { GameScreen(dataBase, controller) }
            composable<PlayerRoute> { PlayerScreen(controller, dataBase) }
            composable<InsertRoute> { InsertScreen(controller, dataBase) }
        }
    }
}

//Creación de objetos, listas, otros
@Serializable
object HomeRoute

@Serializable
object GameRoute

@Serializable
object PlayerRoute

@Serializable
object InsertRoute

//Ventanas
@Composable
fun HomeScreen(controller: NavController)
{
    Row (modifier = Modifier.padding(10.dp)){
        Column(modifier = Modifier.padding(10.dp)) {
            Text("Options:")
        }
        Row {
            Button(
                onClick = { controller.navigate(GameRoute) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFE0E0E0), // Color de fondo del botón
                    contentColor = Color(0xFFFFABAB) // Color del texto dentro del botón (rojo pastel)
                )
            ) {
                Text("Games")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick = {controller.navigate(PlayerRoute)}) {
                Text("Players")
            }
        }

    }

}

@Composable
fun GameScreen(database: Database, controller: NavController) {
    val games = database.gameQueries.selectAll().executeAsList()

    Column(modifier = Modifier.padding(10.dp)) {
        LazyColumn {
            items(games) { game ->
                Row(
                    modifier = Modifier.padding(2.dp), // Añade espaciado
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        game.name,
                        modifier = Modifier
                            .weight(1f) // Ocupa el espacio restante para empujar el botón a la derecha
                            .padding(end = 2.dp) // Espacio entre el nombre y el botón
                    )
                    Button(
                        onClick = { controller.navigate(PlayerRoute) }
                    ) {
                        Text("Info")
                    }
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = { controller.navigate(HomeRoute) },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd) // Alineación en la parte inferior derecha
        ) {
            Text("Home")
        }

        Button(
            onClick = { controller.navigate(InsertRoute) },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomStart) // Alineación en la parte inferior izquierda
        ) {
            Text("Insert")
        }
    }
}




@Composable
fun PlayerScreen(controller: NavController, database: Database)
{
    val player = database.playerQueries.selectById(1).executeAsOne()
    val game = player.Games?.let { gameId ->
        database.gameQueries.selectById(gameId).executeAsOneOrNull()
    }
    Row{
        Column(modifier = Modifier.padding(10.dp)) {
            if (game != null) {
                Text("${player.name} : ${game.name}")
            }
            else
            {
                Text("${player.name} doesn't have a game.")
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = { controller.navigate(HomeRoute) },
            modifier = Modifier
                .padding(16.dp) // Espaciado
                .align(Alignment.BottomEnd) // Alineación en la parte inferior derecha
        ) {
            Text("Home")
        }
    }
}

@Composable
fun InsertScreen(controller: NavController, database: Database)
{
    var gameName by remember { mutableStateOf("") }
    var gamesList by remember { mutableStateOf(listOf<String>()) }

    Row{
        Spacer(modifier = Modifier
            .padding(10.dp))
        TextField(
            value = gameName,
            placeholder = { Text("Nuevo Game")},
            onValueChange = {newValue -> gameName = newValue}
        )
        Spacer(modifier = Modifier
            .padding(10.dp))
        Button(
            onClick = {
                if (gameName.isNotEmpty()) {
                    database.gameQueries.insert(gameName) // Inserta en la base de datos
                    gameName = "" // Limpia el campo
                    gamesList = database.gameQueries.selectAll()
                        .executeAsList()
                        .map { it.name } // Extrae solo el campo "name" de cada objeto Games
                }
            }
        ) {
            Text("Insert Game")
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Button(
                onClick = { controller.navigate(HomeRoute) },
                modifier = Modifier
                    .padding(16.dp) // Espaciado
                    .align(Alignment.BottomEnd) // Alineación en la parte inferior derecha
            ) {
                Text("Home")
            }
        }
    }
}