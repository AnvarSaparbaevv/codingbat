package uz.data.codingbat.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import uz.data.codingbat.entities.Test;

public interface TestRepository extends PagingAndSortingRepository<Test, Integer> {



}