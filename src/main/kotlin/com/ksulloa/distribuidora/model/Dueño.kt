package com.ksulloa.distribuidora.model

import javax.persistence.*

@Entity
@Table(name = "dueño")

class Dueño {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var nombre: String? = null
    var apellido: String? = null
    @Column(name = "distribuidora_id")
    var distribuidoraId: Long? = null

}