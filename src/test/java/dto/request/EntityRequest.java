package dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityRequest {
    private String title;

    private boolean verified;

    @JsonProperty("important_numbers")
    private List<Integer> importantNumbers;

    private AdditionRequest addition;
}
