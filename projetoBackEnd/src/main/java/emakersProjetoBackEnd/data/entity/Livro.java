package emakersProjetoBackEnd.data.entity;

import java.util.Date;

import emakersProjetoBackEnd.data.dto.request.LivroRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * TABELA A SER CRIADA:
 * 
 * Livro
 * idLivro int
 * nome varchar(100)
 * autor varchar(100)
 * data_lancamento date
 * 
 */

@Getter
@Setter
@Entity
@NoArgsConstructor

@Table(name = "Livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "autor", nullable = false, length = 100)
    private String autor;

    @Column(name = "data lançamento", nullable = false)
    private Date data_lancamento;

    @Builder
    public Livro(LivroRequestDTO livroRequestDTO){
        this.name = livroRequestDTO.nome();
        this.autor = livroRequestDTO.autor();
        this.data_lancamento = livroRequestDTO.data_lancamento();
    }

}
