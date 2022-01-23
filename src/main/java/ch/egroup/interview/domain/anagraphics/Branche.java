package ch.egroup.interview.domain.anagraphics;

import ch.egroup.interview.domain.rules.Regel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "branche")
public class Branche {
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

    @OneToMany(mappedBy = "branche", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private Set<Regel> regels = new HashSet<>();

    public Set<Regel> getRegels() {
        return new HashSet<>(regels);
    }

    public void addRegel(Regel newRegel){
        regels.add(newRegel);
    }
}