package emakersProjetoBackEnd.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*
 * TABELA A SER CRIADA:
 * 
 * Pessoa
 * idPessoa int
 * nome varcher(100)
 * cpf char(11)
 * cep char(9)
 * email varchar(100)
 * senha varchar(100)
 * 
 */

//Comandos responsáveis por criar os campos e os métodos para acessar as instâncias da classe
@Getter
@Setter


@Entity
@Table(name = "category")
public class Pessoa {
    //CRIAÇÃO DOS CAMPOS DA TABELA DO BANCO DE DADOS

    //criação do campo Id de forma que seja atualizado automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;

    //criação do campo nome
    @Column(name = "name",  nullable = false, length = 100) //definir que o campo name será uma coluna da tabela z
    private String name;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;


}
