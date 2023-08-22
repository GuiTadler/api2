package api2

import grails.converters.JSON

class ReajusteSalarioController {

    def ReajusteSalarioService

    def index() {
        if (request.method != "GET") {
            response.setStatus(405)
            render "Método Não Permitido - Exclusivo para Update | GET"
            return
        }

        def reajustes = ReajusteSalarioService.listaReajustes()
        render reajustes as JSON
    }

    def show(Long id) {
        if (request.method != "GET") {
            response.setStatus(405)
            render "Método Não Permitido - Exclusivo para Update | GET"
            return
        }

        def reajuste = ReajusteSalarioService.buscaReajId(id)
        render reajuste as JSON
    }

    def save() {
        if (request.method != "POST") {
            response.setStatus(405)
            render "Método Não Permitido - Exclusivo para Update | POST"
            return
        }

        def reajusteDate = request.JSON
        def reajuste = ReajusteSalarioService.criaReajuste(reajusteDate)
        render reajuste as JSON
    }

    def update(Long id) {
        if (request.method != "PUT") {
            response.setStatus(405)
            render "Método Não Permitido - Exclusivo para Update | PUT"
            return
        }

        def reajusteDate = request.JSON
        def reajuste = ReajusteSalarioService.atualizaReajuste(id, reajusteDate)
        render reajuste as JSON
    }

    def delete(Long id) {
        if (request.method != "DELETE") {
            response.setStatus(405)
            render "Método Não Permitido - Exclusivo para Update | DELETE"
            return
        }

        def reajuste = ReajusteSalarioService.excluiReajuste(id)
        render reajuste as JSON
    }
}