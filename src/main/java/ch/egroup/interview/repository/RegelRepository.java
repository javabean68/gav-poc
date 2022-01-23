package ch.egroup.interview.repository;

import ch.egroup.interview.domain.rules.Regel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "rules", path = "rules")
public interface RegelRepository extends JpaRepository<Regel, Long>, JpaSpecificationExecutor<Regel> {
    @Override
    <S extends Regel> S save(S s);

    @Override
    List<Regel> findAll(Specification<Regel> specification, Sort sort);

    @Override
    <S extends Regel> List<S> findAll(Example<S> example, Sort sort);

    List<Regel> findByValidFrom(LocalDate validFrom);

    List<Regel> findAllByValidFromLessThanEqualAndValidToGreaterThanEqual(LocalDate fromDate, LocalDate endDate);
}