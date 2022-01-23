package ch.egroup.interview.domain.rules.composition;

import ch.egroup.interview.domain.rules.Regel;

import java.util.Set;

/**
 * Die Regel wird hinzugefügt.
 * TODO: Es sollte überprüfen ob eine Regel dergleichen Art schon vorhanden ist und
 * ob sie auch ist mit der Strategy Union kenngezeichet, sonst eine Exception werfen
 */
public class UnionStrategy implements CompositionStrategy {
    @Override
    public void composeWith(Set<? super Regel> targetSet, Regel anotherRegel) {
        targetSet.add(anotherRegel);
    }
}
