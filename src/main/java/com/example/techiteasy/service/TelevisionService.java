package com.example.techiteasy.service;

import com.example.techiteasy.dto.TelevisionDto;
import com.example.techiteasy.exception.RecordNotFoundException;
import com.example.techiteasy.model.Television;
import com.example.techiteasy.repository.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Hier wordt alle code geschreven die echt specifiek voor jouw business belangrijk is. Bijvoorbeeld hoe je een factuur wil maken etc.

@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;

    // Al een constructor geschreven dus @Autowired is niet meer nodig.
    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public List<TelevisionDto> getAllTelevisions() {
        List<Television> televisions = televisionRepository.findAll();
        List<TelevisionDto> televisionDtos = new ArrayList<>();

        for (Television television : televisions) {

            // Methode toepassen en toevoegen aan de lijst met televisionDtos.
            televisionDtos.add(transferTelevisionToDto(television));
        }

        return televisionDtos;

/*        if (televisions.isEmpty()) {
            throw new RecordNotFoundException("No televisions found.");
        }*/
    }

    public TelevisionDto transferTelevisionToDto(Television television) {
        // Voor elk item in de televisions lijst gaan we een televisionDto maken.
        TelevisionDto televisionDto = new TelevisionDto();
        // Waardes van de television over gaan zetten naar de televisionDto.
        // Als je Dto private is wordt het 'televisionDto.setName() = ...'.
        televisionDto.name = television.getName();
        televisionDto.brand = television.getBrand();

        return televisionDto;

    }

    public Television transferDtoToTelevision(TelevisionDto televisionDto) {
        Television television = new Television();
        television.setName(televisionDto.name);
        television.setBrand(televisionDto.brand);

        return television;
    }
}
