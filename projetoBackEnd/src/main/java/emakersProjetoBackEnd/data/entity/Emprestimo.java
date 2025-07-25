package emakersProjetoBackEnd.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(EmprestimoId.class)
@Table(name = "emprestimo")

public class Emprestimo {

   //para criar uma tabela sem id, é necessário criar uma chave primaria composta, para isso é preciso criar uma classe auxiliar
    

    /*
     * Relacionamento com Pessoa.
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;


    /*
     * Relacionamento com Livro.
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "idLivro")
    private Livro livro;

}
