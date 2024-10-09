package br.com.treinaweb.cleancodesolid.mappers.impl;

import br.com.treinaweb.cleancodesolid.dtos.outputs.LivroResponse;
import br.com.treinaweb.cleancodesolid.mappers.ResponseMapper;
import br.com.treinaweb.cleancodesolid.models.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroResponseMapper implements ResponseMapper<LivroResponse, Livro> {
    @Override
    public LivroResponse toResponse(Livro model) {
        LivroResponse response = new LivroResponse();
        response.setId(model.getId());
        response.setTitulo(model.getTitulo());
        response.setAutor(model.getAutor());

        return response;
    }
}
