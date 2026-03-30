package org.application.filetrackerapi.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.antlr.v4.runtime.misc.NotNull;
import org.application.filetrackerapi.store.entities.ProjectEntity;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AskDto {
    Boolean answer;

    public static AskDto makeDefault(Boolean answer) {
        return builder().answer(answer).build();
    }


}
