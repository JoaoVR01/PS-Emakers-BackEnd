package emakersProjetoBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emakersProjetoBackEnd.data.entity.Emprestimo;
import emakersProjetoBackEnd.data.entity.Livro;
import emakersProjetoBackEnd.data.entity.Pessoa;

import java.util.List;


public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByStatusTrue();

    List<Emprestimo> findByStatusFalse();

    boolean existsByLivroAndStatusTrue(Livro livro);

    boolean existsByPessoaAndLivroAndStatusTrue(Pessoa pessoa, Livro livro);

    Emprestimo findByLivroAndPessoaAndStatusTrue(Livro livro, Pessoa pessoa);
}
