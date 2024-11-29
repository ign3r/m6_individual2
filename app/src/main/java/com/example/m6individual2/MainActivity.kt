package com.example.m6individual2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.m6individual2.navigation.NavManager
import com.example.m6individual2.ui.theme.M6Individual2Theme
import com.example.m6individual2.viewmodel.IMCViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Instancia de ViewModel
        val viewModel: IMCViewModel by viewModels()

        // Instancia de DataStoreManager
        val dataStoreManager = DataStoreManager(this)

        // Configuración del contenido
        setContent {
            M6Individual2Theme {
                // Pasamos DataStoreManager y ViewModel a NavManager
                NavManager(
                    viewModel = viewModel,
                    dataStoreManager = dataStoreManager,
                    context = this
                )
            }
        }
    }
}


