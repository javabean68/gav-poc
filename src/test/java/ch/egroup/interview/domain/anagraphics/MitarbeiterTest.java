package ch.egroup.interview.domain.anagraphics;

import ch.egroup.interview.domain.rules.concrete.ArbeitzeitRegel;
import ch.egroup.interview.repository.MitarbeitRepository;
import ch.egroup.interview.repository.RegelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MitarbeiterTest {

    @Autowired
    MitarbeitRepository mitarbeitRepository;

    @Test
    @Transactional
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
        mitarbeiter.addRegel(arbeitzeitRegel);

        //act
        mitarbeitRepository.save(mitarbeiter);

        //assert
        List<Mitarbeiter> byLastName = mitarbeitRepository.findByLastName("Salvi");
        Assertions.assertAll(
                () -> assertTrue(byLastName.size() == 1),
                () -> assertTrue(byLastName.get(0).getRegels().size() == 1)
        );
    }

    @Test
    @Transactional
    void manageAdditionalField() {
        //arrange
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setFirstName("Fabio");
        mitarbeiter.setLastName("Salvi");

        MitarbeiterOptionalField mitarbeiterAlter = new MitarbeiterOptionalField();
        mitarbeiterAlter.setName("age");
        mitarbeiterAlter.setType(FieldType.STRING);
        mitarbeiterAlter.setValue("34");

        //act.
        mitarbeiter.getMitarbeiterOptionalFields().add(mitarbeiterAlter);
        mitarbeitRepository.save(mitarbeiter);

        //assert
        List<Mitarbeiter> byLastName = mitarbeitRepository.findByLastName("Salvi");
        Assertions.assertAll(
                () -> assertTrue(byLastName.size() == 1),
                () -> assertTrue(byLastName.get(0).getMitarbeiterOptionalFields().size() == 1)
        );
    }
}