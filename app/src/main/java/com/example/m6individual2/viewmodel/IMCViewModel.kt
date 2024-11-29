package com.example.m6individual2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.m6individual2.model.ImcState
import java.text.DecimalFormat

class IMCViewModel : ViewModel() {
    var state by mutableStateOf(ImcState())
        private set

    // Lista de pacientes
    var contactos by mutableStateOf(listOf<Contacto>())
        private set

    // Agregar un nuevo paciente
    fun agregarContacto( nombre: String, telefono: String, correo: String, imagen: String, fecha: String) {
        val id =contactos.size
        val nuevoContacto = Contacto(id,nombre, telefono, correo, imagen, fecha)
        contactos = contactos + nuevoContacto
    }

    fun editarContacto(id: Int, nombre: String, telefono: String, correo: String, imagen: String, fecha: String) {
        // Verificar que el índice esté dentro de los límites de la lista
        if (id in contactos.indices) {
            // Crear una copia mutable de la lista
            val listaActualizada = contactos.toMutableList()
            // Modificar el elemento en la posición indicada
            listaActualizada[id] = Contacto(id,nombre, telefono, correo, imagen, fecha)
            // Actualizar la lista inmutable
            contactos = listaActualizada
        } else {
            // Manejo del caso en el que el índice es inválido
            throw IndexOutOfBoundsException("El índice $id está fuera de los límites de la lista.")
        }
    }

    fun eliminarContacto(id: Int) {
        // Verificar que el índice esté dentro de los límites de la lista
        if (id in contactos.indices) {
            // Crear una copia mutable de la lista
            val listaActualizada = contactos.toMutableList()
            // Eliminar el elemento en la posición indicada
            listaActualizada.removeAt(id)
            // Actualizar la lista inmutable
            contactos = listaActualizada.mapIndexed { index, contacto ->
                // Reasignar IDs para mantener el orden
                contacto.copy(id = index)
            }
        } else {
            // Manejo del caso en el que el índice es inválido
            throw IndexOutOfBoundsException("El índice $id está fuera de los límites de la lista.")
        }
    }

    fun obtenerPacientePorId(id: Int): Contacto {
        return contactos.firstOrNull { it.id == id }
            ?: throw IllegalArgumentException("No se encontró un contacto con el ID $id")
    }

    // Cálculo del IMC (sin cambios)
    fun evaluarDatoVacio(nombre: String, telefono: String, correo: String, imagen: String, fecha: String):  Boolean {

        return if (nombre.isNotEmpty() && telefono.isNotEmpty() && correo.isNotEmpty() && imagen.isNotEmpty() && fecha.isNotEmpty()) {
            false
        } else {
            true
        }
    }
}

data class Contacto(
    val id: Int,
    var nombre: String,
    val telefono: String,
    val correo: String,
    val imagen: String,
    val fecha: String
)

