package emakersProjetoBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emakersProjetoBackEnd.data.entity.Livro;


@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
    Livro findByName(String name);
}
