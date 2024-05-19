package backend.developer.librarymanagementsystem.service;

import backend.developer.librarymanagementsystem.exception.AlreadyExistsException;
import backend.developer.librarymanagementsystem.exception.NotFoundException;
import backend.developer.librarymanagementsystem.mapper.PatronMapper;
import backend.developer.librarymanagementsystem.model.dto.request.PatronRequestDTO;
import backend.developer.librarymanagementsystem.model.dto.response.PatronResponseDTO;
import backend.developer.librarymanagementsystem.model.entity.Patron;
import backend.developer.librarymanagementsystem.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PatronService {
    private final PatronRepository repository;
    private final PatronMapper mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public PatronResponseDTO createPatron(PatronRequestDTO request) {
        validatePatronContactInformationNonExistence(request);

        Patron patron = mapper.mapToEntity(request);
        Patron saved = repository.save(patron);
        return mapper.mapToDTO(saved);
    }
    @Cacheable("patron")
    @Transactional(readOnly = true)
    public PatronResponseDTO findPatronById(long id) {
        Patron patron = validatePatronExistence(id);
        return mapper.mapToDTO(patron);
    }

    @Cacheable("patrons")
    @Transactional(readOnly = true)
    public Page<Patron> findAllPatrons(@PageableDefault(size = 10) Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePatronById(long id, PatronRequestDTO request) {
        Patron patron = validatePatronExistence(id);

        validatePatronNameNonExistence(request);
        validatePatronContactInformationNonExistence(request);

        patron.setName(request.getName());
        patron.setContactInformation(request.getContactInformation());

        repository.save(patron);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletePatronById(long id) {
        Patron patron = validatePatronExistence(id);
        repository.delete(patron);
    }

    private Patron validatePatronExistence(long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The patron with Id:[%s] does not exist.",id)));
    }

    private void validatePatronNameNonExistence(PatronRequestDTO request) {
        if (repository.existsByName(request.getName())) {
            throw new NotFoundException(String.format("The patron with name: [%s] already exists.", request.getName()));
        }
    }

    private void validatePatronContactInformationNonExistence(PatronRequestDTO request) {
        if (repository.existsByContactInformation(request.getContactInformation().getEmail())) {
            throw new AlreadyExistsException(String.format("The patron with email: [%s] already exists.", request.getContactInformation().getEmail()));
        }
        if (repository.existsByContactInformation(request.getContactInformation().getPhoneNumber())) {
            throw new AlreadyExistsException(String.format("The patron with phone number: [%s] already exists.", request.getContactInformation().getPhoneNumber()));
        }
    }
}
