package emakersProjetoBackEnd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emakersProjetoBackEnd.data.dto.request.LivroRequestDTO;
import emakersProjetoBackEnd.data.dto.response.LivroResponseDTO;
import emakersProjetoBackEnd.data.entity.Livro;
import emakersProjetoBackEnd.repository.LivroRepository;

@Service
//O service encapsula a lógica de negócios da aplicação. Muitas vezes atua como intermediario dos controllers
//Criação dos métodos que serão chamados pelo controller
public class LivroService {

    @Autowired
    private LivroRepository livroRepository; //serve para fazer consultas e alterações no banco de dados de maneira mais simples 

    //método que retorna todas as categorias
    public List<LivroResponseDTO> getallLivros(){
        List<Livro> livros = livroRepository.findAll();
        return livros.stream().map(LivroResponseDTO::new).collect(Collectors.toList());
         //retorna uma lista de categorias. Passa por todas as categorias, a cada passagem, cria uma categoria responseDTO com base na categoria
    }

    //método que retorna uma categoria por id
    public LivroResponseDTO getLivroById(Long idLivro){
        Livro livro = findLivro(idLivro);
        return new LivroResponseDTO(livro);
    }

    //método que adiciona um novo livro
    public LivroResponseDTO createLivro(LivroRequestDTO livroRequestDTO){
        Livro livro = new Livro(livroRequestDTO);
        livroRepository.save(livro);

        return new LivroResponseDTO(livro);
    }

    //método que atualiza um livro
    public LivroResponseDTO updateLivro(LivroRequestDTO livroRequestDTO, Long idLivro){
        Livro livro = findLivro(idLivro);
        livro.setName(livroRequestDTO.name());
        livro.setAutor(livroRequestDTO.autor());
        livro.setData_lancamento(livroRequestDTO.data_lancamento());
        livroRepository.save(livro);

        return new LivroResponseDTO(livro);
    }

    //método que deleta um livro
    public String deleteLivro(Long idLivro){
        Livro livro = findLivro(idLivro);
        livroRepository.delete(livro);

        return "Livro " + idLivro + " has been deleted!";
    }

    private Livro findLivro(Long idLivro){
        return livroRepository.findById(idLivro).orElseThrow(() -> new RuntimeException("Book not found"));
    }

}
