package api2

import grails.converters.JSON

class CidadeController {

    def CidadeService

    def index() {
        if (request.method != "GET") {
            response.setStatus(405)
            render "Método Não Permitido - Exclusivo para Update | GET"
            return
        }

        def cidades = CidadeService.listaCidades()
        render cidades as JSON
    }

    def show(Long id) {
        if (request.method != "GET") {
            response.setStatus(405)
            render "Método Não Permitido - Exclusivo para Update | GET"
            return
        }

        def cidade = CidadeService.buscarCidaId(id)
        render cidade as JSON
    }

    def save() {
        if (request.method != "POST") {
            response.setStatus(405)
            render "Método Não Permitido - Exclusivo para Update | POST"
            return
        }

        def cidadeDate = request.JSON
        def cidade = CidadeService.criarCidade(cidadeDate)
        render cidade as JSON
    }

    def update(Long id) {
        if (request.method != "PUT") {
            response.setStatus(405)
            render "Método Não Permitido - Exclusivo para Update | PUT"
            return
        }

        def cidadeData = request.JSON
        def cidade = CidadeService.atualizarCidade(id, cidadeData)
        render cidade as JSON
    }

    def delete(Long id) {
        if (request.method != "DELETE") {
            response.setStatus(405)
            render "Método Não Permitido - Exclusivo para Update | DELETE"
            return
        }

        def cidade = CidadeService.excluiCidade(id)
        render cidade as JSON
    }
}