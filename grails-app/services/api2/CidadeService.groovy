package api2

import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.support.TransactionTemplate

class CidadeService {

    PlatformTransactionManager transactionManager

    def listarCidades() {
        Cidade.list()
    }

    def buscarCidadePorId(Long id) {
        def cidade = Cidade.get(id)
        if (!cidade) {
            throw new IllegalArgumentException("cidade com ID ${id} não encontrado.")
        }
        cidade
    }

    def criarCidade(Map cidadeData) {
        def cidade = new Cidade(cidadeData)
        cidade.validate()

        if (cidade.hasErrors()) {
            throw new IllegalArgumentException(cidade.errors.toString())
        }

        realizarTransacao { status ->
            cidade.save()
        }

        cidade
    }

    def atualizarCidade(Long id, Map cidadeData) {
        def cidade = Cidade.get(id)
        if (!cidade) {
            throw new IllegalArgumentException("cidade com ID ${id} não encontrado.")
        }

        cidade.properties = cidadeData
        cidade.validate()

        if (cidade.hasErrors()) {
            throw new IllegalArgumentException(cidade.errors.toString())
        }

        realizarTransacao { status ->
            cidade.save()
        }

        cidade
    }

    def excluirCidade(Long id) {
        def cidade = Cidade.get(id)
        if (!cidade) {
            throw new IllegalArgumentException("cidade com ID ${id} não encontrado.")
        }

        realizarTransacao { status ->
            cidade.delete()
        }

        cidade
    }

    private realizarTransacao(Closure closure) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager)
        transactionTemplate.execute { status ->
            closure.call(status)
        }
    }
}