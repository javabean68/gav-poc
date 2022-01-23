package ch.egroup.interview.service;

import ch.egroup.interview.domain.anagraphics.Branche;
import ch.egroup.interview.domain.anagraphics.Gav;
import ch.egroup.interview.domain.anagraphics.Kunde;
import ch.egroup.interview.domain.anagraphics.Mitarbeiter;
import ch.egroup.interview.domain.rules.Regel;

class GavBuilder {
    private Gav instance = new Gav();
    private Mitarbeiter mitarbeiter;
    private Kunde kunde;
    private Branche branche;

    public GavBuilder withMitarbeiter(Mitarbeiter mitarbeiter){
        this.mitarbeiter = mitarbeiter;
        return this;
    }

    public GavBuilder withKunde(Kunde kunde) {
        this.kunde = kunde;
        return this;
    }

    public GavBuilder withBranche(Branche branche){
        this.branche = branche;
        return this;
    }

    Gav build() {
        if(branche != null) this.instance.getRegeln().addAll(branche.getRegels());
        if(kunde != null) {
            for (Regel kundeRegel : kunde.getRegels()) {
                kundeRegel.addToSet(this.instance.getRegeln());
            }
        }
        if(mitarbeiter != null) {
            for (Regel mitarbeiterRegel : mitarbeiter.getRegels()) {
                mitarbeiterRegel.addToSet(this.instance.getRegeln());
            }
        }

        return this.instance;
    }
}
