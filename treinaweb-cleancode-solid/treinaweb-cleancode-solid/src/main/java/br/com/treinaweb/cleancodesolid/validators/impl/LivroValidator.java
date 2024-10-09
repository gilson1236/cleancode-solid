package br.com.treinaweb.cleancodesolid.validators.impl;

import br.com.treinaweb.cleancodesolid.exceptions.ValidacaoException;
import br.com.treinaweb.cleancodesolid.models.Livro;
import br.com.treinaweb.cleancodesolid.repositories.LivroRepository;
import br.com.treinaweb.cleancodesolid.validators.Validator;

public class LivroValidator implements Validator<Livro> {
    private final LivroRepository livroRepository;

    public LivroValidator(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    @Override
    public void validar(Livro model) {
        validarTituloNulo(model);
    }
    private void validarTituloNulo(Livro model) {
        if (model.getTitulo() == null) {
            throw new ValidacaoException("O título não pode ser nulo");
        }

        validarTituloVazio(model);
    }

    private void validarTituloVazio(Livro model) {
        if (model.getTitulo().isEmpty()) {
            throw new ValidacaoException("O título não pode estar em branco");
        }

        validarTituloMenorQue3Caracteres(model);
    }

    private void validarTituloMenorQue3Caracteres(Livro model){
        if(model.getTitulo().length() < 3){
            throw new ValidacaoException("O título não pode ter menos que 3 caracteres");
        }

        validarTituloMaiorQue255Caracteres(model);
    }

    private void validarTituloMaiorQue255Caracteres(Livro model){
        if (model.getTitulo().length() > 255) {
            throw new ValidacaoException("O título não pode ter mais que 255 caracteres");
        }

        validarTituloJaCadastrado(model);
    }

    private void validarTituloJaCadastrado(Livro model){
        if (livroRepository.isTituloExists(model.getTitulo())) {
            throw new ValidacaoException("Já existe um livro cadastrado com esse título");
        }

        validarIsbnCom10Caracteres(model);
    }

    private void validarIsbnCom10Caracteres(Livro model){
        if (model.getIsbn() != null && model.getIsbn().length() != 10) {
            throw new ValidacaoException("O ISBN deve ter 10 caracteres");
        }
    }
}
