package com.ksulloa.distribuidora.controller


import com.ksulloa.distribuidora.model.Dueño
import com.ksulloa.distribuidora.service.DueñoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dueño")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class DueñoController {
    @Autowired
    lateinit var dueñoService: DueñoService

    @GetMapping
    fun list(): List<Dueño>{
        return dueñoService.list()
    }
    @PostMapping
    fun save(@RequestBody dueño: Dueño): Dueño {
        return dueñoService.save(dueño)

    }
    @PutMapping
    fun update(@RequestBody dueño: Dueño): Dueño {
        return  dueñoService.update(dueño)

    }
    @PatchMapping
    fun updateTelefono(@RequestBody dueño: Dueño): Dueño {
        return  dueñoService.updateTelefono(dueño)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean{
        return dueñoService.delete(id)
    }

}