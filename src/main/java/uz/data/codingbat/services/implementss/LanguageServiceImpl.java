package uz.data.codingbat.services.implementss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.data.codingbat.entities.Language;
import uz.data.codingbat.repositories.LanguageRepository;
import uz.data.codingbat.services.LanguageService;
import uz.data.codingbat.templates.Result;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository repository;


    @Override
    public List<Language> getLanguageList() {
        return (List<Language>) repository.findAll();
    }

    @Override
    public Language getLanguageById(Integer languageId) {
        Optional<Language> optionalLanguage = repository.findById(languageId);
        return optionalLanguage.orElse(null);
    }

    @Override
    public Result addLanguage(Language language) {
        boolean languageByName = repository.existsLanguageByName(language.getName());
        if (languageByName) {
            return new Result("This language already exist", false, HttpStatus.CONFLICT);
        }
        repository.save(language);
        return new Result("Successfully saved!", true, HttpStatus.CREATED);
    }

    @Override
    public Result updateLanguage(Language language, Integer languageId) {
        boolean languageByName = repository.existsLanguageByName(language.getName());
        if (languageByName) {
            return new Result("This language already exist", false, HttpStatus.CONFLICT);
        }
        Optional<Language> languageOptional = repository.findById(languageId);
        if (!languageOptional.isPresent()) {
            return new Result("Language not found!", false, HttpStatus.NOT_FOUND);
        }
        Language updating = languageOptional.get();
        updating.setName(language.getName());
        repository.save(updating);
        return new Result("Language updated!", true, HttpStatus.ACCEPTED);
    }

    @Override
    public Result deleteLanguage(Integer languageId) {
        Optional<Language> optionalLanguage = repository.findById(languageId);
        if (optionalLanguage.isPresent()) {
            repository.deleteById(languageId);
            return new Result("Language deleted!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Language not found!", false, HttpStatus.NOT_FOUND);
    }
}
