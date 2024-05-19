//package backend.developer.librarymanagementsystem.service;
//
//import backend.developer.librarymanagementsystem.model.dto.request.PatronRequestDTO;
//import backend.developer.librarymanagementsystem.model.entity.Patron;
//import backend.developer.librarymanagementsystem.repository.PatronRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//
//@RunWith(MockitoJUnitRunner.class)
//public class PatronServiceTest {
//    @InjectMocks
//    private PatronService service;
//
//    @Mock
//    private PatronRepository repository;
//
//    @Test
//    public void createPatron(){
//        //prepare data
//        PatronRequestDTO request = new PatronRequestDTO(); // there is a reason why we call the non-arg constructor,,because if we called the arg before non-arg you wont be able to set any updates in the non-arg attributes
//        request.setName("Name");
//        request.setContactInformation("+20(100) 333-7373");
//
//
//        Patron patron = new Patron();
//        patron.setId(1L);
//        patron.setName(patron.getName());
//        patron.setContactInformation(patron.getContactInformation());
//        patron.setBorrowedBooks(null);
//
//        //Mock the behavior repository save method
//
//        when(repository.save(any(Patron.class))).thenReturn(patron);
//
//        //inject
//
//        Patron response = service.createPatron(request);
//
//        assertNotNull(patron);
//        assertEquals(patron.getId(),response.getId());
//        assertEquals(patron.getName(),response.getName());
//        assertEquals(patron.getContactInformation(),response.getContactInformation());
//        assertEquals(patron.getBorrowedBooks(),response.getBorrowedBooks());
//    }
//    @Test
//    public void testFindPatronById()
//    {
//        Patron patron = new Patron();
//        patron.setId(1L);
//        patron.setName("Name");
//        patron.setContactInformation("+20(100) 333-7374");
//        patron.setBorrowedBooks(null);
//
//        when(repository.findById(patron.getId())).thenReturn(Optional.of(patron));
//
//        Patron response = service.findPatronById(patron.getId());
//
//        assertNotNull(response);
//        assertEquals(patron.getId(),response.getId());
//        assertEquals(patron.getName(),response.getName());
//        assertEquals(patron.getContactInformation(),response.getContactInformation());
//        assertEquals(patron.getBorrowedBooks(),response.getBorrowedBooks());
//    }
//    @Test
//    public void testFindAllPatrons()
//    {
//        Patron patron1 = new Patron();
//        patron1.setId(1L);
//        patron1.setName("Name");
//        patron1.setContactInformation("+20(100) 333-7374");
//        patron1.setBorrowedBooks(null);
//
//        Patron patron2 = new Patron();
//        patron2.setId(2L);
//        patron2.setName("Name2");
//        patron2.setContactInformation("+20(100) 333-7373");
//        patron2.setBorrowedBooks(null);
//
//        List<Patron> patrons = Arrays.asList(patron1,patron2);
//        when(repository.findAll()).thenReturn(patrons);
//
//        List<Patron> response = service.findAllPatrons();
//
//        assertNotNull(response);
//        assertEquals(patron1.getId(),response.get(0).getId());
//        assertEquals(patron1.getName(),response.get(0).getName());
//        assertEquals(patron1.getContactInformation(),response.get(0).getContactInformation());
//        assertEquals(patron1.getBorrowedBooks(),response.get(0).getBorrowedBooks());
//    }
//    @Test
//    public void testUpdatePatronById(){
//        //prepare the data
//        PatronRequestDTO request = new PatronRequestDTO();
//        request.setName("Name");
//        request.setContactInformation("+20(100) 333-7372");
//
//
//        //mock the behaviour of the save repository method
//
//        Patron patron = new Patron();
//        patron.setName("Name Updated");
//        patron.setContactInformation("+20(100) 333-7374");
////        patron.setBorrowedBooks(null);
//
//        when(repository.findById(patron.getId())).thenReturn(Optional.of(patron));
//        when(repository.save(any(Patron.class))).thenReturn(patron);
//
//        service.updatePatronById(patron.getId(),request);
////        assertNotNull(book);
//        assertEquals(request.getName(),patron.getName());
//        assertEquals(request.getContactInformation(),patron.getContactInformation());
//
//    }
//    @Test
//    public void testDeletePatronById(){
//        Patron patron = new Patron();
//        patron.setId(1L);
//        patron.setName("Deleted Name");
//        patron.setContactInformation("Deleted Contact");
////        patron.setBorrowedBooks();
//        when(repository.findById(patron.getId())).thenReturn(Optional.of(patron));
//        service.deletePatronById(patron.getId());
//        verify(repository).delete(patron);
//
//
//    }
//}
