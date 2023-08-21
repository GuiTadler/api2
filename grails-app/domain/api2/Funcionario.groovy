package api2

import grails.gorm.annotation.Entity

@Entity
class Funcionario {

    String nome
    Cidade cidade

    static mapping = {
        id generator: 'increment'
        version false
    }

    static constraints = {
        nome nullable: false, maxSize: 50
        cidade nullable: false
    }
}
