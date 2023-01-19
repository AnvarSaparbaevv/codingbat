package uz.data.codingbat.services.implementss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.data.codingbat.entities.Language;
import uz.data.codingbat.entities.Part;
import uz.data.codingbat.payloads.PartDTO;
import uz.data.codingbat.repositories.LanguageRepository;
import uz.data.codingbat.repositories.PartRepository;
import uz.data.codingbat.services.PartService;
import uz.data.codingbat.templates.Result;

import java.util.List;
import java.util.Optional;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    private PartRepository partRepository;
    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public List<Part> getPartList() {
        return (List<Part>) partRepository.findAll();
    }

    @Override
    public Part getPart(Integer partId) {
        return partRepository.findById(partId).orElse(null);
    }

    @Override
    public Result addPart(PartDTO partDTO) {
        Part newPart = new Part();
        newPart.setName(partDTO.getName());
        newPart.setAbout(partDTO.getAbout());
        newPart.setDifficulty(partDTO.getDifficulty());
        Optional<Language> optionalLanguage = languageRepository.findById(partDTO.getLanguageId());
        if (optionalLanguage.isPresent()) {
            newPart.setLanguage(optionalLanguage.get());
            partRepository.save(newPart);
            return new Result("Successfully saved!", true, HttpStatus.CREATED);
        }
        return new Result("Language not found!", false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Result updatePart(PartDTO partDTO, Integer partId) {
        Optional<Part> optionalPart = partRepository.findById(partId);
        if (optionalPart.isPresent()) {
            Part updatingPart = optionalPart.get();
            updatingPart.setName(partDTO.getName());
            updatingPart.setAbout(partDTO.getAbout());
            updatingPart.setDifficulty(partDTO.getDifficulty());
            Optional<Language> optionalLanguage = languageRepository.findById(partDTO.getLanguageId());
            if (optionalLanguage.isPresent()) {
                updatingPart.setLanguage(optionalLanguage.get());
                partRepository.save(updatingPart);
                return new Result("Part updated!", true, HttpStatus.ACCEPTED);
            }
            return new Result("Language not found!", false, HttpStatus.NOT_FOUND);
        }
        return new Result("Part not found!", false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Result deletePart(Integer partId) {
        Optional<Part> optionalPart = partRepository.findById(partId);
        if(optionalPart.isPresent()){
            partRepository.deleteById(partId);
            return new Result("Part deleted!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Part not found!", false, HttpStatus.NOT_FOUND);
    }
}