package api2

class UrlMappings {

    static mappings = {

        // FUNCIONARIO
        delete "/funcionario/$id"(controller: "funcionario", action: "delete")
        get "/funcionario"(controller: "funcionario", action: "index")
        get "/funcionario/$id"(controller: "funcionario", action: "show")
        post "/funcionario"(controller: "funcionario", action: "save")
        put "/funcionario/$id"(controller: "funcionario", action: "update")

        // CIDADE
        delete "/cidade/$id"(controller: "cidade", action: "delete")
        get "/cidade"(controller: "cidade", action: "index")
        get "/cidade/$id"(controller: "cidade", action: "show")
        post "/cidade"(controller: "cidade", action: "save")
        put "/cidade/$id"(controller: "cidade", action: "update")

        // REAJUSTE
        delete "/reajusteSalario/$id"(controller: "reajusteSalario", action: "delete")
        get "/reajusteSalario"(controller: "reajusteSalario", action: "index")
        get "/reajusteSalario/$id"(controller: "reajusteSalario", action: "show")
        post "/reajusteSalario"(controller: "reajusteSalario", action: "save")
        put "/reajusteSalario/$id"(controller: "reajusteSalario", action: "update")

        //"/"(controller: 'application', action:'index')
        "/$controller/$action?/$id?"()
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
