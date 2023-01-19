package uz.data.codingbat.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import uz.data.codingbat.entities.Language;

@Repository
public interface LanguageRepository extends PagingAndSortingRepository<Language, Integer> {

    boolean existsLanguageByName(String name);

}