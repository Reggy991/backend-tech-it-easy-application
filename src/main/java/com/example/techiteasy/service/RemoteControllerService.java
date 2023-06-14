package com.example.techiteasy.service;

import com.example.techiteasy.dto.RemoteControllerDto;
import com.example.techiteasy.dto.RemoteControllerInputDto;
import com.example.techiteasy.exception.RecordNotFoundException;
import com.example.techiteasy.model.RemoteController;
import com.example.techiteasy.repository.RemoteControllerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {
    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public List<RemoteControllerDto> getAllRemotes() {
        List<RemoteController> remotes = remoteControllerRepository.findAll();
        List<RemoteControllerDto> remoteControllerDtos = new ArrayList<>();

        for (RemoteController remote : remotes) {
            RemoteControllerDto dto = transferRemoteToDto(remote);
            remoteControllerDtos.add(dto);
        }

        return remoteControllerDtos;
    }

    public RemoteControllerDto getRemoteById(Long id) {
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(id);
        if (optionalRemoteController.isPresent()) {
            RemoteController remoteController = optionalRemoteController.get();
            return transferRemoteToDto(remoteController);
        } else {
            throw new RecordNotFoundException("This remote controller with id " + id + " does not exist.");
        }
    }

    public RemoteControllerDto addRemote (RemoteControllerInputDto dto) {
        RemoteController remoteController = transferDtoToRemote(dto);
        remoteControllerRepository.save(remoteController);

        return transferRemoteToDto(remoteController);
    }

    public RemoteControllerDto updateRemote(Long id, RemoteControllerInputDto updatedRemote) {
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(id);
        if (optionalRemoteController.isPresent()) {
            RemoteController remoteController1 = optionalRemoteController.get();

            remoteController1.setCompatibleWith(updatedRemote.getCompatibleWith());
            remoteController1.setBatteryType(updatedRemote.getBatteryType());
            remoteController1.setName(updatedRemote.getName());
            remoteController1.setBrand(updatedRemote.getBrand());
            remoteController1.setPrice(updatedRemote.getPrice());
            remoteController1.setOriginalStock(updatedRemote.getOriginalStock());
            RemoteController returnRemoteController = remoteControllerRepository.save(remoteController1);

            return transferRemoteToDto(returnRemoteController);
        } else {
            throw new RecordNotFoundException("This remote controller with id " + id + " does not exist.");
        }
    }

    public void deleteRemote(@PathVariable Long id) {
        remoteControllerRepository.deleteById(id);
    }


    public RemoteControllerDto transferRemoteToDto (RemoteController remoteController) {

        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();

        remoteControllerDto.setCompatibleWith(remoteController.getCompatibleWith());
        remoteControllerDto.setBatteryType(remoteController.getBatteryType());
        remoteControllerDto.setName(remoteController.getName());
        remoteControllerDto.setBrand(remoteController.getBrand());
        remoteControllerDto.setPrice(remoteController.getPrice());
        remoteControllerDto.setOriginalStock(remoteController.getOriginalStock());
        remoteControllerDto.setTelevision(remoteController.getTelevision());

        return remoteControllerDto;

    }

    public RemoteController transferDtoToRemote (RemoteControllerInputDto remoteControllerInputDto){
        RemoteController remoteController = new RemoteController();

        remoteController.setCompatibleWith(remoteControllerInputDto.getCompatibleWith());
        remoteController.setBatteryType(remoteControllerInputDto.getBatteryType());
        remoteController.setName(remoteControllerInputDto.getName());
        remoteController.setBrand(remoteControllerInputDto.getBrand());
        remoteController.setPrice(remoteControllerInputDto.getPrice());
        remoteController.setOriginalStock(remoteControllerInputDto.getOriginalStock());
        remoteController.setTelevision(remoteControllerInputDto.getTelevision());

        return remoteController;
    }
}
