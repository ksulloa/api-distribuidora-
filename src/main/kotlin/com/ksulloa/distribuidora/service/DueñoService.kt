package com.ksulloa.distribuidora.service

import com.ksulloa.distribuidora.model.Dueño
import com.ksulloa.distribuidora.repository.DueñoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DueñoService {
    @Autowired
    lateinit var dueñoRepository: DueñoRepository


    fun list(): List<Dueño> {

        return dueñoRepository.findAll()
    }

    fun save(dueño: Dueño): Dueño {
      try {
          val response = dueñoRepository.findById(dueño.distribuidoraId)
              ?: throw Exception("El ID ${dueño.distribuidoraId}  no existe")
          if (dueño.nombre.equals("") || dueño.apellido.equals("") || dueño.cedula.equals("") || dueño.telefono.equals("")) {
            throw Exception("Llenar los campos requeridos")
        } else {
            return dueñoRepository.save(dueño)
        }
    }
    catch(ex: Exception) {
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, ex.message, ex)
    }
        }
    fun update(dueño: Dueño):Dueño {
        try {
            val response = dueñoRepository.findById(dueño.id)
                ?: throw Exception("El ID ${dueño.id}  no existe")
            val response1 = dueñoRepository.findById(dueño.distribuidoraId)
                ?: throw Exception("El ID ${dueño.distribuidoraId}  no existe")

            if (dueño.nombre.equals("") || dueño.apellido.equals("") || dueño.cedula.equals("") || dueño.telefono.equals("")) {
                throw Exception("Llenar los campos requeridos")
            } else {
                return dueñoRepository.save(dueño)
            }
        }
        catch (ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID no encontrado", ex)
        }
    }

     fun updateTelefono (dueño: Dueño):Dueño {
         try {
             if (dueño.telefono.equals("")) {
                 throw Exception("El campo se encuentra vacío")
             }
             val response = dueñoRepository.findById(dueño.id)
                 ?: throw Exception("El ID ${dueño.id}  no existe")
             response.apply {
                 this.telefono = dueño.telefono
             }
             return dueñoRepository.save(response)
         }
         catch (ex: Exception) {
             throw ResponseStatusException(
                 HttpStatus.NOT_FOUND, ex.message, ex)
         }
     }


     fun delete (id:Long): Boolean{
         dueñoRepository.deleteById(id)
         return true
     }
}
