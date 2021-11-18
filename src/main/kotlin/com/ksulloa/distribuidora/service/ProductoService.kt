package com.ksulloa.distribuidora.service

import com.ksulloa.distribuidora.model.Producto
import com.ksulloa.distribuidora.repository.ProductoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductoService {
    @Autowired
    lateinit var productoRepository: ProductoRepository


    fun list(): List<Producto> {

        return productoRepository.findAll()
    }

    fun save(producto: Producto): Producto {
        return productoRepository.save(producto)
    }

    fun update(producto: Producto): Producto {
        return productoRepository.save(producto)

    }

    fun updateCantidad (producto: Producto):Producto {
        val response = productoRepository.findById(producto.id)
            ?: throw Exception()
        response.apply {
            this.cantidad=producto.cantidad
        }
        return productoRepository.save(response)
    }
    fun delete (id:Long): Boolean{
        productoRepository.deleteById(id)
        return true
    }
}