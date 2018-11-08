package com.madrefoca.crud2.controllers;

import com.madrefoca.crud2.exceptions.ResourceNotFoundException;
import com.madrefoca.crud2.model.Month;
import com.madrefoca.crud2.repositories.MonthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/months")
public class MonthsController {

    private final Logger log = LoggerFactory.getLogger(MonthsController.class);

    @Autowired
    MonthRepository monthRepository;

    @GetMapping("/all")
    public Collection<Month> getAllMonth() {
        return monthRepository.findAll();
    }

    @GetMapping("/month/{id}")
    ResponseEntity<?> getMonth(@PathVariable Long id) {
        Optional<Month> month = monthRepository.findById(id);
        return month.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/month")
    ResponseEntity<Month> createMonth(@Valid @RequestBody Month month) throws URISyntaxException {
        log.info("Request to create month: {}", month);
        Month result = monthRepository.save(month);
        return ResponseEntity.created(new URI("/api/group/" + result.getId()))
                .body(result);
    }

    @PutMapping("/month/{id}")
    ResponseEntity<Month> updateMonth(@PathVariable Long id, @Valid @RequestBody Month month) {
        month.setId(id);
        log.info("Request to update month: {}", month);
        Month result = monthRepository.save(month);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{monthId}")
    public ResponseEntity<?> deleteMonth(@PathVariable Long monthId) {
        return monthRepository.findById(monthId).map(month -> {
            monthRepository.delete(month);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + monthId + " not found"));
    }
}
