package ch.egroup.interview.domain.anagraphics;

import javax.persistence.*;

@Entity
@Table(name = "mandant")
public class Mandant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}