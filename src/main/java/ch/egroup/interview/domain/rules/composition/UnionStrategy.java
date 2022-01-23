package ch.egroup.interview.domain.rules.composition;

import ch.egroup.interview.domain.rules.Regel;

import java.util.Set;

public class UnionStrategy implements CompositionStrategy {
    @Override
    public void composeWith(Set<? super Regel> targetSet, Regel anotherRegel) {
        targetSet.add(anotherRegel);
    }
}
