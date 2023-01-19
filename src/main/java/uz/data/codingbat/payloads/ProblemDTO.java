package uz.data.codingbat.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDTO {
    private String name;
    private String text;
    private Integer partId;
    private List<Integer> testId;
}