package br.com.treinaweb.cleancodesolid.mappers.impl;

import br.com.treinaweb.cleancodesolid.dtos.inputs.LivroRequest;
import br.com.treinaweb.cleancodesolid.mappers.RequestMapper;
import br.com.treinaweb.cleancodesolid.models.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroRequestMapper implements RequestMapper<LivroRequest, Livro> {

    @Override
    public Livro toModel(LivroRequest request) {
        Livro model = new Livro();
        model.setTitulo(request.getTitulo());
        model.setAutor(request.getAutor());
        model.setPaginas(request.getPaginas());
        model.setIsbn(request.getIsbn());
        model.setDescricao(request.getDescricao());

        return model;
    }
}
