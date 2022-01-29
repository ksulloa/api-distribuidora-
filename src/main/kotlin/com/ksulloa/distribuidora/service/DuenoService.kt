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

              dueno.nombre?.takeIf {it.trim().isNotEmpty()}
                  ?: throw Exception("El campo nombre esta vacio")

              dueno.apellido?.takeIf {it.trim().isNotEmpty()}
                  ?: throw Exception("El campo apellido esta vacio")

              dueno.cedula?.takeIf {it.trim().isNotEmpty()}
                  ?: throw Exception("El campo cedula esta vacio")

              dueno.telefono?.takeIf {it.trim().isNotEmpty()}
                  ?: throw Exception("El campo telefono esta vacio")


            return duenoRepository.save(dueno)

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

            dueno.nombre?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo nombre esta vacio")

            dueno.apellido?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo apellido esta vacio")

            dueno.cedula?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo cedula esta vacio")

            dueno.telefono?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo telefono esta vacio")


                return duenoRepository.save(dueno)

        }
        catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
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


    fun delete (id:Long?): Boolean{

        try {
            duenoRepository.findById(id)
                ?: throw Exception("El ID del dueño no existe")
            duenoRepository.deleteById(id!!)
            return true

        }catch(ex: Exception){
            throw Exception()
        }
    }

    fun verifyWord(cedula: String?, telefono: String?):Boolean{
        if (cedula?.length!! ==10){
            return false
        }
        if (telefono?.length!! ==10 || telefono?.length!! ==7 ){
            return false
        }
        return true


}

}
