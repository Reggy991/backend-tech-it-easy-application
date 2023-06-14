package com.example.techiteasy.controller;

import com.example.techiteasy.dto.WallbracketDto;
import com.example.techiteasy.dto.WallbracketInputDto;
import com.example.techiteasy.service.WallbracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallbrackets")
public class WallbracketController {
    private final WallbracketService wallbracketService;

    public WallbracketController(WallbracketService wallbracketService) {
        this.wallbracketService = wallbracketService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<WallbracketDto>> getAllWallbrackets() {
        return ResponseEntity.ok().body(wallbracketService.getAllWallbrackets());
    }
    @GetMapping("/{id}")
    public ResponseEntity<WallbracketDto> getWallbracket(@PathVariable Long id) {
        WallbracketDto wallbracket = wallbracketService.getWallbracketById(id);
        return ResponseEntity.ok().body(wallbracket);
    }

    @PostMapping("/add")
    public ResponseEntity<WallbracketDto> addWallbracket(@RequestBody WallbracketInputDto wallbracketInputDto) {
        WallbracketDto dto = wallbracketService.addWallbracket(wallbracketInputDto);
        return ResponseEntity.created(null).body(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<WallbracketDto> updateWallbracket(@PathVariable Long id, @RequestBody WallbracketInputDto updatedWallbracket) {
        WallbracketDto dto = wallbracketService.updateWallbracket(id, updatedWallbracket);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteWallbracket(@PathVariable Long id) {
        wallbracketService.deleteWallbracket(id);
        return ResponseEntity.noContent().build();
    }
}
