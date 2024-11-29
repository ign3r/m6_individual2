package com.example.m6individual2.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.m6individual2.R
import com.example.m6individual2.components.Alert
import com.example.m6individual2.components.Boton
import com.example.m6individual2.components.CustomOutlinedTextField
import com.example.m6individual2.components.CustomOutlinedTextFieldTexto
import com.example.m6individual2.components.EspacioNormal
import com.example.m6individual2.components.MultiBoton
import com.example.m6individual2.components.Texto
import com.example.m6individual2.components.estadoSalud
import com.example.m6individual2.viewmodel.IMCViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView2(id:Int, viewModel: IMCViewModel, navController: NavHostController) {

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.title2)) },
            )
        }
    ) {
        ContentDetailView2(id,it,viewModel,navController)
    }

}

@Composable
fun ContentDetailView2(id: Int, paddingValues: PaddingValues, viewModel: IMCViewModel,navController: NavHostController) {
    // Obtener los datos del contacto usando el ViewModel
    val contacto = viewModel.obtenerPacientePorId(id)

    // Variables para el estado de los campos del formulario
    var nombre by remember { mutableStateOf(contacto.nombre) }
    var telefono by remember { mutableStateOf(contacto.telefono) }
    var correo by remember { mutableStateOf(contacto.correo) }
    var imagen by remember { mutableStateOf(contacto.imagen) }
    var fecha by remember { mutableStateOf(contacto.fecha) }
    var showAlert by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EspacioNormal()

        // Campos del formulario
        CustomOutlinedTextFieldTexto(nombre, { nombre = it }, "Nombre")
        CustomOutlinedTextField(telefono, { telefono = it }, "Teléfono")
        CustomOutlinedTextFieldTexto(correo, { correo = it }, "Correo")
        CustomOutlinedTextFieldTexto(imagen, { imagen = it }, "URL de imagen")
        CustomOutlinedTextFieldTexto(fecha, { fecha = it }, "Fecha de Nacimiento")

        EspacioNormal()
        EspacioNormal()
        EspacioNormal()

        // Botón para guardar cambios
        Boton(text = "Guardar") {
            showAlert = viewModel.evaluarDatoVacio(nombre, telefono, correo, imagen, fecha)
            // Editar contacto en el ViewModel

            // Evaluar si hay datos vacíos
            if(!showAlert){
                viewModel.editarContacto(id, nombre, telefono, correo, imagen, fecha)
                navController.navigate("Home")
            }


        }

        // Mostrar alerta en caso de datos vacíos
        if (showAlert) {
            Alert(
                title = "Alerta",
                msj = "Ingresa los datos adecuadamente",
                confirmText = "Aceptar",
                onConfirmClick = { showAlert = false }
            ) {}
        }
    }
}


