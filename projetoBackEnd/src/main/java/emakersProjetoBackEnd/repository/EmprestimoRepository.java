package emakersProjetoBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import emakersProjetoBackEnd.data.entity.Emprestimo;
import emakersProjetoBackEnd.data.entity.Livro;
import emakersProjetoBackEnd.data.entity.Pessoa;

import java.util.List;


public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByStatusTrue();

    List<Emprestimo> findByStatusFalse();

    boolean existsByLivroAndStatusTrue(Livro livro);

    boolean existsByLivroAndStatusFalse(Livro livro);

    boolean existsByPessoaAndStatusTrue(Pessoa pessoa);

    boolean existsByPessoaAndLivroAndStatusTrue(Pessoa pessoa, Livro livro);

    Emprestimo findByLivroAndPessoaAndStatusTrue(Livro livro, Pessoa pessoa);

    @Modifying //informa que é uma query de delete ou update
    void deleteAllByLivroAndStatusFalse(Livro livro);

    @Modifying
    void deleteAllByPessoa(Pessoa pessoa);

}
