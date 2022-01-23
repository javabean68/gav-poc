package ch.egroup.interview.domain.rules.composition;

import ch.egroup.interview.domain.rules.Regel;

import java.util.Set;

public interface CompositionStrategy {
    void composeWith(Set<? super Regel> targetSet, Regel anotherRegel);
}