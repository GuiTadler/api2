package api2

import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate

import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReajusteSalarioService {

    PlatformTransactionManager transactionManager

    def listarReajustes() {
        ReajusteSalario.list()
    }

    def buscarReajustePorId(Long id) {
        def reajuste = ReajusteSalario.get(id)
        if (!reajuste) {
            throw new IllegalArgumentException("Reajuste com ID ${id} não encontrado.")
        }
        reajuste
    }

    def criarReajuste(Map reajusteData) {
        // Converter a string de data para LocalDate
        def dataReajusteString = reajusteData.dataReajuste
        def formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        def dataReajuste = LocalDate.parse(dataReajusteString, formatter)

        reajusteData.dataReajuste = dataReajuste

        def reajuste = new ReajusteSalario(reajusteData)
        reajuste.validate()

        if (reajuste.hasErrors()) {
            throw new IllegalArgumentException(reajuste.errors.toString())
        }


        realizarTransacao { status ->
            reajuste.save()
        }
        reajuste
    }

    def atualizarReajuste(Long id, Map reajusteData) {
        def reajuste = ReajusteSalario.get(id)
        if (!reajuste) {
            throw new IllegalArgumentException("Reajuste com ID ${id} não encontrado.")
        }

        // Converter a string de data para LocalDate
        def dataReajusteString = reajusteData.dataReajuste
        def formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        def dataReajuste = LocalDate.parse(dataReajusteString, formatter)

        reajusteData.dataReajuste = dataReajuste

        reajuste.properties = reajusteData
        reajuste.validate()

        if (reajuste.hasErrors()) {
            throw new IllegalArgumentException(reajuste.errors.toString())
        }


        realizarTransacao { status ->
            reajuste.save()
        }
        reajuste
    }

    def excluirReajuste(Long id) {
        def reajuste = ReajusteSalario.get(id)
        if (!reajuste) {
            throw new IllegalArgumentException("Reajuste com ID ${id} não encontrado.")
        }

        realizarTransacao { status ->
            reajuste.delete()
        }
        reajuste
    }

    private realizarTransacao(Closure closure) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager)
        transactionTemplate.execute { status ->
            closure.call(status)
        }
    }
}