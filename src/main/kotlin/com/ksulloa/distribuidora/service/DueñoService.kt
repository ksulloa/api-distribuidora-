package com.ksulloa.distribuidora.service

import com.ksulloa.distribuidora.model.Dueño
import com.ksulloa.distribuidora.repository.DistribuidoraRepository
import com.ksulloa.distribuidora.repository.DueñoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DueñoService {
    @Autowired
    lateinit var dueñoRepository: DueñoRepository

    @Autowired
    lateinit var distribuidoraRepository: DistribuidoraRepository

    fun list(): List<Dueño> {

        return dueñoRepository.findAll()
    }

    fun save(dueño: Dueño): Dueño {
      try {
          val response1 = distribuidoraRepository.findById(dueño.distribuidoraId)
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
            val response1 = distribuidoraRepository.findById(dueño.distribuidoraId)
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
             dueño.telefono?.takeIf {it.trim().isNotEmpty()}
                 ?: throw Exception("El campo telefono esta vacio")


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
