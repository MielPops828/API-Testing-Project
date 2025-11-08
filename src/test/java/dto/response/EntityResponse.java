package dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dto.request.AdditionRequest;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityResponse {
    private int id;

    private String title;

    private boolean verified;

    @JsonProperty("important_numbers")
    private List<Integer> importantNumbers;

    private AdditionResponse addition;
}
