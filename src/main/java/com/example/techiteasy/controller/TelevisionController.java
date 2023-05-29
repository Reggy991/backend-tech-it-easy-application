package com.example.techiteasy.controller;

import com.example.techiteasy.dto.TelevisionDto;
import com.example.techiteasy.exception.RecordNotFoundException;
import com.example.techiteasy.exception.ToManyCharException;
import com.example.techiteasy.model.Television;
import com.example.techiteasy.service.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping ("/all")
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions() {

        // Ok code 200
        // Functie getAllTelevisions van Service laag returnen.
        return ResponseEntity.ok().body(televisionService.getAllTelevisions());
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable Long id) throws RecordNotFoundException {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("This television with id " + id + " does not exist.");
        }
        Television television = optionalTelevision.get(); // Hier haal je de tv uit de Optional op.
        return ResponseEntity.ok().body(television);
    }

    @PostMapping("/add")
    public ResponseEntity<Television> addTelevision(@RequestBody Television television) throws ToManyCharException {
        if (television.getName().length() > 20) {
            throw new ToManyCharException("Mag niet langer dan 20 karakters zijn.");
        }
        televisionRepository.save(television);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(television.getId()).toUri();
        return ResponseEntity.created(location).body(television);
    }

    @PutMapping("/update/{id}")
    // Normaal gesproken bij @RequestBody voer je hier geen String in maar een datatype.naam (bijv. Television.television).
    public ResponseEntity<Television> updateTelevision(@PathVariable Long id, @RequestBody Television updatedTelevision) throws RecordNotFoundException {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("This television with id " + id + " does not exist.");
        }
        Television television = optionalTelevision.get();

        television.setName(updatedTelevision.getName());
        television.setBrand(updatedTelevision.getBrand());
        television.setType(updatedTelevision.getType());
        television.setPrice(updatedTelevision.getPrice());
        television.setScreenSize(updatedTelevision.getScreenSize());

        Television savedTelevision = televisionRepository.save(television);
        return ResponseEntity.ok().body(savedTelevision);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable Long id) throws RecordNotFoundException {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("This television with id " + id + " does not exist.");
        }
        televisionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }*/
}


