package ch.egroup.interview.domain.anagraphics;

import ch.egroup.interview.domain.rules.Regel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "gav")

@Getter
@Setter
public class Gav {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private  transient Set<Regel> regeln = new HashSet<>();
}