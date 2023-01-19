package uz.data.codingbat.services.implementss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.data.codingbat.entities.Problem;
import uz.data.codingbat.entities.Test;
import uz.data.codingbat.payloads.ProblemDTO;
import uz.data.codingbat.repositories.PartRepository;
import uz.data.codingbat.repositories.ProblemRepository;
import uz.data.codingbat.repositories.TestRepository;
import uz.data.codingbat.services.ProblemService;
import uz.data.codingbat.templates.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private PartRepository partRepository;

    @Override
    public List<Problem> getProblemList() {
        return (List<Problem>) problemRepository.findAll();
    }

    @Override
    public Problem getProblem(Integer problemId) {
        return problemRepository.findById(problemId).orElse(null);
    }

    @Override
    public Result addProblem(ProblemDTO problemDTO) {
        List<Integer> testList = problemDTO.getTestId();
        List<Test> tests = new ArrayList<>();
        Problem newProblem = new Problem();
        newProblem.setName(problemDTO.getName());
        newProblem.setText(problemDTO.getText());
        partRepository.findById(problemDTO.getPartId()).ifPresent(newProblem::setPart);

        for (Integer integer : testList) {
            Optional<Test> optionalTest = testRepository.findById(integer);
            if (!optionalTest.isPresent()) {
                return new Result("Tests not found!", false, HttpStatus.NOT_FOUND);
            }
            tests.add(optionalTest.get());
        }
        newProblem.setTest(tests);
        problemRepository.save(newProblem);
        return new Result("Successfully saved!", true, HttpStatus.CREATED);
    }

    @Override
    public Result updateProblem(ProblemDTO problemDTO, Integer problemId) {
        Optional<Problem> optionalProblem = problemRepository.findById(problemId);
        if (optionalProblem.isPresent()) {
            Problem updatingProblem = optionalProblem.get();
            updatingProblem.setName(problemDTO.getName());
            updatingProblem.setText(problemDTO.getText());
            partRepository.findById(problemDTO.getPartId()).ifPresent(updatingProblem::setPart);

            List<Integer> updatingTest = problemDTO.getTestId();
            List<Test> testList = new ArrayList<>();

            for (Integer integer : updatingTest) {
                Optional<Test> optionalTest = testRepository.findById(integer);
                if (!optionalTest.isPresent()) {
                    return new Result("Test not found!", false, HttpStatus.NOT_FOUND);
                }
                testList.add(optionalTest.get());
            }

            updatingProblem.setTest(testList);
            problemRepository.save(updatingProblem);
            return new Result("Problems updated!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Problems not found!", false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Result deleteProblem(Integer problemId) {
        Optional<Problem> optionalProblem = problemRepository.findById(problemId);
        if (optionalProblem.isPresent()) {
            problemRepository.deleteById(problemId);
            return new Result("Problem deleted!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Problem not found!", false, HttpStatus.NOT_FOUND);
    }
}
