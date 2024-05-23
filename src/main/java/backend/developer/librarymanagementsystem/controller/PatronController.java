package backend.developer.librarymanagementsystem.controller;

import backend.developer.librarymanagementsystem.model.dto.request.PatronRequestDTO;
import backend.developer.librarymanagementsystem.model.dto.response.PatronResponseDTO;
import backend.developer.librarymanagementsystem.model.entity.Patron;
import backend.developer.librarymanagementsystem.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class PatronController {
    private final PatronService service;

    public PatronController(PatronService service) {
        this.service = service;
    }


    @PostMapping(path = "/patrons")
    @ResponseStatus(HttpStatus.CREATED)
    public PatronResponseDTO createPatron(@Valid @RequestBody PatronRequestDTO request) {
        return service.createPatron(request);
    }

    @GetMapping(path = "/patrons/{id}")
    public PatronResponseDTO retrievePatronById(@PathVariable long id) {
        return service.findPatronById(id);
    }

    @GetMapping(path = "/patrons")
    public Page<Patron> findAllBooks(Pageable pageable) {
        return service.findAllPatrons(pageable);
    }

    @PutMapping(path = "/patrons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePatron(@PathVariable long id, @Valid @RequestBody PatronRequestDTO request) {
        service.updatePatronById(id, request);
    }

    @DeleteMapping(path = "/patrons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatron(@PathVariable long id) {
        service.deletePatronById(id);
    }
}
