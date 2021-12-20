package com.ksulloa.distribuidora.repository

import com.ksulloa.distribuidora.model.Dueno
import org.springframework.data.jpa.repository.JpaRepository

interface DuenoRepository : JpaRepository<Dueno, Long> {
    fun findById(id: Long?): Dueno?
}