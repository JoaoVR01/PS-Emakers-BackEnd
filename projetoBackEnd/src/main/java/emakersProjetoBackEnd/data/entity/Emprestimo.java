package emakersProjetoBackEnd.data.entity;

import java.time.LocalDate;

import emakersProjetoBackEnd.data.dto.request.EmprestimoRequestDTO;
import jakarta.persistence.Column;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmprestimo;

    /*Relacionamento com Pessoa. */
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    /*Relacionamento com Livro.*/
    @ManyToOne
    @JoinColumn(name = "id_livro")
    private Livro livro;

    @Column(name = "DataEmprestimo")
    private LocalDate dataEmprestimo;

    @Column(name = "DataDevolucao")
    private LocalDate dataDevolucao;

    @Column(name = "Status")
    private boolean status;

    @Builder
    public Emprestimo(EmprestimoRequestDTO emprestimoRequestDTO){
        this.livro = emprestimoRequestDTO.livro();
    }

}
