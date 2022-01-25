package ch.egroup.interview.domain.anagraphics;

import ch.egroup.interview.domain.rules.Regel;
import lombok.*;

import javax.persistence.*;
import java.util.*;

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

	@OneToMany(mappedBy = "mitarbeiter", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MitarbeiterOptionalField> mitarbeiterOptionalFields = new ArrayList<>();

	public Set<Regel> getRegels() {
		return new HashSet<>(regels);
	}

	public void addRegel(Regel newRegel){
		regels.add(newRegel);
	}
}