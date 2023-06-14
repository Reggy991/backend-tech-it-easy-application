package com.example.techiteasy.controller;

import com.example.techiteasy.dto.CIModuleDto;
import com.example.techiteasy.dto.CIModuleInputDto;
import com.example.techiteasy.service.CIModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cimodules")
public class CIModuleController {

    private final CIModuleService ciModuleService;

    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CIModuleDto>> getAllCIModules() {
        return ResponseEntity.ok().body(ciModuleService.getAllCIModules());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CIModuleDto> getCIModule(@PathVariable Long id) {
        CIModuleDto ciModule = ciModuleService.getCIModuleById(id);
        return ResponseEntity.ok().body(ciModule);
    }

    @PostMapping("/add")
    public ResponseEntity<CIModuleDto> addCIMOdule(@RequestBody CIModuleInputDto ciModuleInputDto) {
        CIModuleDto dto = ciModuleService.addCIModule(ciModuleInputDto);
        return ResponseEntity.created(null).body(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CIModuleDto> updateCIModule(@PathVariable Long id, @RequestBody CIModuleInputDto updatedCIModule) {
        CIModuleDto dto = ciModuleService.updateCIModule(id, updatedCIModule);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/update/{id}/television/{television_id}")
    public ResponseEntity<String> assignTelevisionToCIModule(@PathVariable Long id, @PathVariable Long television_id) {
        return ResponseEntity.ok(ciModuleService.assignTelevisionToCIModule(id, television_id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCIModule(@PathVariable Long id) {
        ciModuleService.deleteCIModule(id);
        return ResponseEntity.noContent().build();
    }
}
