package org.example.springdatam1.services.impl;

import org.example.springdatam1.entity.Genre;
import org.example.springdatam1.repository.GenreRepository;
import org.example.springdatam1.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {


    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre save(Genre entity) {
        return genreRepository.save(entity);
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre update(Long aLong, Genre entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {
        genreRepository.findById(aLong).orElseThrow(()->new EntityNotFoundException("Genre not found"));
        genreRepository.deleteById(aLong);
    }
}
