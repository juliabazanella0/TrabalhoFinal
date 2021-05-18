package com.example.trabalho_final.models

data class OutPerson(
    var status: String?,
    var data: DataPerson?,     //Objeto
    var message: String?
)

data class DataPerson(              //Pegando o objeto
    var user: PersonRegister?
)

data class PersonRegister(          //Registrar
    var email: String?,
    var name: String?,
    var token: String?
)