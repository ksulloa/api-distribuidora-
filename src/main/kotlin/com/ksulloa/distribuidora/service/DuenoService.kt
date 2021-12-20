package com.ksulloa.distribuidora.service

import com.ksulloa.distribuidora.model.Dueno
import com.ksulloa.distribuidora.repository.DistribuidoraRepository
import com.ksulloa.distribuidora.repository.DuenoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DuenoService {
    @Autowired
    lateinit var duenoRepository: DuenoRepository

    @Autowired
    lateinit var distribuidoraRepository: DistribuidoraRepository

    fun list(): List<Dueno> {

        return duenoRepository.findAll()
    }

    fun save(dueno: Dueno): Dueno {
      try {
          val response1 = distribuidoraRepository.findById(dueno.distribuidoraId)
              ?: throw Exception("El ID ${dueno.distribuidoraId}  no existe")


          if (dueno.nombre.equals("") || dueno.apellido.equals("") || dueno.cedula.equals("") || dueno.telefono.equals("")) {
            throw Exception("Llenar los campos requeridos")
        } else {
            return duenoRepository.save(dueno)
        }
    }
    catch(ex: Exception) {
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, ex.message, ex)
    }
        }
    fun update(dueno: Dueno):Dueno {
        try {
            val response = duenoRepository.findById(dueno.id)
                ?: throw Exception("El ID ${dueno.id}  no existe")
            val response1 = distribuidoraRepository.findById(dueno.distribuidoraId)
                ?: throw Exception("El ID ${dueno.distribuidoraId}  no existe")

            if (dueno.nombre.equals("") || dueno.apellido.equals("") || dueno.cedula.equals("") || dueno.telefono.equals("")) {
                throw Exception("Llenar los campos requeridos")
            } else {
                return duenoRepository.save(dueno)
            }
        }
        catch (ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID no encontrado", ex)
        }
    }

     fun updateTelefono (dueno: Dueno):Dueno {
         try {
             dueno.telefono?.takeIf {it.trim().isNotEmpty()}
                 ?: throw Exception("El campo telefono esta vacio")


             val response = duenoRepository.findById(dueno.id)
                 ?: throw Exception("El ID ${dueno.id}  no existe")
             response.apply {
                 this.telefono = dueno.telefono
             }
             return duenoRepository.save(response)
         }
         catch (ex: Exception) {
             throw ResponseStatusException(
                 HttpStatus.NOT_FOUND, ex.message, ex)
         }
     }


     fun delete (id:Long): Boolean{
         duenoRepository.deleteById(id)
         return true
     }
}
