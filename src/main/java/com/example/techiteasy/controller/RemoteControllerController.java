package com.example.techiteasy.controller;

import com.example.techiteasy.dto.RemoteControllerDto;
import com.example.techiteasy.dto.RemoteControllerInputDto;
import com.example.techiteasy.service.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remotecontrollers")
public class RemoteControllerController {
    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RemoteControllerDto>> getAllRemotes() {
        return ResponseEntity.ok().body(remoteControllerService.getAllRemotes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> getRemote(@PathVariable Long id) {
        RemoteControllerDto remote = remoteControllerService.getRemoteById(id);
        return ResponseEntity.ok().body(remote);
    }

    @PostMapping("/add")
    public ResponseEntity<RemoteControllerDto> addRemote(@RequestBody RemoteControllerInputDto remoteControllerInputDto) {
        RemoteControllerDto dto = remoteControllerService.addRemote(remoteControllerInputDto);
        return ResponseEntity.created(null).body(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RemoteControllerDto> updateRemote(@PathVariable Long id, @RequestBody RemoteControllerInputDto updatedRemote) {
        RemoteControllerDto dto = remoteControllerService.updateRemote(id, updatedRemote);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteRemote(@PathVariable Long id) {
        remoteControllerService.deleteRemote(id);
        return ResponseEntity.noContent().build();
    }
}
