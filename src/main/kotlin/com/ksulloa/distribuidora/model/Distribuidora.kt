package com.ksulloa.distribuidora.model

import javax.persistence.*

@Entity
@Table(name = "distribuidora")

class Distribuidora {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var nombre: String? = null
    var direccion: String? = null
    var categoria: String? = null
    @Column(name = "dueno_id")
    var duenoId: Long? = null

}