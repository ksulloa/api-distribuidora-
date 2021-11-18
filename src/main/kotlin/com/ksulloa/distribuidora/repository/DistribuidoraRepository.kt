package com.ksulloa.distribuidora.repository

import com.ksulloa.distribuidora.model.Distribuidora
import org.springframework.data.jpa.repository.JpaRepository

interface DistribuidoraRepository: JpaRepository<Distribuidora, Long> {
    fun findById(id: Long?): Distribuidora?
}