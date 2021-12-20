package com.ksulloa.distribuidora.service

import com.ksulloa.distribuidora.model.Distribuidora
import com.ksulloa.distribuidora.repository.DistribuidoraRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DistribuidoraService {
    @Autowired
    lateinit var distribuidoraRepository: DistribuidoraRepository

    fun list(): List<Distribuidora> {

        return distribuidoraRepository.findAll()
    }

    fun save(distribuidora: Distribuidora): Distribuidora {
        try {
            if (distribuidora.nombre.equals("") || distribuidora.direccion.equals("") || distribuidora.categoria.equals("")) {
                throw Exception("Llenar los campos requeridos")
            } else {
                return distribuidoraRepository.save(distribuidora)
            }
        }
            catch(ex: Exception) {
                throw ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.message, ex)

            }
        }

    fun update(distribuidora: Distribuidora): Distribuidora {

        try {
            val response = distribuidoraRepository.findById(distribuidora.id)
                ?: throw Exception("El ID ${distribuidora.id}  no existe")

            if (distribuidora.nombre.equals("") || distribuidora.direccion.equals("") || distribuidora.categoria.equals("")) {
                throw Exception("Llenar los campos requeridos")
            } else {
                return distribuidoraRepository.save(distribuidora)
            }
        }
        catch (ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID no encontrado", ex)
        }
    }

     fun updateDireccion (distribuidora: Distribuidora):Distribuidora {
         try {
             distribuidora.direccion?.trim()?.isEmpty()
                 ?: throw Exception("El campo se encuentra vacío")

             val response = distribuidoraRepository.findById(distribuidora.id)
                 ?: throw Exception("El ID ${distribuidora.id}  no existe")
             response.apply {
                 this.direccion = distribuidora.direccion
             }
             return distribuidoraRepository.save(response)

         }
         catch(ex: Exception){
             throw ResponseStatusException(
                 HttpStatus.NOT_FOUND, ex.message, ex)
     }
     }


     fun delete (id:Long): Boolean{
         distribuidoraRepository.deleteById(id)
         return true
     }

}