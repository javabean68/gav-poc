package ch.egroup.interview.domain.anagraphics;

import ch.egroup.interview.domain.rules.Regel;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Mitarbeiter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String firstName;
	private String lastName;

	@OneToMany(mappedBy = "mitarbeiter", cascade = CascadeType.REFRESH, orphanRemoval = true)
	private Set<Regel> regels = new LinkedHashSet<>();

	public Set<Regel> getRegels() {
		return new HashSet<>(regels);
	}

	public void addRegel(Regel newRegel){
		regels.add(newRegel);
	}
}