package backend.developer.librarymanagementsystem.repository;

import backend.developer.librarymanagementsystem.model.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {
    boolean existsByName(String name);
    boolean existsByContactInformation(String contactInformation);
}
