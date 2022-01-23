package ch.egroup.interview.service;

import ch.egroup.interview.domain.anagraphics.Branche;
import ch.egroup.interview.domain.anagraphics.Gav;
import ch.egroup.interview.domain.anagraphics.Kunde;
import ch.egroup.interview.domain.anagraphics.Mitarbeiter;
import org.springframework.stereotype.Component;

@Component
public class RegelService {
    Gav buildGav(Mitarbeiter mitarbeiter, Kunde kunde, Branche branche){
        return new GavBuilder()
                .withBranche(branche)
                .withKunde(kunde)
                .withMitarbeiter(mitarbeiter).
                build();
    }
}
