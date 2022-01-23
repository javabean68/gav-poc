package ch.egroup.interview.domain.anagraphics;

import ch.egroup.interview.domain.rules.Regel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kunde")

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Kunde {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "kunde", cascade = CascadeType.REFRESH, orphanRemoval = true)

    private Set<Regel> regels = new HashSet<>();

    public Set<Regel> getRegels() {
        return new HashSet<>(regels);
    }

    public void addRegel(Regel newRegel){
        regels.add(newRegel);
    }
}