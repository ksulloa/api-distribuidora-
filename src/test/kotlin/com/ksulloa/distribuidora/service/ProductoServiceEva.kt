package com.ksulloa.distribuidora.service

import com.google.gson.Gson
import com.ksulloa.distribuidora.model.Distribuidora
import com.ksulloa.distribuidora.model.Producto
import com.ksulloa.distribuidora.repository.DistribuidoraRepository
import com.ksulloa.distribuidora.repository.ProductoRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class ProductoServiceEva {
    @InjectMocks
    lateinit var productoService: ProductoService

    @Mock
    lateinit var productoRepository: ProductoRepository

    @Mock
    lateinit var distribuidoraRepository: DistribuidoraRepository



    val returnObject: Producto = Producto().apply {
        id= 1
        nombre="Bombones"
        cantidad= "100"
        precio="1.50"
        categoria="Comida"
        distribuidoraId= 1
    }
    val newObject: Producto = Producto().apply {
        id= 1
        nombre="Bombones"
        cantidad= "100"
        precio="1.50"
        categoria="Comida"
        distribuidoraId= 1
    }

    val jsonString = File("./src/test/resources/producto/crearProducto.json").readText(Charsets.UTF_8)
    val productoMock = Gson().fromJson(jsonString, Producto::class.java)

    val jsonString1 = File("./src/test/resources/distribuidora/crearDistribuidora.json").readText(Charsets.UTF_8)
    val distribuidoraMock = Gson().fromJson(jsonString1, Distribuidora::class.java)


    @Test
    fun updateProductoIsCorrect() {
        Mockito.`when`(productoRepository.findById(returnObject.id)).thenReturn(returnObject)
        Mockito.`when`(distribuidoraRepository.findById(productoMock.distribuidoraId)).thenReturn(distribuidoraMock)
        Mockito.`when`(productoRepository.save(Mockito.any(Producto::class.java))).thenReturn(returnObject)
        val response = productoService.update(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.cantidad, newObject.cantidad)
        Assertions.assertEquals(response.precio, newObject.precio)
        Assertions.assertEquals(response.categoria, newObject.categoria)
        Assertions.assertEquals(response.distribuidoraId, newObject.distribuidoraId)
    }

    @Test
    fun updateProductoIsFailed() {
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(productoRepository.findById(returnObject.id)).thenReturn(returnObject)
            Mockito.`when`(productoRepository.save(Mockito.any(Producto::class.java))).thenReturn(returnObject)
            val response = productoService.update(newObject)
            Assertions.assertEquals(response.id, newObject.id)
        }
    }

    @Test
    fun updateProductoFailedNombre() {
        Assertions.assertThrows(Exception::class.java) {
            productoMock.apply { nombre = "    " }
            Mockito.`when`(productoRepository.save(Mockito.any(Producto::class.java))).thenReturn(productoMock)
            productoService.update(productoMock)
        }
    }

    @Test
    fun updateProductoFailedCantidad() {
        Assertions.assertThrows(Exception::class.java) {
            productoMock.apply { cantidad = "    " }
            Mockito.`when`(productoRepository.save(Mockito.any(Producto::class.java))).thenReturn(productoMock)
            productoService.update(productoMock)
        }
    }

    @Test
    fun updateProductoFailedPrecio() {
        Assertions.assertThrows(Exception::class.java) {
            productoMock.apply { precio = "    " }
            Mockito.`when`(productoRepository.save(Mockito.any(Producto::class.java))).thenReturn(productoMock)
            productoService.update(productoMock)
        }
    }

    @Test
    fun updateProductoFailedCategoria() {
        Assertions.assertThrows(Exception::class.java) {
            productoMock.apply { categoria = "    " }
            Mockito.`when`(productoRepository.save(Mockito.any(Producto::class.java))).thenReturn(productoMock)
            productoService.update(productoMock)
        }
    }
    @Test
    fun updateProductoPassedListCategoria() {
        Mockito.`when`(productoRepository.findById(returnObject.id)).thenReturn(returnObject)
        Mockito.`when`(productoRepository.save(Mockito.any(Producto::class.java))).thenReturn(returnObject)
        val response = productoService.update(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.categoria, newObject.categoria)

    }

    @Test
    fun updateProductoFailedListCategoria() {
        Assertions.assertThrows(Exception::class.java) {
            productoMock.apply { categoria = " " }

            Mockito.`when`(productoRepository.findById(returnObject.id)).thenReturn(returnObject)
            Mockito.`when`(productoRepository.save(Mockito.any(Producto::class.java))).thenReturn(returnObject)
            productoService.update(productoMock)
        }
    }
}