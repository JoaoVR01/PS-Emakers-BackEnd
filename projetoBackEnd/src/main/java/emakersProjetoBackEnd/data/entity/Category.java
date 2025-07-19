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
 * categoria:
 * idCategory int
 * nome varchar(45)
 * 
 */

//Comandos responsáveis por criar os campos e os métodos para acessar as instâncias da classe
@Getter
@Setter


@Entity
@Table(name = "category")
public class Category {
    //CRIAÇÃO DOS CAMPOS DA TABELA DO BANCO DE DADOS

    //criação do campo Id de forma que seja atualizado automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    //criação do campo nome
    @Column(name = "name",  nullable = false, length = 45) //definir que o campo name será uma coluna da tabela z
    private String name;


}
