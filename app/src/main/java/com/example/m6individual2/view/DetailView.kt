package com.example.m6individual2.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
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
fun DetailView(viewModel: IMCViewModel, navController: NavHostController) {

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.title)) },
            )
        }
    ) {
        ContentDetailView(it,viewModel,navController)
    }

}

@Composable
fun ContentDetailView(paddingValues: PaddingValues, viewModel: IMCViewModel,navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var nombre by remember { mutableStateOf("") }
        var telefono by remember { mutableStateOf("") }
        var correo by remember { mutableStateOf("") }
        var imagen by remember { mutableStateOf("") }
        var fecha by remember { mutableStateOf("") }
        var showAlert:Boolean by remember {mutableStateOf(false)}


        EspacioNormal()
        CustomOutlinedTextFieldTexto(nombre, { nombre = it }, "Nombre")
        CustomOutlinedTextField(telefono, { telefono = it }, "Telefono")
        CustomOutlinedTextFieldTexto(correo, { correo = it }, "Correo")
        CustomOutlinedTextFieldTexto(imagen, { imagen = it }, "URL de imagen")
        CustomOutlinedTextFieldTexto(fecha , { fecha = it } , "Fecha de Nacimiento" )
        EspacioNormal()
        EspacioNormal()
        EspacioNormal()
        Boton(text = "Guardar") {

            showAlert = viewModel.evaluarDatoVacio(nombre, telefono, correo, imagen, fecha)
            if(!showAlert){
            viewModel.agregarContacto(nombre, telefono, correo, imagen, fecha)
                navController.navigate("Home")
            }
        }

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

fun estadoSaludTexto(imc: Double): String {
    return when {
        imc < 18.5 -> "Bajo peso"
        imc < 24.9 -> "Peso normal"
        imc < 29.9 -> "Sobrepeso"
        imc < 34.9 -> "Obesidad Clase I"
        imc < 39.9 -> "Obesidad Clase II"
        else -> "Obesidad Clase III"
    }
}
