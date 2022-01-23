package ch.egroup.interview.domain.rules.composition;

import ch.egroup.interview.domain.rules.Regel;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Die Regel wird überschrieben.
 * TODO: Es sollte überprüfen ob eine Regel dergleichen Art schon vorhanden ist und
 * ob sie auch ist mit der Strategy Overwrite kenngezeichet, sonst eine Exception werfen
 */
public class OverwriteStrategy implements CompositionStrategy {
    @Override
    public void composeWith(Set<? super Regel> targetSet, Regel anotherRegel) {
        //finde ob diese Regel bei der Set schon vorhanden ist
        List<? super Regel> schonVorhandenInstanzen = targetSet.stream().
                filter(r -> r.getClass().equals(anotherRegel.getClass())).collect(Collectors.toList());
        targetSet.removeAll(schonVorhandenInstanzen);
        targetSet.add(anotherRegel);
    }
}