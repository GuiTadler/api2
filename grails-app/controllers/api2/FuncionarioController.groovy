package api2

import grails.converters.JSON

class FuncionarioController {

    def FuncionarioService

    def index() {
        if (request.method != "GET") {
            response.setStatus(405)
            def errorMessage = [
                    message: "Método Não Permitido - Exclusivo para Update | GET"
            ]
            render errorMessage as JSON
            return
        }

        try {
            def funcionarios = FuncionarioService.listaFuncionarios()
            render funcionarios as JSON
        } catch (Exception e) {
            handleControllerException(e)
        }
    }

    def show(Long id) {
        if (request.method != "GET") {
            response.setStatus(405)
            def errorMessage = [
                    message: "Método Não Permitido - Exclusivo para Update | GET"
            ]
            render errorMessage as JSON
            return
        }

        try {
            def funcionario = FuncionarioService.buscaFuncId(id)
            render funcionario as JSON
        } catch (Exception e) {
            handleControllerException(e)
        }
    }

    def save() {
        if (request.method != "POST") {
            response.setStatus(405)
            def errorMessage = [
                    message: "Método Não Permitido - Exclusivo para Update | POST"
            ]
            render errorMessage as JSON
            return
        }

        try {
            def funcionarioDate = request.JSON
            def funcionario = FuncionarioService.criaFuncionario(funcionarioDate)
            render funcionario as JSON
        } catch (Exception e) {
            handleControllerException(e)
        }
    }

    def update(Long id) {
        if (request.method != "PUT") {
            response.setStatus(405)
            def errorMessage = [
                    message: "Método Não Permitido - Exclusivo para Update | PUT"
            ]
            render errorMessage as JSON
            return
        }

        try {
            def funcionarioDate = request.JSON
            def funcionario = FuncionarioService.atualizaFuncionario(id, funcionarioDate)
            render funcionario as JSON
        } catch (Exception e) {
            handleControllerException(e)
        }
    }

    def delete(Long id) {
        if (request.method != "DELETE") {
            response.setStatus(405)
            def errorMessage = [
                    message: "Método Não Permitido - Exclusivo para Update | DELETE"
            ]
            render errorMessage as JSON
            return
        }

        try {
            def funcionario = FuncionarioService.excluiFuncionario(id)
            render funcionario as JSON
        } catch (Exception e) {
            handleControllerException(e)
        }
    }

    private void handleControllerException(Exception e) {
        def errorMessage = [
                message: "Funcionário de ID Não Encontrado Para Essa Operação!"
        ]
        render errorMessage as JSON
    }
}
