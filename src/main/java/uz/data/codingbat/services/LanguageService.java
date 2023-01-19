package uz.data.codingbat.services;

import uz.data.codingbat.entities.Language;
import uz.data.codingbat.templates.Result;

import java.util.List;

public interface   LanguageService {
    List<Language> getLanguageList();

    Language getLanguageById(Integer languageId);

    Result addLanguage(Language language);

    Result updateLanguage(Language language, Integer languageId);

    Result deleteLanguage(Integer languageId);
}
