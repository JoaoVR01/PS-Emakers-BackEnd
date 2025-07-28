package emakersProjetoBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emakersProjetoBackEnd.data.entity.Emprestimo;
import emakersProjetoBackEnd.data.entity.EmprestimoId;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, EmprestimoId> {

}
