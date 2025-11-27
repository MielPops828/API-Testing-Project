package dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionRequest {
    @JsonProperty("additional_info")
    private String additionalInfo;

    @JsonProperty("additional_number")
    private int additionalNumber;
}
