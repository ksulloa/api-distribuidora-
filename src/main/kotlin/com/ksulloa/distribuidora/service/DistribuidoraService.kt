package com.ksulloa.distribuidora.service0

import com.ksulloa.distribuidora.model.Distribuidora
import com.ksulloa.distribuidora.repository.DistribuidoraRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DistribuidoraService {
    @Autowired
    lateinit var distribuidoraRepository: DistribuidoraRepository


    fun list(): List<Distribuidora> {

        return distribuidoraRepository.findAll()
    }

    fun save(distribuidora: Distribuidora): Distribuidora {
        return distribuidoraRepository.save(distribuidora)
    }

    fun update(distribuidora: Distribuidora): Distribuidora {
        return distribuidoraRepository.save(distribuidora)

    }

     fun updateDireccion (distribuidora: Distribuidora):Distribuidora {
         val response = distribuidoraRepository.findById(distribuidora.id)
             ?: throw Exception()
         response.apply {
             this.direccion=distribuidora.direccion
         }
         return distribuidoraRepository.save(response)
     }
     fun delete (id:Long): Boolean{
         distribuidoraRepository.deleteById(id)
         return true
     }
}