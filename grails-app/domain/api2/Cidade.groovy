package api2

import grails.gorm.annotation.Entity

@Entity
class Cidade {

    String nome

    static mapping = {
        id generator: 'increment'
        version false
    }

    static constraints = {
        nome nullable: false, maxSize: 50
    }
}
