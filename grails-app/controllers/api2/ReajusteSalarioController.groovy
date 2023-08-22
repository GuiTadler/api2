package api2

import grails.converters.JSON

class ReajusteSalarioController {

    def ReajusteSalarioService

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
            def reajustes = ReajusteSalarioService.listaReajustes()
            render reajustes as JSON
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
            def reajuste = ReajusteSalarioService.buscaReajId(id)
            render reajuste as JSON
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
            def reajusteDate = request.JSON
            def reajuste = ReajusteSalarioService.criaReajuste(reajusteDate)
            render reajuste as JSON
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
            def reajusteDate = request.JSON
            def reajuste = ReajusteSalarioService.atualizaReajuste(id, reajusteDate)
            render reajuste as JSON
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
            def reajuste = ReajusteSalarioService.excluiReajuste(id)
            render reajuste as JSON
        } catch (Exception e) {
            handleControllerException(e)
        }
    }

    private void handleControllerException(Exception e) {
        def errorMessage = [
                message: "Reajuste Salarial de ID Não Encontrado Para Essa Operação!"
        ]
        render errorMessage as JSON
    }
}
