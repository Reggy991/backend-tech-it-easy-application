package com.example.techiteasy.service;

import com.example.techiteasy.dto.TelevisionDto;
import com.example.techiteasy.dto.TelevisionInputDto;
import com.example.techiteasy.exception.RecordNotFoundException;
import com.example.techiteasy.model.RemoteController;
import com.example.techiteasy.model.Television;
import com.example.techiteasy.model.Wallbracket;
import com.example.techiteasy.repository.RemoteControllerRepository;
import com.example.techiteasy.repository.TelevisionRepository;
import com.example.techiteasy.repository.WallbracketRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Hier wordt alle code geschreven die echt specifiek voor jouw business belangrijk is. Bijvoorbeeld hoe je een factuur wil maken etc.

@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;
    private final WallbracketRepository wallbracketRepository;

    // Al een constructor geschreven dus @Autowired is niet meer nodig.
    public TelevisionService(TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository, WallbracketRepository wallbracketRepository) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
        this.wallbracketRepository = wallbracketRepository;
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

    public TelevisionDto getTelevisionById(Long id) {
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

    public TelevisionDto assignRemoteControllerToTelevision(Long id, Long remote_id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(remote_id);
        if (optionalTelevision.isEmpty() || optionalRemoteController.isEmpty()) {
            throw new RecordNotFoundException("This television and/or remote controller with id " + id + " " + remote_id + " does not exist.");
        }
        Television television1 = optionalTelevision.get();
        RemoteController remoteController1 = optionalRemoteController.get();

        television1.setRemoteController(remoteController1);
        Television returnTelevision = televisionRepository.save(television1);

        return transferTelevisionToDto(returnTelevision);
    }

    public String assignWallbracketToTelevision(Long id, Long wallbracket_id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        Optional<Wallbracket> optionalWallbracket = wallbracketRepository.findById(wallbracket_id);
        if (optionalTelevision.isEmpty() && optionalWallbracket.isEmpty()) {
            throw new RecordNotFoundException("This television and/or wallbracket with id " + id + " " + wallbracket_id + " does not exist.");
        }
        Television television1 = optionalTelevision.get();
        Wallbracket wallbracket1 = optionalWallbracket.get();

        List<Wallbracket> wallbrackets = television1.getWallbrackets();
        wallbrackets.add(wallbracket1);
        television1.setWallbrackets(wallbrackets);
        televisionRepository.save(television1);

        return "De koppeling is gelukt!";
    }

    public void deleteTelevision(@PathVariable Long id) {
        televisionRepository.deleteById(id);
    }


    public TelevisionDto transferTelevisionToDto (Television television) {

        TelevisionDto televisionDto = new TelevisionDto();

        televisionDto.setName(television.getName());
        televisionDto.setBrand(television.getBrand());
        televisionDto.setType(television.getType());
        televisionDto.setPrice(television.getPrice());
        televisionDto.setScreenSize(television.getScreenSize());
        televisionDto.setRemoteController(television.getRemoteController());
        televisionDto.setCiModule(television.getCiModule());

        return televisionDto;

    }

    public Television transferDtoToTelevision (TelevisionInputDto televisionInputDto){
        Television television = new Television();
        television.setName(televisionInputDto.getName());
        television.setBrand(televisionInputDto.getBrand());
        television.setType(televisionInputDto.getType());
        television.setPrice(televisionInputDto.getPrice());
        television.setScreenSize(televisionInputDto.getScreenSize());
        television.setRemoteController(televisionInputDto.getRemoteController());
        television.setCiModule(televisionInputDto.getCiModule());

        return television;
    }

}
