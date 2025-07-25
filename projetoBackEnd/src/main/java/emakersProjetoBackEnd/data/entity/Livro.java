package emakersProjetoBackEnd.data.entity;

import java.util.Date;
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

}
