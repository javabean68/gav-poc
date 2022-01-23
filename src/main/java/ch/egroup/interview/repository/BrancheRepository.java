package ch.egroup.interview.repository;

import ch.egroup.interview.domain.anagraphics.Branche;
import ch.egroup.interview.domain.anagraphics.Kunde;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "branche", path = "branche")
public interface BrancheRepository extends JpaRepository<Branche, Long>, JpaSpecificationExecutor<Kunde> {
    @Override
    <S extends Branche> S save(S s);

    @Override
    void delete(Branche kunde);

    @Override
    <S extends Branche> List<S> findAll(Example<S> example, Sort sort);
}