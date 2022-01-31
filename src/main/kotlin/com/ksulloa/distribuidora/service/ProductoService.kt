package com.ksulloa.distribuidora.service

import com.ksulloa.distribuidora.model.Producto
import com.ksulloa.distribuidora.repository.DistribuidoraRepository
import com.ksulloa.distribuidora.repository.DuenoRepository
import com.ksulloa.distribuidora.repository.ProductoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductoService {
    @Autowired
    lateinit var productoRepository: ProductoRepository

    @Autowired
    lateinit var distribuidoraRepository: DistribuidoraRepository

    val listaCategoria= listOf<String>("alimentos","cosmeticos","repuestos")


    fun list(): List<Producto> {
        return productoRepository.findAll()
    }

    fun save(producto: Producto): Producto {
        try {

            val response = distribuidoraRepository.findById(producto.distribuidoraId)
                ?: throw Exception("El ID ${producto.distribuidoraId}  no existe")

            producto.nombre?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo nombre esta vacio")

            producto.cantidad?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo cantidad esta vacio")

            producto.precio?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo precio esta vacio")

            producto.categoria?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo categoria esta vacio")


                return productoRepository.save(producto)

        }
        catch(ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun update(producto: Producto): Producto {
        try {
            val response1 = distribuidoraRepository.findById(producto.distribuidoraId)
                ?: throw Exception("El ID ${producto.distribuidoraId}  no existe")

            val response = productoRepository.findById(producto.id)
                ?: throw Exception("El ID ${producto.id}  no existe")

            producto.nombre?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo nombre esta vacio")

            producto.cantidad?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo cantidad esta vacio")

            producto.precio?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo precio esta vacio")

            producto.categoria?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo categoria esta vacio")

       if (producto.cantidad!! > "100" && producto.cantidad!! < "500" ) {
           throw Exception("Los productos se deben llevar al por mayor de 100 a 500")
       }
           if (!validarProducto(Producto.categoria!!)) {
               throw Exception("El campo de categoria no pertenece a la lista")
           }

            return productoRepository.save(producto)

        }
        catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }

    }

    fun updateCantidad (producto: Producto):Producto {
        try {
            producto.cantidad?.trim()?.isEmpty()
                ?: throw Exception("El campo se encuentra vacío")


            val response = productoRepository.findById(producto.id)
                ?: throw Exception("El ID ${producto.id}  no existe")
            response.apply {
                this.cantidad = producto.cantidad
            }
            return productoRepository.save(response)
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }
        fun validarProducto(categoria: String):Boolean{
          for (i in listaCategoria){
              if (categoria == i){
                  return true
              }
          }
        return false

        }


    fun delete (id:Long?): Boolean{

        try {
            productoRepository.findById(id)
                ?: throw Exception("El ID del producto no existe")
            productoRepository.deleteById(id!!)
            return true

        }catch(ex: Exception){
            throw Exception()
        }
    }
}