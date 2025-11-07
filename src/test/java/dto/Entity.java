package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entity {
    private int id;

    private String title;

    private boolean verified;

    @JsonProperty("important_numbers")
    private List<Integer> importantNumbers;

    private Addition addition;
}
