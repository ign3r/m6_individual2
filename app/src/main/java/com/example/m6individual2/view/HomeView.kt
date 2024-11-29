package com.example.m6individual2.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.m6individual2.R
import com.example.m6individual2.ui.theme.Purple80
import com.example.m6individual2.viewmodel.IMCViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel: IMCViewModel, navController: NavHostController, context: Context) {
    val contactos = viewModel.contactos

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("DetailView") },
                containerColor = Purple80
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir paciente")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyColumn {
                items(contactos.size) { index ->
                    val contacto = contactos[index]
                    CardItem(
                        id=contacto.id,
                        nombre = contacto.nombre,
                        telefono = contacto.telefono,
                        correo = contacto.correo,
                        imagen = contacto.imagen,
                        fecha = contacto.fecha,
                        navController,
                        viewModel
                    )
                }
            }
        }
    }
}
@Composable
fun CardItem(id:Int, nombre: String, telefono: String, correo:String, imagen:String, fecha:String, navController: NavHostController,viewModel: IMCViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                Column(
                    modifier = Modifier.padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {

                    Spacer(modifier = Modifier.height(20.dp))
                    ImagenContacto(imagen)

                }
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Nombre: $nombre",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Telefono: $telefono",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Correo: $correo",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Fecha de Nacimiento: $fecha",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }

            }
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = { navController.navigate("DetailView2/$id") }) {
                        Text("Editar")
                    }
                }
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {  viewModel.eliminarContacto(id)}) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}


@Composable
fun ImagenContacto(image: String) {
    val images = rememberAsyncImagePainter(model = image)
    Image(
        painter = images,
        contentDescription = "Imagen_contacto",
        modifier = Modifier
            .height(80.dp)
    )
}
