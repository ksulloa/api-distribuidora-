package com.ksulloa.distribuidora.service

import com.ksulloa.distribuidora.model.Producto
import com.ksulloa.distribuidora.repository.ProductoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductoService {
    @Autowired
    lateinit var productoRepository: ProductoRepository


    fun list(): List<Producto> {
        return productoRepository.findAll()
    }

    fun save(producto: Producto): Producto {
        try {
            if (producto.nombre.equals("") || producto.cantidad.equals("") || producto.precio.equals("") || producto.categoria.equals("")) {
                throw Exception("Llenar los campos requeridos")
            } else {
                return productoRepository.save(producto)
            }
        }
        catch(ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun update(producto: Producto): Producto {
        try {
            val response = productoRepository.findById(producto.id)
                ?: throw Exception("El ID ${producto.id}  no existe")
            val response1 = productoRepository.findById(producto.dueñoId)
                ?: throw Exception("El ID ${producto.dueñoId}  no existe")

       if (producto.cantidad!! > "100" && producto.cantidad!! < "500" ){
           throw Exception("Los productos se deben llevar al por mayor de 100 a 500")
       }
        if (producto.nombre.equals("") || producto.cantidad.equals("") || producto.precio.equals("") || producto.categoria.equals("") ){
            throw Exception("Llenar los campos requeridos")
        }
        else{
            return productoRepository.save(producto)
        }
        }
        catch (ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID no encontrado", ex)
        }
    }

    fun updateCantidad (producto: Producto):Producto {
        try {
            if (producto.cantidad.equals("")){
                throw Exception("El campo se encuentra vacío")
            }
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

    fun delete (id:Long): Boolean{
        productoRepository.deleteById(id)
        return true
    }
}