package ch.egroup.interview.domain.anagraphics;

import ch.egroup.interview.domain.rules.Regel;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "kunde")
public class Kunde {
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

    @OneToMany(mappedBy = "kunde", cascade = CascadeType.REFRESH, orphanRemoval = true)

    private Set<Regel> regels = new LinkedHashSet<>();

    public Set<Regel> getRegels() {
        return regels;
    }

    public void setRegels(Set<Regel> regels) {
        this.regels = regels;
    }
}