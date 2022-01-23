package ch.egroup.interview.domain.anagraphics;

import ch.egroup.interview.domain.rules.Regel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Mitarbeiter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String firstName;
	private String lastName;

	@OneToMany(mappedBy = "mitarbeiter", cascade = CascadeType.REFRESH, orphanRemoval = true)
	private Set<Regel> regels = new LinkedHashSet<>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Regel> getRegels() {
		return new HashSet<>(regels);
	}

	public void addRegel(Regel newRegel){
		regels.add(newRegel);
	}
}
