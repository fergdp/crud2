package com.madrefoca.crud2.controllers;

import com.madrefoca.crud2.exceptions.ResourceNotFoundException;
import com.madrefoca.crud2.model.Month;
import com.madrefoca.crud2.repositories.MonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/months")
public class MonthsController {

    @Autowired
    MonthRepository monthRepository;

    @GetMapping("/all")
    public Collection<Month> getAllMonth() {
        return monthRepository.findAll();
    }

    @PostMapping("/create")
    public Month createMonth(@Valid @RequestBody Month month) {
        return monthRepository.save(month);
    }

    @PutMapping("/update/{monthId}")
    public Month updateMonth(@PathVariable Long monthId, @Valid @RequestBody Month monthRequest) {
        return monthRepository.findById(monthId).map(month -> {
            month.setName(monthRequest.getName());
            return monthRepository.save(month);
        }).orElseThrow(() -> new ResourceNotFoundException("MonthId " + monthId + " not found"));
    }

    @DeleteMapping("/delete/{monthId}")
    public ResponseEntity<?> deleteMonth(@PathVariable Long monthId) {
        return monthRepository.findById(monthId).map(month -> {
            monthRepository.delete(month);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + monthId + " not found"));
    }
}
