package uz.data.codingbat.templates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String message;
    private boolean success;
    private HttpStatus status;
}