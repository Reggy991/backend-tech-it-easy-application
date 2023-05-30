package com.example.techiteasy.service;

import com.example.techiteasy.dto.TelevisionDto;
import com.example.techiteasy.dto.TelevisionInputDto;
import com.example.techiteasy.exception.RecordNotFoundException;
import com.example.techiteasy.exception.ToManyCharException;
import com.example.techiteasy.model.Television;
import com.example.techiteasy.repository.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            TelevisionDto dto = transferTelevisionToDto(television);
            televisionDtos.add(dto);
        }

        return televisionDtos;
    }

    public TelevisionDto getTelevision(Long id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalTelevision.isPresent()) {
            Television television = optionalTelevision.get();
            return transferTelevisionToDto(television);
        } else {
            throw new RecordNotFoundException("This television with id " + id + " does not exist.");
        }
    }

    public TelevisionDto addTelevision (TelevisionInputDto dto) {
        Television television = transferDtoToTelevision(dto);
        televisionRepository.save(television);

        return transferTelevisionToDto(television);
    }

    public TelevisionDto updateTelevision(Long id, TelevisionInputDto updatedTelevision) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalTelevision.isPresent()) {
            Television television1 = optionalTelevision.get();

            television1.setBrand(updatedTelevision.getBrand());
            television1.setName(updatedTelevision.getName());
            television1.setScreenSize(updatedTelevision.getScreenSize());
            television1.setType(updatedTelevision.getType());
            television1.setPrice(updatedTelevision.getPrice());
            Television returnTelevision = televisionRepository.save(television1);

            return transferTelevisionToDto(returnTelevision);
        } else {
            throw new RecordNotFoundException("This television with id " + id + " does not exist.");
        }
    }

    public void deleteTelevision(@RequestBody Long id) {
        televisionRepository.deleteById(id);
    }


    public TelevisionDto transferTelevisionToDto (Television television) {

        TelevisionDto televisionDto = new TelevisionDto();

        televisionDto.setName(television.getName());
        televisionDto.setBrand(television.getBrand());
        televisionDto.setType(television.getType());
        televisionDto.setPrice(television.getPrice());
        televisionDto.setScreenSize(television.getScreenSize());

        return televisionDto;

    }

    public Television transferDtoToTelevision (TelevisionInputDto televisionInputDto){
        Television television = new Television();
        television.setName(televisionInputDto.getName());
        television.setBrand(televisionInputDto.getBrand());
        television.setType(televisionInputDto.getType());
        television.setPrice(televisionInputDto.getPrice());
        television.setScreenSize(television.getScreenSize());

        return television;
    }

}
