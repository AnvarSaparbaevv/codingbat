package uz.data.codingbat.services;

import uz.data.codingbat.entities.Test;
import uz.data.codingbat.templates.Result;

import java.util.List;

public interface TestService {

    List<Test> getTestList();

    Test getTest(Integer testId);

    Result addTest(Test test);

    Result updateTest(Test test, Integer testId);

    Result deleteTest(Integer testId);
}
