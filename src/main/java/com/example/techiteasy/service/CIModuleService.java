package com.example.techiteasy.service;

import com.example.techiteasy.dto.CIModuleDto;
import com.example.techiteasy.dto.CIModuleInputDto;
import com.example.techiteasy.exception.RecordNotFoundException;
import com.example.techiteasy.model.CIModule;
import com.example.techiteasy.model.Television;
import com.example.techiteasy.repository.CIModuleRepository;
import com.example.techiteasy.repository.TelevisionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CIModuleService {
    private final CIModuleRepository ciModuleRepository;
    private final TelevisionRepository televisionRepository;

    public CIModuleService(CIModuleRepository ciModuleRepository, TelevisionRepository televisionRepository) {
        this.ciModuleRepository = ciModuleRepository;
        this.televisionRepository = televisionRepository;
    }

    public List<CIModuleDto> getAllCIModules() {
        List<CIModule> ciModules = ciModuleRepository.findAll();
        List<CIModuleDto> ciModuleDtos = new ArrayList<>();

        for (CIModule ciModule : ciModules) {
            CIModuleDto dto = transferCIModuleToDto(ciModule);
            ciModuleDtos.add(dto);
        }

        return ciModuleDtos;
    }

    public CIModuleDto getCIModuleById(Long id) {
        Optional<CIModule> optionalCIModule = ciModuleRepository.findById(id);
        if (optionalCIModule.isPresent()) {
            CIModule ciModule = optionalCIModule.get();
            return transferCIModuleToDto(ciModule);
        } else {
            throw new RecordNotFoundException("This CI module with id " + id + " does not exist.");
        }
    }

    public CIModuleDto addCIModule (CIModuleInputDto dto) {
        CIModule ciModule = transferDtoToCIModule(dto);
        ciModuleRepository.save(ciModule);

        return transferCIModuleToDto(ciModule);
    }

    public CIModuleDto updateCIModule(Long id, CIModuleInputDto updatedCIModule) {
        Optional<CIModule> optionalCIModule = ciModuleRepository.findById(id);
        if (optionalCIModule.isPresent()) {
            CIModule ciModule1 = optionalCIModule.get();

            ciModule1.setName(updatedCIModule.getName());
            ciModule1.setType(updatedCIModule.getType());
            ciModule1.setPrice(updatedCIModule.getPrice());
            CIModule returnCIModule = ciModuleRepository.save(ciModule1);

            return transferCIModuleToDto(returnCIModule);
        } else {
            throw new RecordNotFoundException("This CI module with id " + id + " does not exist.");
        }
    }

    public String assignTelevisionToCIModule(Long id, Long television_id) {
        Optional<CIModule> optionalCIModule = ciModuleRepository.findById(id);
        Optional<Television> optionalTelevision = televisionRepository.findById(television_id);
        if(optionalCIModule.isEmpty() || optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("This CI module and/or television with id " + id + " " + television_id + " does not exist.");
        }
        CIModule ciModule1 = optionalCIModule.get();
        Television television1 = optionalTelevision.get();

        ciModule1.setTelevision(television1);
        CIModule returnCIModule = ciModuleRepository.save(ciModule1);

        return returnCIModule.getName() + " is gekoppeld!";
    }

    public void deleteCIModule(@PathVariable Long id) {
        ciModuleRepository.deleteById(id);
    }


    public CIModuleDto transferCIModuleToDto (CIModule ciModule) {

        CIModuleDto ciModuleDto = new CIModuleDto();

        ciModuleDto.setName(ciModule.getName());
        ciModuleDto.setType(ciModule.getType());
        ciModuleDto.setPrice(ciModule.getPrice());
        ciModuleDto.setTelevision(ciModule.getTelevision());

        return ciModuleDto;
    }

    public CIModule transferDtoToCIModule (CIModuleInputDto ciModuleInputDto){
        CIModule ciModule = new CIModule();

        ciModule.setName(ciModuleInputDto.getName());
        ciModule.setType(ciModuleInputDto.getType());
        ciModule.setPrice(ciModuleInputDto.getPrice());
        ciModule.setTelevision(ciModuleInputDto.getTelevision());

        return ciModule;
    }
}
