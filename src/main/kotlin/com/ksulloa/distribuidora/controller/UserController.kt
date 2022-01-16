package com.ksulloa.distribuidora.controller

import com.ksulloa.distribuidora.dto.AuthenticationRequest
import com.ksulloa.distribuidora.dto.AuthenticationResponse
import com.ksulloa.distribuidora.model.Dueno
import com.ksulloa.distribuidora.model.User
import com.ksulloa.distribuidora.service.AppUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import com.ksulloa.distribuidora.security.JWTUtil
import com.ksulloa.distribuidora.service.DuenoService
import com.ksulloa.distribuidora.service.UserService

@RestController
@RequestMapping("/users")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun list(): List<User> {
        return userService.list()

    }

    @PostMapping
    fun save(@RequestBody user: User): User {
        return userService.save(user)
    }

    @PutMapping
    fun update(@RequestBody user: User): User {
        return userService.update(user)

    }

    @PatchMapping
    fun updatePassword(@RequestBody user: User): User {
        return userService.updatePassword(user)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") id: Long): Boolean {
        return userService.delete(id)
    }


}


