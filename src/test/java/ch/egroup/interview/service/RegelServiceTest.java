package ch.egroup.interview.service;

import ch.egroup.interview.domain.anagraphics.Branche;
import ch.egroup.interview.domain.anagraphics.Gav;
import ch.egroup.interview.domain.anagraphics.Kunde;
import ch.egroup.interview.domain.anagraphics.Mitarbeiter;
import ch.egroup.interview.domain.rules.Regel;
import ch.egroup.interview.domain.rules.composition.OverwriteStrategy;
import ch.egroup.interview.domain.rules.concrete.ArbeitzeitRegel;
import ch.egroup.interview.domain.rules.concrete.FerienRegel;
import ch.egroup.interview.domain.rules.concrete.LohnRegel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class RegelServiceTest {
    
    @Test
    @DisplayName("S1: Die Abbildung erfolgt dabei hierarchisch, von unten nach oben: GAV-Kunde-Mitarbeiter.")
    void buildGav() {
        //Arrange
        //Mitarbeiter Ebene
        Mitarbeiter builtMitarbeiterEben = buildMitarbeiterEben();

        //Kunde Ebene
        Kunde builtKundeEbene = buildKundeEbene();

        //Branche Ebene
        Branche builtBrancheEbene = buildBrancheEbene();

        //act
        GavBuilder gavBuilder = new GavBuilder();
        Gav gav = gavBuilder.
                withMitarbeiter(builtMitarbeiterEben).
                withKunde(builtKundeEbene).
                withBranche(builtBrancheEbene).
                build();

        //assert
        Assertions.assertAll(
                () -> assertTrue(gav.getRegeln().size() == 3),
                () -> assertTrue(gav.getRegeln().
                        stream().
                        filter(r -> r instanceof  ArbeitzeitRegel).
                        findFirst().map(Regel::getDescription).
                        filter(s -> s.contains("Mitarbeiter abgemacht Arbeitszeit Regelung")).stream().findFirst().isPresent()),
                () -> assertTrue(gav.getRegeln().
                        stream().
                        filter(r -> r instanceof  FerienRegel).
                        findFirst().map(Regel::getDescription).
                        filter(s -> s.contains("Beim Kunden geltend Ferien Regelung")).stream().findFirst().isPresent()),
                () -> assertTrue(gav.getRegeln().
                        stream().
                        filter(r -> r instanceof  LohnRegel).
                        findFirst().map(Regel::getDescription).
                        filter(s -> s.contains("Bei Branch geltend Lohn Regelung")).stream().findFirst().isPresent()));
    }

    private Branche buildBrancheEbene() {
        Branche branche = new Branche();
        ArbeitzeitRegel brancheArbeitzeitRegel = new ArbeitzeitRegel();
        brancheArbeitzeitRegel.setDescription("Beim Branche geltend Arbeitszeit Regelung");
        brancheArbeitzeitRegel.setCompositionStrategy(new OverwriteStrategy());
        brancheArbeitzeitRegel.setPauseDuration(45);

        FerienRegel brancheFerienRegel = new FerienRegel();
        brancheFerienRegel.setDescription("Beim Branche geltend Ferien Regelung");
        brancheFerienRegel.setCompositionStrategy(new OverwriteStrategy());  //Replaceable?
        brancheFerienRegel.setMaxNumOfDays(60);

        LohnRegel brancheLohnRegel = new LohnRegel();
        brancheLohnRegel.setDescription("Bei Branch geltend Lohn Regelung");
        brancheLohnRegel.setCompositionStrategy(new OverwriteStrategy());  //Replaceable?
        brancheLohnRegel.setMinLohn(1000);

        branche.addRegel(brancheArbeitzeitRegel);
        branche.addRegel(brancheFerienRegel);
        branche.addRegel(brancheLohnRegel);
        return branche;
    }

    private Kunde buildKundeEbene() {
        Kunde kunde = new Kunde();

        FerienRegel kundeFerienRegel = new FerienRegel();
        kundeFerienRegel.setDescription("Beim Kunden geltend Ferien Regelung");
        kundeFerienRegel.setCompositionStrategy(new OverwriteStrategy());  //TODO: Both Replaceable?

        kunde.addRegel(kundeFerienRegel);
        return kunde;
    }

    private Mitarbeiter buildMitarbeiterEben() {
        Mitarbeiter mitarbeiter = new Mitarbeiter();

        ArbeitzeitRegel mitarbeiterArbeitzeitRegel = new ArbeitzeitRegel();
        mitarbeiterArbeitzeitRegel.setDescription("Mitarbeiter abgemacht Arbeitszeit Regelung");
        mitarbeiterArbeitzeitRegel.setPauseDuration(15);
        mitarbeiterArbeitzeitRegel.setCompositionStrategy(new OverwriteStrategy());

        mitarbeiter.addRegel(mitarbeiterArbeitzeitRegel);
        return mitarbeiter;
    }
}