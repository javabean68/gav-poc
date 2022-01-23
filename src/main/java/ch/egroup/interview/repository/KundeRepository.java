package ch.egroup.interview.repository;

import ch.egroup.interview.domain.anagraphics.Kunde;
import ch.egroup.interview.domain.rules.Regel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "kunden", path = "kunden")
public interface KundeRepository extends JpaRepository<Kunde, Long>, JpaSpecificationExecutor<Kunde> {
    @Override
    <S extends Kunde> S save(S s);

    @Override
    void delete(Kunde kunde);

    @Override
    <S extends Kunde> List<S> findAll(Example<S> example, Sort sort);
}