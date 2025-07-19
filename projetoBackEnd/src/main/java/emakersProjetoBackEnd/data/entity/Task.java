package emakersProjetoBackEnd.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*
 * TABELA A SER CRIADA:
 * 
 * categoria:
 * idTask int
 * nome varchar(45)
 * idCategory int => chave que relaciona com a outra tabela
 * situation varcahr(20)
 */
@Getter
@Setter

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTask;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "situation", nullable = false, length = 20)
    private String situation;

    //criando o campo de relacionamento com a tabela categoria
    @ManyToOne()
    @JoinColumn(name = "idCategory")
    private Category category;

}
