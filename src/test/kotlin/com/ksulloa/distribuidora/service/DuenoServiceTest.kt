package com.ksulloa.distribuidora.service

import com.google.gson.Gson
import com.ksulloa.distribuidora.model.Dueno
import com.ksulloa.distribuidora.repository.DuenoRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

import org.springframework.boot.test.context.SpringBootTest
import java.io.File


@SpringBootTest
class DuenoServiceTest {

    @InjectMocks
    lateinit var duenoService: DuenoService

    @Mock
    lateinit var duenoRepository: DuenoRepository


    val returnObject: Dueno = Dueno().apply {
        id = 1
        nombre = "Carolina"
        apellido = "Lopez"
        cedula = "0150849584"
        telefono = "0984578852"
    }
    val newObject: Dueno = Dueno().apply {
        id = 1
        nombre = "Carolina"
        apellido = "Lopez"
        cedula = "0150849584"
        telefono = "0984578852"
    }

    @Test
    fun saveIsCorrect() {
        Mockito.`when`(duenoRepository.save(Mockito.any(Dueno::class.java))).thenReturn(returnObject)
        val response = duenoService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.apellido, newObject.apellido)
        Assertions.assertEquals(response.cedula, newObject.cedula)
        Assertions.assertEquals(response.telefono, newObject.telefono)

    }

    val jsonString = File("./src/test/resources/dueno/crearDueno.json").readText(Charsets.UTF_8)
    val duenoMock = Gson().fromJson(jsonString, Dueno::class.java)

    @Test
    fun saveDueno() {
        //PAra actualizar
        //Mockito.`when`(duenoRepository.findById(duenoMock.id)).thenReturn(dietMock)
        Mockito.`when`(duenoRepository.save(Mockito.any(Dueno::class.java))).thenReturn(duenoMock)
        val response = duenoService.save(duenoMock)
        Assertions.assertEquals(response.id, duenoMock.id)
        Assertions.assertEquals(response.nombre, duenoMock.nombre)
        Assertions.assertEquals(response.apellido, duenoMock.apellido)
        Assertions.assertEquals(response.cedula, duenoMock.cedula)
        Assertions.assertEquals(response.telefono, duenoMock.telefono)
    }

    @Test
    fun saveDuenoFailedNombre() {
        Assertions.assertThrows(Exception::class.java) {
            duenoMock.apply { nombre = "    " }
            Mockito.`when`(duenoRepository.save(Mockito.any(Dueno::class.java))).thenReturn(duenoMock)
            duenoService.save(duenoMock)
        }
    }

    @Test
    fun saveDuenoFailedApellido() {
        Assertions.assertThrows(Exception::class.java) {
            duenoMock.apply { apellido = "    " }
            Mockito.`when`(duenoRepository.save(Mockito.any(Dueno::class.java))).thenReturn(duenoMock)
            duenoService.save(duenoMock)
        }
    }

    @Test
    fun saveDuenoFailedCedula() {
        Assertions.assertThrows(Exception::class.java) {
            duenoMock.apply { cedula = "    " }
            Mockito.`when`(duenoRepository.save(Mockito.any(Dueno::class.java))).thenReturn(duenoMock)
            duenoService.save(duenoMock)
        }
    }

    @Test
    fun saveDuenoFailedTelefono() {
        Assertions.assertThrows(Exception::class.java) {
            duenoMock.apply { telefono = "    " }
            Mockito.`when`(duenoRepository.save(Mockito.any(Dueno::class.java))).thenReturn(duenoMock)
            duenoService.save(duenoMock)
        }
    }

    @Test
    fun updateDuenoIsIdCorrect() {
        Mockito.`when`(duenoRepository.findById(returnObject.id)).thenReturn(returnObject)
        Mockito.`when`(duenoRepository.save(Mockito.any(Dueno::class.java))).thenReturn(returnObject)
        val response = duenoService.update(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.apellido, newObject.apellido)
        Assertions.assertEquals(response.cedula, newObject.cedula)
        Assertions.assertEquals(response.telefono, newObject.telefono)

    }

    @Test
    fun updateDuenoIsIdFailed() {
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(duenoRepository.findById(returnObject.id)).thenReturn(null)
            Mockito.`when`(duenoRepository.save(Mockito.any(Dueno::class.java))).thenReturn(returnObject)
            val response = duenoService.update(newObject)
            Assertions.assertEquals(response.id, newObject.id)


        }
    }
    @Test
    fun updateDuenoFailedNombre() {
        Assertions.assertThrows(Exception::class.java) {
            duenoMock.apply { nombre = "    " }
            Mockito.`when`(duenoRepository.findById(returnObject.id)).thenReturn(duenoMock)
            Mockito.`when`(duenoRepository.save(Mockito.any(Dueno::class.java))).thenReturn(duenoMock)
            duenoService.update(duenoMock)
        }
    }

    @Test
    fun updateDuenoFailedApellido() {
        Assertions.assertThrows(Exception::class.java) {
            duenoMock.apply { apellido = "    " }
            Mockito.`when`(duenoRepository.findById(returnObject.id)).thenReturn(duenoMock)
            Mockito.`when`(duenoRepository.save(Mockito.any(Dueno::class.java))).thenReturn(duenoMock)
            duenoService.update(duenoMock)
        }
    }

    @Test
    fun updateDuenoFailedCedula() {
        Assertions.assertThrows(Exception::class.java) {
            duenoMock.apply { cedula = "    " }
            Mockito.`when`(duenoRepository.findById(returnObject.id)).thenReturn(duenoMock)
            Mockito.`when`(duenoRepository.save(Mockito.any(Dueno::class.java))).thenReturn(duenoMock)
            duenoService.update(duenoMock)
        }
    }

    @Test
    fun updateDuenoFailedTelefono() {
        Assertions.assertThrows(Exception::class.java) {
            duenoMock.apply { telefono = "    " }
            Mockito.`when`(duenoRepository.findById(returnObject.id)).thenReturn(duenoMock)
            Mockito.`when`(duenoRepository.save(Mockito.any(Dueno::class.java))).thenReturn(duenoMock)
            duenoService.update(duenoMock)
        }
    }
    @Test
    fun delete(){
        Mockito.`when`(duenoRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(duenoRepository.save(Mockito.any(Dueno::class.java))).thenReturn(returnObject)
        val response = duenoService.delete(newObject.id)
        Assertions.assertEquals(response, true)
    }
    @Test
    fun deleteIsFailed(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(duenoRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(duenoRepository.save(Mockito.any(Dueno::class.java))).thenReturn(returnObject)
            val response = duenoService.delete(newObject.id)
            Assertions.assertEquals(response, true)
        }
    }
}







