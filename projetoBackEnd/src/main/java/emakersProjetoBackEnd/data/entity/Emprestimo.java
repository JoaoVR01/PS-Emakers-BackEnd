package emakersProjetoBackEnd.data.entity;

import emakersProjetoBackEnd.data.dto.request.EmprestimoRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "emprestimo")

public class Emprestimo {

   //para criar uma tabela sem id, é necessário criar uma chave primaria composta, para isso é preciso criar uma classe auxiliar
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmprestimo;

    /*
     * Relacionamento com Pessoa.
     */
    @ManyToOne
    @JoinColumn(name = "Pessoa")
    private Pessoa pessoa;


    /*
     * Relacionamento com Livro.
     */
    @ManyToOne
    @JoinColumn(name = "Livro")
    private Livro livro;

    @Builder
    public Emprestimo(EmprestimoRequestDTO emprestimoRequestDTO){
        this.livro = emprestimoRequestDTO.livro();
        this.pessoa = emprestimoRequestDTO.pessoa();
    }

}
