package ch.egroup.interview.repository;

import ch.egroup.interview.domain.rules.concrete.ArbeitzeitRegel;
import ch.egroup.interview.domain.rules.composition.UnionStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RegelRepositoryTest {

    @Autowired
    RegelRepository regelRepository;

    @Test
    void save() {
        //arrange
        ArbeitzeitRegel arbeitzeitRegel = new ArbeitzeitRegel();
        arbeitzeitRegel.setDescription("Diese Regel gilt für...");
        arbeitzeitRegel.setValidFrom(LocalDate.of(1965, 12, 1));
        arbeitzeitRegel.setValidTo(LocalDate.of(2030, 12, 1));
        arbeitzeitRegel.setCompositionStrategy(new UnionStrategy());

        //act
        ArbeitzeitRegel regel = regelRepository.save(arbeitzeitRegel);

        //assert
        assertTrue(regelRepository.findById(regel.getId()).isPresent());
    }
}