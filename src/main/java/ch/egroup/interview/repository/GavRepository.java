package ch.egroup.interview.repository;

import ch.egroup.interview.domain.anagraphics.Branche;
import ch.egroup.interview.domain.anagraphics.Gav;
import ch.egroup.interview.domain.anagraphics.Kunde;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "gav", path = "gav")
public interface GavRepository extends JpaRepository<Gav, Long>, JpaSpecificationExecutor<Kunde> {
    @Override
    <S extends Gav> S save(S s);

    @Override
    void delete(Gav kunde);

    @Override
    <S extends Gav> List<S> findAll(Example<S> example, Sort sort);
}