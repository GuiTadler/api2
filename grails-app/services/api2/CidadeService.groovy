package api2

import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.validation.BindingResult

class CidadeService {

    PlatformTransactionManager transactionManager

    def listaCidades() {
        Cidade.list()
    }

    def buscarCidaId(Long id) {
        def cidade = Cidade.get(id)
        validarExistenciaCidade(cidade, id)
        cidade
    }

    def criarCidade(Map cidadeData) {
        validarDadosCidade(cidadeData)

        def cidade = new Cidade(cidadeData)
        realizarTransacao {
            cidade.save()
        }
        cidade
    }

    def atualizarCidade(Long id, Map cidadeData) {
        def cidade = Cidade.get(id)

        validarExistenciaCidade(cidade, id)
        cidade.properties = cidadeData as BindingResult
        validarDadosCidade(cidade.properties)

        realizarTransacao {
            cidade.save()
        }
        cidade
    }

    def excluiCidade(Long id) {
        def cidade = Cidade.get(id)
        validarExistenciaCidade(cidade, id)
        realizarTransacao {
            cidade.delete()
        }
        cidade
    }

    private static void validarExistenciaCidade(def cidade, Long id) {
        if (!cidade) {
            throw new IllegalArgumentException("Cidade de ID ${id} não encontrada!")
        }
    }

    private static void validarDadosCidade(Map cidadeData) {
        def cidade = new Cidade(cidadeData)
        cidade.validate()
        if (cidade.hasErrors()) {
            throw new IllegalArgumentException(cidade.errors.toString())
        }
    }

    private void realizarTransacao(Closure closure) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager)
        transactionTemplate.execute { status ->
            closure.call(status)
        }
    }
}
