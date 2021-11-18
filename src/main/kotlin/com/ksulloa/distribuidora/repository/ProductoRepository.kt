package com.ksulloa.distribuidora.repository

import com.ksulloa.distribuidora.model.Producto
import org.springframework.data.jpa.repository.JpaRepository

interface ProductoRepository: JpaRepository<Producto, Long> {
    fun findById(id: Long?): Producto?

}