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
class ProductoServiceTest {

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

    @Test
    fun saveIsCorrect(){
        Mockito.`when`(distribuidoraRepository.findById(productoMock.distribuidoraId)).thenReturn(distribuidoraMock)

        Mockito.`when`(productoRepository.save(Mockito.any(Producto::class.java))).thenReturn(returnObject)
        val response = productoService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.cantidad, newObject.cantidad)
        Assertions.assertEquals(response.precio, newObject.precio)
        Assertions.assertEquals(response.categoria, newObject.categoria)
        Assertions.assertEquals(response.distribuidoraId, newObject.distribuidoraId)

    }
    val jsonString = File("./src/test/resources/producto/crearProducto.json").readText(Charsets.UTF_8)
    val productoMock = Gson().fromJson(jsonString, Producto::class.java)

    val jsonString1 = File("./src/test/resources/distribuidora/crearDistribuidora.json").readText(Charsets.UTF_8)
    val distribuidoraMock = Gson().fromJson(jsonString1, Distribuidora::class.java)

    @Test
    fun saveProducto(){
        //PAra actualizar
        Mockito.`when`(distribuidoraRepository.findById(productoMock.distribuidoraId)).thenReturn(distribuidoraMock)

        Mockito.`when`(productoRepository.save(Mockito.any(Producto::class.java))).thenReturn(productoMock)
        val response = productoService.save(productoMock)
        Assertions.assertEquals(response.id, productoMock.id)
        Assertions.assertEquals(response.nombre, productoMock.nombre)
        Assertions.assertEquals(response.cantidad, productoMock.cantidad)
        Assertions.assertEquals(response.precio, productoMock.precio)
        Assertions.assertEquals(response.categoria, productoMock.categoria)
        Assertions.assertEquals(response.distribuidoraId, productoMock.distribuidoraId)
    }

    @Test
    fun saveProductoFailedNombre() {
        Assertions.assertThrows(Exception::class.java) {
            productoMock.apply { nombre = "    " }
            Mockito.`when`(productoRepository.save(Mockito.any(Producto::class.java))).thenReturn(productoMock)
            productoService.save(productoMock)
        }
    }

    @Test
    fun saveProductoFailedCantidad() {
        Assertions.assertThrows(Exception::class.java) {
            productoMock.apply { cantidad = "    " }
            Mockito.`when`(productoRepository.save(Mockito.any(Producto::class.java))).thenReturn(productoMock)
            productoService.save(productoMock)
        }
    }

    @Test
    fun saveProductoFailedPrecio() {
        Assertions.assertThrows(Exception::class.java) {
            productoMock.apply { precio = "    " }
            Mockito.`when`(productoRepository.save(Mockito.any(Producto::class.java))).thenReturn(productoMock)
            productoService.save(productoMock)
        }
    }

    @Test
    fun saveProductoFailedCategoria() {
        Assertions.assertThrows(Exception::class.java) {
            productoMock.apply { categoria = "    " }
            Mockito.`when`(productoRepository.save(Mockito.any(Producto::class.java))).thenReturn(productoMock)
            productoService.save(productoMock)
        }
    }

}







