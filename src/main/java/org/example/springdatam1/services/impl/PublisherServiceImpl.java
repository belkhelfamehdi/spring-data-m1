package org.example.springdatam1.services.impl;

import org.example.springdatam1.services.PublisherService;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import org.example.springdatam1.entity.Publisher;
import org.example.springdatam1.repository.PublisherRepository;

@Service
public class PublisherServiceImpl implements PublisherService {
    private PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Publisher save(Publisher entity) {
        return publisherRepository.save(entity);
    }

    @Override
    public Optional<Publisher> findById(Long id) {
        return publisherRepository.findById(id);
    }

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher update(Long id, Publisher newPublisherData) {
        Publisher existingPublisher = findById(id).orElseThrow(() -> new EntityNotFoundException("Publisher not found")); // Ensure it exists
        existingPublisher.setName(newPublisherData.getName()); // Update name
        return publisherRepository.save(existingPublisher); // Save changes
    }

    @Override
    public void delete(Long id) {
        publisherRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Publisher not found"));
        publisherRepository.deleteById(id);
    }

}
