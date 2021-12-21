package com.ksulloa.distribuidora.model

import javax.persistence.*

@Entity
@Table(name = "dueno")

class Dueno {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var nombre: String? = null
    var apellido: String? = null
    var cedula: String? = null
    var telefono: String? = null


}