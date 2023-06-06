package com.example.techiteasy.controller;

import com.example.techiteasy.dto.TelevisionDto;
import com.example.techiteasy.dto.TelevisionInputDto;
import com.example.techiteasy.dto.WallbracketInputDto;
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
        return ResponseEntity.ok().body(televisionService.getAllTelevisions());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable Long id) {
        TelevisionDto television = televisionService.getTelevisionById(id);
        return ResponseEntity.ok().body(television);
    }

    @PostMapping("/add")
    public ResponseEntity<TelevisionDto> addTelevision(@RequestBody TelevisionInputDto televisionInputDto) {
        TelevisionDto dto = televisionService.addTelevision(televisionInputDto);
        return ResponseEntity.created(null).body(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TelevisionDto> updateTelevision(@PathVariable Long id, @RequestBody TelevisionInputDto updatedTelevision) {
        TelevisionDto dto = televisionService.updateTelevision(id, updatedTelevision);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/update/{id}/remote/{remote_id}")
    public ResponseEntity<TelevisionDto> assignRemoteControllerToTelevision(@PathVariable Long id, @PathVariable Long remote_id) {
        return ResponseEntity.ok(televisionService.assignRemoteControllerToTelevision(id, remote_id));
    }

    @PutMapping("/update/{id}/wallbracket/{wallbracket_id}")
    public ResponseEntity<String> assignWallbracketToTelevision(@PathVariable Long id, @PathVariable Long wallbracket_id) {
        return ResponseEntity.ok(televisionService.assignWallbracketToTelevision(id, wallbracket_id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {
        televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }
}


