package vhslab.solution.API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vhslab.solution.entities.dto.VhsEntityDto;
import vhslab.solution.service.VHSEntityService;

@RestController
@RequestMapping("/api/vhs")
public class VHSController {

    private final VHSEntityService vhsService;

    @Autowired
    public VHSController(VHSEntityService vhsService) {
        this.vhsService = vhsService;
    }

    @PostMapping
    public ResponseEntity<VhsEntityDto> createVHS(@RequestBody VhsEntityDto vhsDto) {
        VhsEntityDto createdVHS = vhsService.createVHS(vhsDto);
        return new ResponseEntity<>(createdVHS, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VhsEntityDto> getVHSById(@PathVariable Long id) {
        VhsEntityDto vhsDto = vhsService.getVHSById(id);
        return vhsDto != null ? ResponseEntity.ok(vhsDto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VhsEntityDto> updateVHS(@PathVariable Long id, @RequestBody VhsEntityDto updatedVhsDto) {
        VhsEntityDto updatedVHS = vhsService.updateVHS(id, updatedVhsDto);
        return updatedVHS != null ? ResponseEntity.ok(updatedVHS) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVHS(@PathVariable Long id) {
        vhsService.deleteVHSById(id);
        return ResponseEntity.noContent().build();
    }
}
