package br.com.treinaweb.cleancodesolid.services;

import java.util.ArrayList;
import java.util.List;

import br.com.treinaweb.cleancodesolid.mappers.RequestMapper;
import br.com.treinaweb.cleancodesolid.mappers.ResponseMapper;
import br.com.treinaweb.cleancodesolid.validators.impl.LivroValidator;
import org.springframework.stereotype.Service;

import br.com.treinaweb.cleancodesolid.dtos.inputs.LivroRequest;
import br.com.treinaweb.cleancodesolid.dtos.outputs.LivroResponse;
import br.com.treinaweb.cleancodesolid.models.Livro;
import br.com.treinaweb.cleancodesolid.repositories.LivroRepository;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    private final ResponseMapper<LivroResponse, Livro> responseMapper;

    private final RequestMapper<LivroRequest, Livro> requestMapper;

    private final LivroValidator livroValidator;

    public LivroService(LivroRepository livroRepository,
                        ResponseMapper<LivroResponse, Livro> responseMapper,
                        RequestMapper<LivroRequest, Livro> requestMapper,
                        LivroValidator livroValidator) {
        this.livroRepository = livroRepository;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
        this.livroValidator = livroValidator;
    }

    public LivroResponse cadastrar(LivroRequest livroRequest) {
        // Converte um LivroResponse em um Livro
        Livro livro = requestMapper.toModel(livroRequest);

        // Aplica regras de validação no Livro
        livroValidator.validar(livro);

        // Salva livro no banco de dados
        livroRepository.save(livro);

        // Converte um Livro em um LivroResponse e Retorna o LivroResponse
        return responseMapper.toResponse(livro);
    }

    public List<LivroResponse> listar() {
        List<Livro> livros = livroRepository.findAll();

        // Converte Livro em LivroResponse e Retorna a lista de LivroResponse
        return livroListToLivroResponseList(livros);
    }

    private List<LivroResponse> livroListToLivroResponseList(List<Livro> livros) {
        List<LivroResponse> livroResponseList = new ArrayList<>();
        for (Livro livro : livros) livroResponseList.add(responseMapper.toResponse(livro));

        return livroResponseList;
    }

}
