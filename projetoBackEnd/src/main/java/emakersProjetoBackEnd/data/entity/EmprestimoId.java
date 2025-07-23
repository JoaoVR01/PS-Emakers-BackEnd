package emakersProjetoBackEnd.data.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/*
 * Essa classe representa a chave primária composta da entidade Emprestimo.
 * Precisa ser:
 * - Serializable (requisito do JPA)
 * - Com métodos equals() e hashCode() (feitos automaticamente com @EqualsAndHashCode)
 */

 @Getter
 @Setter

public class EmprestimoId implements Serializable {//O JPA exige que toda chave composta implemente Serializable
    
    private Long pessoa;
    private Long livro;

    public EmprestimoId(){}
}
