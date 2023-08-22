package api2

import grails.converters.JSON

class CidadeController {

    def CidadeService

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
            def cidades = CidadeService.listaCidades()
            render cidades as JSON
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
            def cidade = CidadeService.buscarCidaId(id)
            render cidade as JSON
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
            def cidadeData = request.JSON
            def cidade = CidadeService.criarCidade(cidadeData)
            render cidade as JSON
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
            def cidadeData = request.JSON
            def cidade = CidadeService.atualizarCidade(id, cidadeData)
            render cidade as JSON
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
            def cidade = CidadeService.excluiCidade(id)
            render cidade as JSON
        } catch (Exception e) {
            handleControllerException(e)
        }
    }

    private void handleControllerException(Exception e) {
        def errorMessage = [
                message: "Cidade de ID Não Encontrado Para Essa Operação!"
        ]
        render errorMessage as JSON
    }
}
