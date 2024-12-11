package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import polcollantes.composeapp.generated.resources.Res
import polcollantes.composeapp.generated.resources.compose_multiplatform
import polcollantes.composeapp.generated.resources.imagen

@Composable
@Preview
fun App() {
    MaterialTheme {
        val controller = rememberNavController()
        NavHost(controller, startDestination = HomeRoute){
            composable<HomeRoute> {HomeScreen(controller)}
            composable<CityRoute> { CityScreen() }
            composable<CountryRoute> { CountryScreen(controller) }
        }

       /*
        Surface (modifier = Modifier.fillMaxSize()){
           //MessageCard(Message("Eva", "Buenas noches señorita"))
           //Conversation(messages)
       }
        */

    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items (messages){ messages -> MessageCard(messages) }
    }
}

//Creación de objetos, listas, otros
@Serializable
object HomeRoute

@Serializable
object CityRoute

@Serializable
object CountryRoute

data class Message(val author: String, val body: String)
val messages = listOf(
    Message(
        "Josep Pla",
        "Antigament, el viatjar era un privilegi de gran senyor. Generalment, era la coronació normal dels estudis d'un home. Ara el viatjar s'ha generalitzat i democratitzat considerablement. Viatja molta gent"
    ),
    Message(
        "Josep Pla",
        "Però, potser, les persones que viatgen per arrodonir i afermar la seva visió del món i dels homes són més rares avui que fa cent anys. "
    ),
    Message(
        "Josep Pla",
        "En el nostre país hi ha tres pretextos essencials per a passar la frontera: la peregrinació a Lourdes, la lluna de mel i els negocis. Hom no pot tenir idea de la quantitat de gent del nostre país que ha estat a Lourdes. És incomptable. "
    ),
    Message(
        "Josep Pla",
        "Fa trenta anys, les persones riques de Catalunya feien el viatge de noces a Madrid. Avui van a París o a Niça i de vegades a Itàlia. La lluna de mel, però, és un mal temps per veure res i per formar-se. No es poden pas fer dues coses importants a la vegada. El pitjor temps, potser, per a viatjar, de la vida, és la temporada de la lluna de mel."
    ),
)

//Ventanas
@Composable
fun HomeScreen(controller: NavController)
{
    Row (modifier = Modifier.padding(10.dp)){
        Column(modifier = Modifier.padding(10.dp)) {
            Text("Buttons")
        }
        Row {
            Button(onClick = {controller.navigate(CityRoute)}) {
                Text("City")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick = {controller.navigate(CountryRoute)}) {
                Text("Country")
            }
        }

    }

}

@Composable
fun CityScreen()
{
    Column(modifier = Modifier.padding(10.dp)) {
        Text("Barcelona")
    }
}

@Composable
fun CountryScreen(controller: NavController)
{
    Row{
        Column(modifier = Modifier.padding(10.dp)) {
            Text("España")
        }
        Row {
            Button(onClick = {controller.navigate(CityRoute)}) {
                Text("City")
            }
        }
    }

}

@Preview
@Composable
fun MessageCard(msg: Message)
{
    Row(modifier = Modifier.padding(all = 16.dp)) {
        Image(
            painter = painterResource(Res.drawable.imagen),
            contentDescription = "contact profile picture",
            modifier = Modifier
            //Size
            .size(100.dp)
            .clip(CircleShape)
            .border(3.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = msg.author, color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.h2
            )
            Spacer(modifier = Modifier.height(8.dp))
            Surface(shape = MaterialTheme.shapes.large) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }

}