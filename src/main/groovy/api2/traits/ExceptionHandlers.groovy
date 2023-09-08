package api2.traits

import grails.artefact.controller.RestResponder
import javassist.NotFoundException

import java.time.format.DateTimeParseException

trait ExceptionHandlers implements RestResponder {

    def handleNullPointerException(NullPointerException e) {
        respond([message: e.getMessage() ?: "NullPointerException"], status: 400)
    }

    def handleNotFoundException(NotFoundException e) {
        respond([message: e.getMessage()], status: 404)
    }

    def handleNumberFormatException(NumberFormatException e) {
        respond([message: "Formato inválido"], status: 400)
    }

    def handleDateTimeParseException(DateTimeParseException e) {
        respond([message: "Formato inválido de data inválida"], status: 420)
    }

    def handleException(Exception e) {
        respond([message: "Ocorreu um erro no servidor, contate o administrador!"], status: 400)
    }
}
