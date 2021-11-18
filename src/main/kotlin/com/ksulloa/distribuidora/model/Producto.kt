package com.ksulloa.distribuidora.model

import javax.persistence.*

@Entity
@Table(name = "producto")

class Producto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var nombre: String? = null
    var cantidad: String? = null
    var precio: String? = null
    @Column(name = "dueño_id")
    var dueñoId: Long? = null

}