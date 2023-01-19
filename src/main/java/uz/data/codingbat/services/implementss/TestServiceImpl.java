package uz.data.codingbat.services.implementss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.data.codingbat.entities.Test;
import uz.data.codingbat.repositories.TestRepository;
import uz.data.codingbat.services.TestService;
import uz.data.codingbat.templates.Result;

import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository repository;

    @Override
    public List<Test> getTestList() {
        return (List<Test>) repository.findAll();
    }

    @Override
    public Test getTest(Integer testId) {
        return repository.findById(testId).orElse(null);
    }

    @Override
    public Result addTest(Test test) {
        repository.save(test);
        return new Result("Successfully saved!", true, HttpStatus.CREATED);
    }

    @Override
    public Result updateTest(Test test, Integer testId) {
        Optional<Test> optionalTest = repository.findById(testId);
        if (!optionalTest.isPresent()) {
            return new Result("Test not found!", false, HttpStatus.NOT_FOUND);
        }
        Test updating = optionalTest.get();
        updating.setTestCode(test.getTestCode());
        updating.setResult(test.getResult());
        repository.save(updating);
        return new Result("Test updated!", true, HttpStatus.ACCEPTED);

    }

    @Override
    public Result deleteTest(Integer testId) {
        Optional<Test> optionalTest = repository.findById(testId);
        if (optionalTest.isPresent()) {
            repository.deleteById(testId);
            return new Result("Test deleted!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Test not found!", false, HttpStatus.NOT_FOUND);
    }
}