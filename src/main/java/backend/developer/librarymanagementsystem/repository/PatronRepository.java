package backend.developer.librarymanagementsystem.repository;

import backend.developer.librarymanagementsystem.model.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {
    boolean existsByName(String name);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Patron p WHERE p.contactInformation.email = :email")
    boolean existsByContactInformationEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Patron p WHERE p.contactInformation.phoneNumber = :phoneNumber")
    boolean existsByContactInformationPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
