package com.ut.sucursales.controller;

import com.ut.sucursales.model.dto.BranchDto;
import com.ut.sucursales.service.interfaces.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sucursales")
public class BranchController {

    private final BranchService branchService;

    @GetMapping
    public List<BranchDto> getBranches() {
        return branchService.getAll();
    }

    @GetMapping("/{id}")
    public BranchDto getBranch(@PathVariable UUID id) {
        return branchService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BranchDto createBranch(@RequestBody @Valid BranchDto branchDto) {
        return branchService.createBranch(branchDto);
    }

    @PutMapping("/{id}")
    public BranchDto updateBranch(@PathVariable UUID id, @RequestBody @Valid BranchDto branchDto) {
        return branchService.updateBranch(id, branchDto);
    }

    @PatchMapping("/{id}/{field}")
    public BranchDto updateFieldBranch(@PathVariable UUID id, @PathVariable String field, @RequestParam String value) throws BadRequestException {
        return branchService.updateFieldBranch(id, field, value);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBranch(@PathVariable UUID id) {
        branchService.deleteById(id);
    }


}
