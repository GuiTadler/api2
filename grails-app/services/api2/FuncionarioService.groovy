package api2

import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.validation.BindingResult

class FuncionarioService {

    PlatformTransactionManager transactionManager

    def listaFuncionarios() {
        Funcionario.list()
    }

    def buscaFuncId(Long id) {
        def funcionario = Funcionario.get(id)
        validaFuncionario(funcionario, id)
        funcionario
    }

    def criaFuncionario(Map funcionarioDate) {
        validaDadosFuncionario(funcionarioDate)

        def funcionario = new Funcionario(funcionarioDate)
        realizarTransacao {
            funcionario.save()
        }
        funcionario
    }

    def atualizaFuncionario(Long id, Map funcionarioDate) {
        def funcionario = Funcionario.get(id)
        validaFuncionario(funcionario, id)

        funcionario.properties = funcionarioDate as BindingResult
        validaDadosFuncionario(funcionario.properties)

        realizarTransacao {
            funcionario.save()
        }
        funcionario
    }

    def excluiFuncionario(Long id) {
        def funcionario = Funcionario.get(id)
        validaFuncionario(funcionario, id)

        realizarTransacao {
            funcionario.delete()
        }
        funcionario
    }

    private static void validaFuncionario(def funcionario, Long id) {
        if (!funcionario) {
            throw new IllegalArgumentException("Funcionário de ID ${id} não encontrado!")
        }
    }

    private static void validaDadosFuncionario(Map funcionarioData) {
        def funcionario = new Funcionario(funcionarioData)
        funcionario.validate()
        if (funcionario.hasErrors()) {
            throw new IllegalArgumentException(funcionario.errors.toString())
        }
    }

    private void realizarTransacao(Closure closure) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager)
        transactionTemplate.execute { status ->
            closure.call(status)
        }
    }
}
