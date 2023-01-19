package uz.data.codingbat.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartDTO {
    private String name;
    private String about;
    private Integer difficulty;
    private Integer languageId;
}