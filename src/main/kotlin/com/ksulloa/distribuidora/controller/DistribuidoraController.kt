package com.ksulloa.distribuidora.controller

import com.ksulloa.distribuidora.model.Distribuidora
import com.ksulloa.distribuidora.service.DistribuidoraService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/distribuidora")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class DistribuidoraController {
    @Autowired
    lateinit var distribuidoraService: DistribuidoraService

    @GetMapping
    fun list(): List<Distribuidora>{
        return distribuidoraService.list()
    }
    @PostMapping
    fun save(@RequestBody distribuidora: Distribuidora):Distribuidora{
        return distribuidoraService.save(distribuidora)

    }
    @PutMapping
    fun update(@RequestBody distribuidora: Distribuidora): Distribuidora {
        return  distribuidoraService.update(distribuidora)

    }
    @PatchMapping
    fun updateDireccion(@RequestBody distribuidora: Distribuidora): Distribuidora{
        return  distribuidoraService.updateDireccion(distribuidora)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean {
        return distribuidoraService.delete(id)
    }

}