package com.ksulloa.distribuidora.repository

import com.ksulloa.distribuidora.model.Dueño
import org.springframework.data.jpa.repository.JpaRepository

interface DueñoRepository : JpaRepository<Dueño, Long> {
    fun findById(id: Long?): Dueño?
}