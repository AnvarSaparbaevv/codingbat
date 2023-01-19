package uz.data.codingbat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.data.codingbat.entities.Part;
import uz.data.codingbat.payloads.PartDTO;
import uz.data.codingbat.services.PartService;
import uz.data.codingbat.templates.Result;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/part")
public class PartController {

    @Autowired
    private PartService service;

    @GetMapping
    private ResponseEntity<List<Part>> getPartList() {
        return ResponseEntity.ok(service.getPartList());
    }

    @GetMapping("/{partId}")
    private ResponseEntity<Part> getPart(@PathVariable Integer partId) {
        return ResponseEntity.ok(service.getPart(partId));
    }

    @PostMapping
    private ResponseEntity<Result> addPart(@Valid @RequestBody PartDTO partDTO) {
        Result result = service.addPart(partDTO);
        return ResponseEntity
                .status(result.getStatus())
                .body(result);
    }

    @PutMapping("/{partId}")
    private ResponseEntity<Result> updatePart(@Valid @RequestBody PartDTO partDTO,
                                              @PathVariable Integer partId) {
        Result result = service.updatePart(partDTO, partId);
        return ResponseEntity
                .status(result.getStatus())
                .body(result);
    }

    @DeleteMapping("/{partId}")
    private ResponseEntity<Result> deletePart(@PathVariable Integer partId) {
        Result result = service.deletePart(partId);
        return ResponseEntity
                .status(result.getStatus())
                .body(result);
    }
}