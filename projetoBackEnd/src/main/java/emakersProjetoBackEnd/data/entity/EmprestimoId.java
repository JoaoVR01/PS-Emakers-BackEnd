package emakersProjetoBackEnd.data.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/*
 * Essa classe representa a chave primária composta da entidade Emprestimo.
 * Precisa ser:
 * - Serializable (requisito do JPA)
 * 
 */

 @Getter
 @Setter

public class EmprestimoId implements Serializable {
//O JPA exige que classes que representam chave primária composta implementem essa interface.
    
    //campos que representam a chave primaria composta
    private Long pessoa;
    private Long livro;

    //construtor vazio
    public EmprestimoId(){}
    /*O JPA precisa disso pra conseguir criar uma instância dessa
    classe automaticamente durante as operações de persistência, busca, etc.*/
}
