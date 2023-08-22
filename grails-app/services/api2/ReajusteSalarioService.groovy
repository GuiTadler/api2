package api2

import org.springframework.validation.BindingResult
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate

import java.time.format.DateTimeParseException

class ReajusteSalarioService {

    PlatformTransactionManager transactionManager

    def listaReajustes() {
        ReajusteSalario.list()
    }

    def buscaReajId(Long id) {
        def reajuste = ReajusteSalario.get(id)
        validarExistenciaReajuste(reajuste, id)
        reajuste
    }

    def criaReajuste(Map reajusteData) {
        def reajuste = criarReajusteAPartirDosDados(reajusteData)
        realizarTransacao {
            reajuste.save()
        }
        reajuste
    }

    def atualizaReajuste(Long id, Map reajusteData) {
        def reajuste = ReajusteSalario.get(id)
        validarExistenciaReajuste(reajuste, id)

        reajuste.properties = criarReajusteAPartirDosDados(reajusteData) as BindingResult
        realizarTransacao {
            reajuste.save()
        }
        reajuste
    }

    def excluiReajuste(Long id) {
        def reajuste = ReajusteSalario.get(id)
        validarExistenciaReajuste(reajuste, id)

        realizarTransacao {
            reajuste.delete()
        }
        reajuste
    }

    private static void validarExistenciaReajuste(def reajuste, Long id) {
        if (!reajuste) {
            throw new IllegalArgumentException("Reajuste de ID ${id} não encontrado!")
        }
    }

    private Map criarReajusteAPartirDosDados(Map reajusteData) {
        def dataReajusteString = reajusteData.dataReajuste
        def formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        try {
            def dataReajuste = LocalDate.parse(dataReajusteString, formatter)
            reajusteData.dataReajuste = dataReajuste.format(formatter)
        } catch (DateTimeParseException e) {
            println("Erro ao analisar a data: ${e.message}")
            throw e
        }

        def reajuste = new ReajusteSalario(reajusteData)
        reajuste.validate()

        if (reajuste.hasErrors()) {
            throw new IllegalArgumentException(reajuste.errors.toString())
        }

        reajuste as Map
    }

    private void realizarTransacao(Closure closure) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager)
        transactionTemplate.execute { status ->
            closure.call(status)
        }
    }
}
