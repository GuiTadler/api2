package api2

import api2.traits.ExceptionHandlers
import grails.rest.*
import grails.converters.*

class ReajusteSalarioController implements ExceptionHandlers {

    ReajusteSalarioService reajusteSalarioService

    static responseFormats = ["json"]
    static defaultAction = "get"
    static allowedMethods = [
            save: "POST",
            list: "GET",
            update: "PUT",
            delete: "DELETE",
            get: "GET"
    ]

    def save() {
        Map retorno = reajusteSalarioService.save()
        respond(retorno)
    }

    def list() {
        Map retorno = reajusteSalarioService.list()
        respond(retorno)
    }

    def update() {
        Map retorno = reajusteSalarioService.update()
        respond(retorno)
    }

    def delete(Long id) {
        Map retorno = reajusteSalarioService.delete(id)
        respond(retorno)
    }

    def get(Long id) {
        Map retorno = reajusteSalarioService.get(id)
        respond(retorno)
    }

}