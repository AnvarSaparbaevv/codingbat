package uz.data.codingbat.services;

import uz.data.codingbat.entities.Problem;
import uz.data.codingbat.payloads.ProblemDTO;
import uz.data.codingbat.templates.Result;

import java.util.List;

public interface ProblemService {

    List<Problem> getProblemList();

    Problem getProblem(Integer problemId);

    Result addProblem(ProblemDTO problemDTO);

    Result updateProblem(ProblemDTO problemDTO, Integer problemId);

    Result deleteProblem(Integer problemId);

}
