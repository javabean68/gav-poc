package ch.egroup.interview.repository;

import ch.egroup.interview.domain.anagraphics.Mitarbeiter;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface MitarbeitRepository extends PagingAndSortingRepository<Mitarbeiter, Long> {
	List<Mitarbeiter> findByLastName(@Param("name") String name);
}