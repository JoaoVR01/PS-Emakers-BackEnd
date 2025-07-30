package emakersProjetoBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emakersProjetoBackEnd.data.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    
}
