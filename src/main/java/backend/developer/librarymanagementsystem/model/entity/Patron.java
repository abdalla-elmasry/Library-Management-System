package backend.developer.librarymanagementsystem.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "patrons")

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Patron {

    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long patronId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    @Embedded
    private ContactInformation contactInformation;

    @OneToMany(mappedBy = "patronId")
    private List<BorrowingRecord> borrowingPatrons;
}
