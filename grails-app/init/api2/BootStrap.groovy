package api2

import groovy.sql.Sql
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BootStrap {
    PlatformTransactionManager transactionManager

    def dataSource // Faz o insert nas tabelas como solicitado

    def init = { servletContext ->
        performTransaction { status ->
            Sql sql = new Sql(dataSource)

            // Inserir cidades
            sql.executeInsert("INSERT INTO cidade (id, nome) VALUES (?, ?)", [1, "Novo Hanburgo"])
            sql.executeInsert("INSERT INTO cidade (id, nome) VALUES (?, ?)", [2, "Porto Alegre"])
            sql.executeInsert("INSERT INTO cidade (id, nome) VALUES (?, ?)", [3, "Nova York"])

            // Inserir funcionários
            sql.executeInsert("INSERT INTO funcionario (id, nome, cidade_id) VALUES (?, ?, ?)", [1,"Guilherme", 1])
            sql.executeInsert("INSERT INTO funcionario (id, nome, cidade_id) VALUES (?, ?, ?)", [2,"Enner Valencia", 3])


            // Inserir reajustes de salário
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            LocalDate data = LocalDate.parse('05/05/2002', formatter)
            sql.executeInsert("INSERT INTO reajuste_salario (id, data_reajuste, valor_salario, funcionario_id) VALUES (?, ?, ?, ?)", [1, data, 1890.00, 2])

            sql.close()
        }
    }

    private performTransaction(Closure closure) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager)
        transactionTemplate.execute { status ->
            closure.call(status)
        }
    }

    def destroy = {

    }
}
