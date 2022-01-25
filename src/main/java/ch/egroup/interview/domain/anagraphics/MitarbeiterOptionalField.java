package ch.egroup.interview.domain.anagraphics;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mitarbeiter_optional_field")

@ToString
public class MitarbeiterOptionalField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Getter
    @Setter
    private String name;

    @Enumerated
    @Column(name = "type", nullable = false)
    @Getter
    @Setter
    private FieldType type;

    @Column(name = "value", nullable = false)
    @Getter
    @Setter
    private String value;


    @ManyToOne
    @JoinColumn(name = "mitarbeiter_id")
    private Mitarbeiter mitarbeiter;

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }
}