package com.ksulloa.distribuidora.controller


import com.ksulloa.distribuidora.model.Dueno
import com.ksulloa.distribuidora.service.DuenoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dueno")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class DuenoController {
    @Autowired
    lateinit var duenoService: DuenoService

    @GetMapping
    fun list(): List<Dueno>{
        return duenoService.list()
    }
    @PostMapping
    fun save(@RequestBody dueno: Dueno): Dueno {
        return duenoService.save(dueno)

    }
    @PutMapping
    fun update(@RequestBody dueno: Dueno): Dueno {
        return  duenoService.update(dueno)

    }
    @PatchMapping
    fun updateTelefono(@RequestBody dueno: Dueno): Dueno {
        return  duenoService.updateTelefono(dueno)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean{
        return duenoService.delete(id)
    }

}