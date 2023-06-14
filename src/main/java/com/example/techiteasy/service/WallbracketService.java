package com.example.techiteasy.service;

import com.example.techiteasy.dto.RemoteControllerDto;
import com.example.techiteasy.dto.RemoteControllerInputDto;
import com.example.techiteasy.dto.WallbracketDto;
import com.example.techiteasy.dto.WallbracketInputDto;
import com.example.techiteasy.exception.RecordNotFoundException;
import com.example.techiteasy.model.RemoteController;
import com.example.techiteasy.model.Wallbracket;
import com.example.techiteasy.repository.WallbracketRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WallbracketService {
    private final WallbracketRepository wallbracketRepository;

    public WallbracketService(WallbracketRepository wallbracketRepository) {
        this.wallbracketRepository = wallbracketRepository;
    }

    public List<WallbracketDto> getAllWallbrackets() {
        List<Wallbracket> wallbrackets = wallbracketRepository.findAll();
        List<WallbracketDto> wallbracketDtos = new ArrayList<>();

        for (Wallbracket wallbracket : wallbrackets) {
            WallbracketDto dto = transferWallbracketToDto(wallbracket);
            wallbracketDtos.add(dto);
        }

        return wallbracketDtos;
    }

    public WallbracketDto getWallbracketById(Long id) {
        Optional<Wallbracket> optionalWallbracket = wallbracketRepository.findById(id);
        if (optionalWallbracket.isPresent()) {
            Wallbracket wallbracket = optionalWallbracket.get();
            return transferWallbracketToDto(wallbracket);
        } else {
            throw new RecordNotFoundException("This wallbracket with id " + id + " does not exist.");
        }
    }

    public WallbracketDto addWallbracket (WallbracketInputDto dto) {
        Wallbracket wallbracket = transferDtoToWallbracket(dto);
        wallbracketRepository.save(wallbracket);

        return transferWallbracketToDto(wallbracket);
    }

    public WallbracketDto updateWallbracket(Long id, WallbracketInputDto updatedWallbracket) {
        Optional<Wallbracket> optionalWallbracket = wallbracketRepository.findById(id);
        if (optionalWallbracket.isPresent()) {
            Wallbracket wallbracket1 = optionalWallbracket.get();

            wallbracket1.setSize(updatedWallbracket.getSize());
            wallbracket1.setAdjustable(updatedWallbracket.getAdjustable());
            wallbracket1.setName(updatedWallbracket.getName());
            wallbracket1.setPrice(updatedWallbracket.getPrice());

            Wallbracket returnWallbracket = wallbracketRepository.save(wallbracket1);

            return transferWallbracketToDto(returnWallbracket);
        } else {
            throw new RecordNotFoundException("This wallbracket with id " + id + " does not exist.");
        }
    }

    public void deleteWallbracket(@PathVariable Long id) {
        wallbracketRepository.deleteById(id);
    }


    public WallbracketDto transferWallbracketToDto (Wallbracket wallbracket) {

        WallbracketDto wallbracketDto = new WallbracketDto();

        wallbracketDto.setSize(wallbracket.getSize());
        wallbracketDto.setAdjustable(wallbracket.getAdjustable());
        wallbracketDto.setName(wallbracket.getName());
        wallbracketDto.setPrice(wallbracket.getPrice());

        return wallbracketDto;

    }

    public Wallbracket transferDtoToWallbracket (WallbracketInputDto wallbracketInputDto){
        Wallbracket wallbracket = new Wallbracket();

        wallbracket.setSize(wallbracketInputDto.getSize());
        wallbracket.setAdjustable(wallbracketInputDto.getAdjustable());
        wallbracket.setName(wallbracketInputDto.getName());
        wallbracket.setPrice(wallbracketInputDto.getPrice());

        return wallbracket;
    }
}
