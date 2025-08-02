package emakersProjetoBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emakersProjetoBackEnd.data.entity.Emprestimo;
import emakersProjetoBackEnd.data.entity.Livro;

import java.util.List;


public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByStatusTrue();

    List<Emprestimo> findByStatusFalse();

    boolean existsByLivroAndStatusTrue(Livro livro);

    Emprestimo findByLivroAndStatusTrue(Livro livro);
}
