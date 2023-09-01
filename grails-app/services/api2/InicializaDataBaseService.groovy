package api2

import grails.gorm.transactions.Transactional
import java.time.LocalDate

@Transactional
class InicializaDataBaseService {

    def Inicializa() {
        Cidade cidade = new Cidade()
        cidade.setNome("Porto Alegre")
        cidade.save(flush: true) // O flush é uma maneira de engessar a inicialização ao banco de dados

        Cidade cidade2 = new Cidade()
        cidade2.setNome("Nova York")
        cidade2.save(flush: true)

        Cidade cidade3 = new Cidade()
        cidade3.setNome("Rio de Janeiro")
        cidade3.save(flush: true)

        Funcionario funcionario = new Funcionario()
        funcionario.setNome("Guilherme Tadler")
        funcionario.setCidade(cidade3)
        funcionario.save(flush: true)

        Funcionario funcionario2 = new Funcionario()
        funcionario2.setNome("Enner Violencia")
        funcionario2.setCidade(cidade)
        funcionario2.save(flush: true)

        ReajusteSalario reajusteSalario = new ReajusteSalario()
        reajusteSalario.setDataReajuste(LocalDate.now()) // Passa a data atual com se fosse um sysdate no banco de dados
        reajusteSalario.setValorSalario(6800 as BigDecimal)
        reajusteSalario.setFuncionario(funcionario)
        reajusteSalario.save(flush: true)
    }
}
