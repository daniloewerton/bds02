package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;
    @Autowired
    private CityRepository cityRepository;

    public EventDTO update(@PathVariable final Long id, @RequestBody final EventDTO dto) {
        try {
            Event event = repository.getOne(id);
            event.setName(dto.getName());
            event.setCity(cityRepository.findById(dto.getCityId()).get());
            event.setDate(dto.getDate());
            event.setUrl(dto.getUrl());
            return new EventDTO(event);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found.");
        }
    }
}