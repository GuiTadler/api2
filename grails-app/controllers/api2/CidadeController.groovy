package api2

import api2.traits.ExceptionHandlers

class CidadeController implements ExceptionHandlers {

    static responseFormats = ["json"]
    static defaultAction = "get"
    static allowedMethods = [
            save: "POST",
            list: "GET",
            update: "PUT",
            delete: "DELETE",
            get: "GET"
    ]

    CidadeService cidadeService

    def save() {
        Map retorno = cidadeService.save()
        respond(retorno)
    }

    def list() {
        Map retorno = cidadeService.list()
        respond(retorno)
    }

    def update() {
        Map retorno = cidadeService.update()
        respond(retorno)
    }

    def delete(Long id) {
        Map retorno = cidadeService.delete(id)
        respond(retorno)
    }

    def get() {
        Cidade retorno = cidadeService.get()
        respond(retorno)
    }

}