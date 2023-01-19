package uz.data.codingbat.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import uz.data.codingbat.entities.Part;

@Repository
public interface PartRepository extends PagingAndSortingRepository<Part, Integer> {

}