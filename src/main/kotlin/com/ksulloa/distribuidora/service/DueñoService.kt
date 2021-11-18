package com.ksulloa.distribuidora.service

import com.ksulloa.distribuidora.model.Dueño
import com.ksulloa.distribuidora.repository.DueñoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DueñoService {
    @Autowired
    lateinit var dueñoRepository: DueñoRepository


    fun list(): List<Dueño> {

        return dueñoRepository.findAll()
    }

    fun save(dueño: Dueño): Dueño {
        return dueñoRepository.save(dueño)
    }

    fun update(dueño: Dueño):Dueño {
        return dueñoRepository.save(dueño)

    }

     fun updateNombre (dueño: Dueño):Dueño {
         val response = dueñoRepository.findById(dueño.id)
             ?: throw Exception()
         response.apply {
             this.nombre=dueño.nombre
         }
         return dueñoRepository.save(response)
     }
     fun delete (id:Long): Boolean{
         dueñoRepository.deleteById(id)
         return true
     }
}
