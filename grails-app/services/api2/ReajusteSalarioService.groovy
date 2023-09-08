package api2

import grails.gorm.transactions.Transactional
import org.springframework.dao.DataIntegrityViolationException

import java.time.format.DateTimeFormatter
import grails.web.api.ServletAttributes
import javassist.NotFoundException
import java.time.LocalDate

@Transactional
class ReajusteSalarioService implements ServletAttributes {

    Map save() {
        Map retorno = [:]

        Funcionario funcionario = Funcionario.get(request.JSON.idFuncionario)

        if (!funcionario) {
            throw new NotFoundException("Funcionário Não Encontrado!")
        }

        ReajusteSalario reajusteSalario = new ReajusteSalario()
        reajusteSalario.setDataReajuste(getLocalDateByParameter(request.JSON.dataReajuste))
        reajusteSalario.setValorSalario(request.JSON.valorSalario as BigDecimal)
        reajusteSalario.setFuncionario(funcionario)
        reajusteSalario.save(flush: true)

        retorno.success = true
        retorno.registro = getShowRecord(reajusteSalario)

        return retorno
    }

    protected LocalDate getLocalDateByParameter(String parameter) {
        return LocalDate.parse(parameter, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }

    protected Map getShowRecord(ReajusteSalario reajusteSalario) {
        return [
                id          : reajusteSalario.id,
                dataReajuste: reajusteSalario.dataReajuste.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                valorSalario: reajusteSalario.valorSalario,
                funcionario : [
                        id: reajusteSalario.funcionario.id,
                        nome: reajusteSalario.funcionario.nome
                ]
        ]
    }

    Map list() {
        Map retorno = [:]

        List<ReajusteSalario> reajusteSalarioList = ReajusteSalario.findAll()

        retorno.total = reajusteSalarioList.size()
        retorno.registros = []

        reajusteSalarioList.each {
            retorno.registros << getShowRecord(it)
        }

        return retorno
    }

    Map update() {
        Map retorno = [:]

        Long id = params.long("id")

        Funcionario funcionario = Funcionario.get(request.JSON.idFuncionario)

        if (!funcionario) {
            throw new NotFoundException("Funcioanrio nao encontrado para ${request.JSON.idFuncionario}")
        }

        ReajusteSalario record = ReajusteSalario.findById(id)
        record.setFuncionario(Funcionario.get(request.JSON.idFuncionario))
        record.setDataReajuste(getLocalDateByParameter(request.JSON.dataReajuste))
        record.setValorSalario(request.JSON.valorSalario as BigDecimal)
        record.save(flush: true)

        retorno.success = true
        retorno.registro = getShowRecord(record)

        return retorno
    }

    Map delete(Long id) {
        Map retorno = [success: true]

        ReajusteSalario reajustesalario = ReajusteSalario.get(id)
        reajustesalario.delete(flush: true)

        return retorno
    }

    Map get(Long id) {
        Map retorno = [success: true]

        ReajusteSalario reajustesalario = ReajusteSalario.get(id)

        retorno.registro = getShowRecord(reajustesalario)

        return retorno
    }

}