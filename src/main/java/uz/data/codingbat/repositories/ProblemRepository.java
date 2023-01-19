package uz.data.codingbat.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import uz.data.codingbat.entities.Problem;

public interface ProblemRepository extends PagingAndSortingRepository<Problem, Integer> {

}