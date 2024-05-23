package backend.developer.librarymanagementsystem.service;

import backend.developer.librarymanagementsystem.mapper.PatronMapper;
import backend.developer.librarymanagementsystem.model.dto.request.PatronRequestDTO;
import backend.developer.librarymanagementsystem.model.dto.response.PatronResponseDTO;
import backend.developer.librarymanagementsystem.model.entity.ContactInformation;
import backend.developer.librarymanagementsystem.model.entity.Patron;
import backend.developer.librarymanagementsystem.repository.PatronRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PatronServiceTest {
    @InjectMocks
    private PatronService service;

    @Mock
    private PatronRepository repository;

    @Mock
    private PatronMapper mapper;

    @Test
    public void testCreatePatron() {
        // Prepare test data
        PatronRequestDTO request = new PatronRequestDTO(); // there is a reason why we call the non-arg constructor,,because if we called the arg before non-arg you wont be able to set any updates in the non-arg attributes
        request.setName("Name");
        request.setContactInformation(new ContactInformation("email@yahoo.com", "123 Main St, Apt 4B, Springfield, IL 62704", "+20 (100) 333-7374"));


        // Mock the behavior of the mapper mapToEntity() method
        Patron patron = new Patron();
        Patron.builder()
                .name(request.getName())
                .contactInformation(request.getContactInformation())
                .build();
        when(mapper.mapToEntity(any(PatronRequestDTO.class))).thenReturn(patron);

        // Mock the behavior of the repository save() method
        patron.setPatronId(1L);
        patron.setName(patron.getName());
        patron.setContactInformation(patron.getContactInformation());
        when(repository.save(any(Patron.class))).thenReturn(patron);

        // Mock the behavior of the mapper mapToDTO method
        PatronResponseDTO saved = new PatronResponseDTO();
        saved.setPatronId(patron.getPatronId());
        saved.setName(patron.getName());
        saved.setContactInformation(patron.getContactInformation());
        when(mapper.mapToDTO(any(Patron.class))).thenReturn(saved);

        // Call the service method
        PatronResponseDTO response = service.createPatron(request);

        // Verify the results
        assertNotNull(response);
        assertEquals(saved.getPatronId(), response.getPatronId());
        assertEquals(saved.getName(), response.getName());
        assertEquals(saved.getContactInformation(), response.getContactInformation());
    }

    @Test
    public void testFindPatronById() {
        // Prepare test data
        Patron patron = new Patron();
        patron.setPatronId(1L);
        patron.setName("Patron name");
        patron.setContactInformation(new ContactInformation("email@yahoo.com", "123 Main St, Apt 4B, Springfield, IL 62704", "+20 (100) 333-7374"));

        // Mock the behavior of the findById() method
        when(repository.findById(patron.getPatronId())).thenReturn(Optional.of(patron));

        // Mock the behavior of the mapper mapToDTO() method
        PatronResponseDTO saved = new PatronResponseDTO();
        saved.setPatronId(patron.getPatronId());
        saved.setName(patron.getName());
        saved.setContactInformation(patron.getContactInformation());
        when(mapper.mapToDTO(any(Patron.class))).thenReturn(saved);

        // Call the service method
        PatronResponseDTO response = service.findPatronById(patron.getPatronId());

        // Verify the results
        assertNotNull(response);
        assertEquals(saved.getPatronId(), response.getPatronId());
        assertEquals(saved.getName(), response.getName());
        assertEquals(saved.getContactInformation(), response.getContactInformation());
    }

    @Test
    public void testFindAllPatrons() {
        // Prepare test data
        Patron patron1 = new Patron();
        patron1.setPatronId(1L);
        patron1.setName("Patron name");
        patron1.setContactInformation(new ContactInformation("email@yahoo.com", "123 Main St, Apt 4B, Springfield, IL 62704", "+20 (100) 333-7374"));

        Patron patron2 = new Patron();
        patron1.setPatronId(1L);
        patron1.setName("Patron name");
        patron1.setContactInformation(new ContactInformation("email2@yahoo.com", "123 Main St, Apt 4B, Springfield2, IL 62704", "+20 (100) 333-7372"));

        List<Patron> patrons = Arrays.asList(patron1, patron2);
        Page<Patron> patronPage = new PageImpl<>(patrons);

        // Mock the behavior of the repository.findAll() method
        Pageable pageable = Pageable.unpaged();
        when(repository.findAll(pageable)).thenReturn(patronPage);

        // Call the service method
        Page<Patron> responsePage = service.findAllPatrons(pageable);

        // Verify the result
        assertNotNull(responsePage);
        assertEquals(2, responsePage.getTotalElements());

        // Additional checks on the first patron
        Patron firstPatron = responsePage.getContent().get(0);
        assertEquals(patron1.getPatronId(), firstPatron.getPatronId());
        assertEquals(patron1.getName(), firstPatron.getName());
        assertEquals(patron1.getContactInformation(), firstPatron.getContactInformation());

        // Additional checks on the second patron
        Patron secondPatron = responsePage.getContent().get(1);
        assertEquals(patron2.getPatronId(), secondPatron.getPatronId());
        assertEquals(patron2.getName(), secondPatron.getName());
        assertEquals(patron1.getContactInformation(), firstPatron.getContactInformation());

    }

    @Test
    public void testUpdatePatronById() {
        // Prepare test data
        PatronRequestDTO request = new PatronRequestDTO();
        request.setName("Name");
        request.setContactInformation(new ContactInformation("email@yahoo.com", "123 Main St, Apt 4B, Springfield, IL 62704", "+20 (100) 333-7374"));

        Patron patron = new Patron();
        patron.setName("Name Updated");
        request.setContactInformation(new ContactInformation("updatedemail@yahoo.com", "123 updatedMain St, Apt 4B, Springfield, IL 62704", "+20 (100) 333-7372"));

        // Mock the behavior of the repository.findById() and repository.save() methods
        when(repository.findById(patron.getPatronId())).thenReturn(Optional.of(patron));
        when(repository.save(any(Patron.class))).thenReturn(patron);

        // Call the service method
        service.updatePatronById(patron.getPatronId(), request);

        // Verify the result
        assertEquals(request.getName(), patron.getName());
        assertEquals(request.getContactInformation(), patron.getContactInformation());
    }

    @Test
    public void testDeletePatronById() {
        // Prepare test data
        Patron patron = new Patron();
        patron.setPatronId(1L);
        patron.setName("Deleted Name");
        patron.setContactInformation(new ContactInformation("email@yahoo.com", "123 Main St, Apt 4B, Springfield, IL 62704", "+20 (100) 333-7374"));

        // Mock the behavior of the findById() method
        when(repository.findById(patron.getPatronId())).thenReturn(Optional.of(patron));

        // Call the service method
        service.deletePatronById(patron.getPatronId());

        // Verify the result is deleted
        verify(repository).delete(patron);
    }
}
