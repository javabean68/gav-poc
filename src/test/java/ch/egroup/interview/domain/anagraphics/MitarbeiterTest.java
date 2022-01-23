package ch.egroup.interview.domain.anagraphics;

import ch.egroup.interview.domain.rules.concrete.ArbeitzeitRegel;
import ch.egroup.interview.repository.MitarbeitRepository;
import ch.egroup.interview.repository.RegelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MitarbeiterTest {

    @Autowired
    MitarbeitRepository mitarbeitRepository;

    @Test
    void setRegels() {
        //arrange
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setFirstName("Fabio");
        mitarbeiter.setLastName("Salvi");

        ArbeitzeitRegel arbeitzeitRegel = new ArbeitzeitRegel();
        arbeitzeitRegel.setMaxNumOfHoursPerDay(7);
        arbeitzeitRegel.setPauseDuration(15);
        arbeitzeitRegel.setDescription("A specific rule for the current employee blah blah :)" +
                "");
        mitarbeiter.setRegels(Set.of(arbeitzeitRegel));

        //act
        mitarbeitRepository.save(mitarbeiter);

        //assert
        List<Mitarbeiter> byLastName = mitarbeitRepository.findByLastName("Salvi");
        Assertions.assertAll(
                () -> assertTrue(byLastName.size() == 1),
                () -> assertTrue(byLastName.get(0).getRegels().size() == 1)
        );
    }
}