package api2

import grails.gorm.annotation.Entity
import java.time.LocalDate

@Entity
class ReajusteSalario {

    LocalDate dataReajuste
    BigDecimal valorSalario
    Funcionario funcionario

    static mapping = {
        id generator: 'increment'
        version false
    }

    static constraints = {
        dataReajuste nullable: false
        valorSalario nullable: false, max: 6.2
        funcionario nullable: false
    }
}
