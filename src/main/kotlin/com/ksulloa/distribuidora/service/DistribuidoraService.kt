package com.ksulloa.distribuidora.service

import com.ksulloa.distribuidora.model.Distribuidora
import com.ksulloa.distribuidora.repository.DistribuidoraRepository
import com.ksulloa.distribuidora.repository.DuenoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DistribuidoraService {
    @Autowired
    lateinit var distribuidoraRepository: DistribuidoraRepository

    @Autowired
    lateinit var duenoRepository: DuenoRepository

    fun list(): List<Distribuidora> {

        return distribuidoraRepository.findAll()
    }

    fun save(distribuidora: Distribuidora): Distribuidora {
        try {
            val response = duenoRepository.findById(distribuidora.duenoId)
                ?: throw Exception("El ID ${distribuidora.duenoId}  no existe")

            distribuidora.nombre?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo nombre esta vacio")

            distribuidora.direccion?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo direccion esta vacio")


            distribuidora.categoria?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo categoria esta vacio")


            return distribuidoraRepository.save(distribuidora)

        } catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex
            )

        }
    }

    fun update(distribuidora: Distribuidora): Distribuidora {

        try {
            val response = duenoRepository.findById(distribuidora.duenoId)
                ?: throw Exception("El ID ${distribuidora.duenoId}  no existe")


            val response1 = distribuidoraRepository.findById(distribuidora.id)
                ?: throw Exception("El ID ${distribuidora.id}  no existe")

            distribuidora.nombre?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo nombre esta vacio")

            distribuidora.direccion?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo direccion esta vacio")


            distribuidora.categoria?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo categoria esta vacio")

            return distribuidoraRepository.save(distribuidora)

        } catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID no encontrado", ex
            )
        }
    }

    fun updateDireccion(distribuidora: Distribuidora): Distribuidora {
        try {
            distribuidora.direccion?.trim()?.isEmpty()
                ?: throw Exception("El campo se encuentra vacío")

            val response = distribuidoraRepository.findById(distribuidora.id)
                ?: throw Exception("El ID ${distribuidora.id}  no existe")
            response.apply {
                this.direccion = distribuidora.direccion
            }
            return distribuidoraRepository.save(response)

        } catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex
            )
        }
    }

    fun delete (id:Long?): Boolean{

        try {
            distribuidoraRepository.findById(id)
                ?: throw Exception("El ID de la distribuidora no existe")
            distribuidoraRepository.deleteById(id!!)
            return true

        }catch(ex: Exception){
            throw Exception()
        }
    }
}

