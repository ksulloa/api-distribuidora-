package com.ksulloa.distribuidora.service

import com.google.gson.Gson
import com.ksulloa.distribuidora.model.Distribuidora
import com.ksulloa.distribuidora.model.Dueno
import com.ksulloa.distribuidora.repository.DistribuidoraRepository
import com.ksulloa.distribuidora.repository.DuenoRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

import org.springframework.boot.test.context.SpringBootTest
import java.io.File


@SpringBootTest
class DistribuidoraServiceTest {

    @InjectMocks
    lateinit var distribuidoraService: DistribuidoraService

    @Mock
    lateinit var distribuidoraRepository: DistribuidoraRepository

    @Mock
    lateinit var duenoRepository: DuenoRepository

    val returnObject: Distribuidora = Distribuidora().apply {
        id = 1
        nombre = "Nestle"
        direccion = "Parque Industrial"
        categoria = "Comida"
        duenoId = 1
    }
    val newObject: Distribuidora = Distribuidora().apply {
        id = 1
        nombre = "Nestle"
        direccion = "Parque Industrial"
        categoria = "Comida"
        duenoId = 1
    }

    @Test
    fun saveIsCorrect() {
        Mockito.`when`(duenoRepository.findById(distribuidoraMock.duenoId)).thenReturn(duenoMock)

        Mockito.`when`(distribuidoraRepository.save(Mockito.any(Distribuidora::class.java))).thenReturn(returnObject)
        val response = distribuidoraService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.direccion, newObject.direccion)
        Assertions.assertEquals(response.categoria, newObject.categoria)
        Assertions.assertEquals(response.duenoId, newObject.duenoId)

    }

    val jsonString = File("./src/test/resources/distribuidora/crearDistribuidora.json").readText(Charsets.UTF_8)
    val distribuidoraMock = Gson().fromJson(jsonString, Distribuidora::class.java)

    val jsonString1 = File("./src/test/resources/dueno/crearDueno.json").readText(Charsets.UTF_8)
    val duenoMock = Gson().fromJson(jsonString1, Dueno::class.java)

    @Test
    fun saveDistribuidora() {
        //PAra actualizar

        Mockito.`when`(duenoRepository.findById(distribuidoraMock.duenoId)).thenReturn(duenoMock)

        Mockito.`when`(distribuidoraRepository.save(Mockito.any(Distribuidora::class.java)))
            .thenReturn(distribuidoraMock)
        val response = distribuidoraService.save(distribuidoraMock)
        Assertions.assertEquals(response.id, distribuidoraMock.id)
        Assertions.assertEquals(response.nombre, distribuidoraMock.nombre)
        Assertions.assertEquals(response.direccion, distribuidoraMock.direccion)
        Assertions.assertEquals(response.categoria, distribuidoraMock.categoria)
        Assertions.assertEquals(response.duenoId, distribuidoraMock.duenoId)
    }

    @Test
    fun saveDistribuidoraFailedNombre() {
        Assertions.assertThrows(Exception::class.java) {
            distribuidoraMock.apply { nombre = "    " }
            Mockito.`when`(distribuidoraRepository.save(Mockito.any(Distribuidora::class.java)))
                .thenReturn(distribuidoraMock)
            distribuidoraService.save(distribuidoraMock)
        }
    }

    @Test
    fun saveDistribuidoraFailedDireccion() {
        Assertions.assertThrows(Exception::class.java) {
            distribuidoraMock.apply { direccion = "    " }
            Mockito.`when`(distribuidoraRepository.save(Mockito.any(Distribuidora::class.java)))
                .thenReturn(distribuidoraMock)
            distribuidoraService.save(distribuidoraMock)
        }
    }

    @Test
    fun saveDistribuidoraoFailedCategoria() {
        Assertions.assertThrows(Exception::class.java) {
            distribuidoraMock.apply { categoria = "    " }
            Mockito.`when`(distribuidoraRepository.save(Mockito.any(Distribuidora::class.java)))
                .thenReturn(distribuidoraMock)
            distribuidoraService.save(distribuidoraMock)
        }
    }

    @Test
    fun updateDistribuidoraIsIdCorrect() {
        Mockito.`when`(distribuidoraRepository.findById(returnObject.id)).thenReturn(returnObject)
        Mockito.`when`(duenoRepository.findById(distribuidoraMock.duenoId)).thenReturn(duenoMock)
        Mockito.`when`(distribuidoraRepository.save(Mockito.any(Distribuidora::class.java))).thenReturn(returnObject)
        val response = distribuidoraService.update(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.direccion, newObject.direccion)
        Assertions.assertEquals(response.categoria, newObject.categoria)
        Assertions.assertEquals(response.duenoId, newObject.duenoId)

    }

    @Test
    fun updateDistribuidoraIsIdFailed() {
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(distribuidoraRepository.findById(returnObject.id)).thenReturn(returnObject)
            Mockito.`when`(distribuidoraRepository.save(Mockito.any(Distribuidora::class.java))).thenReturn(returnObject)
            val response = distribuidoraService.update(newObject)
            Assertions.assertEquals(response.id, newObject.id)

        }
    }

    @Test
    fun updateDistribuidoraFailedNombre() {
        Assertions.assertThrows(Exception::class.java) {
            distribuidoraMock.apply { nombre = "    " }
            Mockito.`when`(distribuidoraRepository.save(Mockito.any(Distribuidora::class.java))).thenReturn(distribuidoraMock)
            distribuidoraService.update(distribuidoraMock)
        }
    }

    @Test
    fun updateDistribuidoraFailedDireccion() {
        Assertions.assertThrows(Exception::class.java) {
            distribuidoraMock.apply { direccion = "    " }
            Mockito.`when`(distribuidoraRepository.save(Mockito.any(Distribuidora::class.java))).thenReturn(distribuidoraMock)
            distribuidoraService.update(distribuidoraMock)
        }
    }

    @Test
    fun updateDistribuidoraoFailedCategoria() {
        Assertions.assertThrows(Exception::class.java) {
            distribuidoraMock.apply { categoria = "    " }
            Mockito.`when`(distribuidoraRepository.save(Mockito.any(Distribuidora::class.java)))
                .thenReturn(distribuidoraMock)
            distribuidoraService.update(distribuidoraMock)
        }
    }

    @Test
    fun delete() {
        Mockito.`when`(distribuidoraRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(distribuidoraRepository.save(Mockito.any(Distribuidora::class.java))).thenReturn(returnObject)
        val response = distribuidoraService.delete(newObject.id)
        Assertions.assertEquals(response, true)
    }

    @Test
    fun deleteFailed() {
        Assertions.assertThrows(Exception::class.java) {
        Mockito.`when`(distribuidoraRepository.findById(newObject.id)).thenReturn(null)
        Mockito.`when`(distribuidoraRepository.save(Mockito.any(Distribuidora::class.java))).thenReturn(returnObject)
        val response = distribuidoraService.delete(newObject.id)
        Assertions.assertEquals(response, true)
    }
}
}




