package api2

import grails.converters.JSON

class FuncionarioController {

    def FuncionarioService

    def index() {
        if (request.method != "GET") {
            response.setStatus(405)
            render "Método Não Permitido - Exclusivo para Update | GET"
            return
        }

        def funcionarios = FuncionarioService.listaFuncionarios()
        render funcionarios as JSON
    }

    def show(Long id) {
        if (request.method != "GET") {
            response.setStatus(405)
            render "Método Não Permitido - Exclusivo para Update | GET"
            return
        }

        def funcionario = FuncionarioService.buscaFuncId(id)
        render funcionario as JSON
    }

    def save() {
        if (request.method != "POST") {
            response.setStatus(405)
            render "Método Não Permitido - Exclusivo para Update | POST"
            return
        }

        def funcionarioDate = request.JSON
        def funcionario = FuncionarioService.criaFuncionario(funcionarioDate)
        render funcionario as JSON
    }

    def update(Long id) {
        if (request.method != "PUT") {
            response.setStatus(405)
            render "Método Não Permitido - Exclusivo para Update | PUT"
            return
        }

        def funcionarioDate = request.JSON
        def funcionario = FuncionarioService.atualizaFuncionario(id, funcionarioDate)
        render funcionario as JSON
    }

    def delete(Long id) {
        if (request.method != "DELETE") {
            response.setStatus(405)
            render "Método Não Permitido - Exclusivo para Update | DELETE"
            return
        }

        def funcionario = FuncionarioService.excluiFuncionario(id)
        render funcionario as JSON
    }
}
