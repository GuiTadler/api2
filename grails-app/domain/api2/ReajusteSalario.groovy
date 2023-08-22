package api2

import com.fasterxml.jackson.annotation.JsonFormat
import grails.converters.JSON
import grails.gorm.annotation.Entity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity
class ReajusteSalario {

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataReajuste
    BigDecimal valorSalario
    Funcionario funcionario

    static mapping = {
        id generator: 'increment'
        version false
    }

    static {
        JSON.registerObjectMarshaller(LocalDate) { LocalDate date ->
            date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }
    }

    static constraints = {
        dataReajuste nullable: false
        valorSalario nullable: false, max: 6.2
        funcionario nullable: false
    }
}
