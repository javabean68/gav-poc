package ch.egroup.interview.domain.rules;

import ch.egroup.interview.domain.rules.composition.OverwriteStrategy;
import ch.egroup.interview.domain.rules.composition.UnionStrategy;
import ch.egroup.interview.domain.rules.concrete.ArbeitzeitRegel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegelTest {

    @Test
    @DisplayName("Testet die Overwrite Strategy")
    void givenOverwriteStrategy_addToSetReplaceTheCurrentStrategy() {
        //arrange
        ArbeitzeitRegel ursprünglicheArbeitzeitRegel = new ArbeitzeitRegel();
        ursprünglicheArbeitzeitRegel.setMaxNumOfHoursPerDay(10);
        ursprünglicheArbeitzeitRegel.setCompositionStrategy(new OverwriteStrategy());

        Set<? super Regel> regels = new HashSet<>();
        regels.add(ursprünglicheArbeitzeitRegel);

        ArbeitzeitRegel newArbeitzeitRegel = new ArbeitzeitRegel();
        newArbeitzeitRegel.setMaxNumOfHoursPerDay(10);
        newArbeitzeitRegel.setCompositionStrategy(new OverwriteStrategy());

        //act
        newArbeitzeitRegel.addToSet(regels);

        //assert
        assertTrue(regels.contains(newArbeitzeitRegel));
        assertFalse(regels.contains(ursprünglicheArbeitzeitRegel));
    }

    @Test
    @DisplayName("Testet die Union Strategy")
    void givenUnionStrategy_addToSetTheCurrentStrategyAndTheNewStartegyArePresent() {
        //arrange
        ArbeitzeitRegel ursprünglicheArbeitzeitRegel = new ArbeitzeitRegel();
        ursprünglicheArbeitzeitRegel.setMaxNumOfHoursPerDay(10);
        ursprünglicheArbeitzeitRegel.setCompositionStrategy(new OverwriteStrategy());

        Set<? super Regel> regels = new HashSet<>();
        regels.add(ursprünglicheArbeitzeitRegel);

        ArbeitzeitRegel newArbeitzeitRegel = new ArbeitzeitRegel();
        newArbeitzeitRegel.setMaxNumOfHoursPerDay(10);
        newArbeitzeitRegel.setCompositionStrategy(new UnionStrategy());

        //act
        newArbeitzeitRegel.addToSet(regels);

        //assert
        assertTrue(regels.contains(newArbeitzeitRegel));
        assertTrue(regels.contains(ursprünglicheArbeitzeitRegel));
    }
}