package api2

import grails.converters.JSON

class CidadeController {

    def CidadeService

    def index() {
        if (request.method != "GET") {
            response.setStatus(405)
            render "Método não permitido para esta ação."
            return
        }

        def cidades = cidadeService.listarCidades()
        render cidades as JSON
    }

    def show(Long id) {
        if (request.method != "GET") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação."
            return
        }

        def cidade = cidadeService.buscarCidadePorId(id)
        render cidade as JSON
    }

    def save() {
        if (request.method != "POST") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação."
            return
        }

        def cidadeData = request.JSON
        def cidade = cidadeService.criarCidade(cidadeData)
        render cidade as JSON
    }

    def update(Long id) {
        if (request.method != "PUT") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação."
            return
        }

        def cidadeData = request.JSON
        def cidade = cidadeService.atualizarCidade(id, cidadeData)
        render cidade as JSON
    }

    def delete(Long id) {
        if (request.method != "DELETE") {
            response.setStatus(405) // Método não permitido
            render "Método não permitido para esta ação."
            return
        }

        def cidade = cidadeService.excluirCidade(id)
        render cidade as JSON
    }
}