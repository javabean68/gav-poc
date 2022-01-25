package ch.egroup.interview.service;

import ch.egroup.interview.domain.anagraphics.Branche;
import ch.egroup.interview.domain.anagraphics.Gav;
import ch.egroup.interview.domain.anagraphics.Kunde;
import ch.egroup.interview.domain.anagraphics.Mitarbeiter;
import ch.egroup.interview.repository.GavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
/**
 * https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html
 */
public class RegelService {

    @Autowired
    GavRepository gavRepository;

    @PreAuthorize("hasRole('ADMIN') && #mitarbeiter.firstname == principal.username")
    Gav buildGav(Mitarbeiter mitarbeiter, Kunde kunde, Branche branche){
        Gav gav = new GavBuilder()
                .withBranche(branche)
                .withKunde(kunde)
                .withMitarbeiter(mitarbeiter).
                build();
        gavRepository.save(gav);
        return gav;
    }
}