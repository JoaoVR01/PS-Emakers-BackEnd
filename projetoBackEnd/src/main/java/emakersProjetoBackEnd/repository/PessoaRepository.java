package emakersProjetoBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import emakersProjetoBackEnd.data.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    Pessoa findByEmail(String email);

    Pessoa findByCpf(String cpf);
}
