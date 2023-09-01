package api2


class BootStrap {

    InicializaDataBaseService inicializaDataBaseService

    def init = { servletContext ->
        inicializaDataBaseService.Inicializa()
    }

    def destroy = {
    }

}
