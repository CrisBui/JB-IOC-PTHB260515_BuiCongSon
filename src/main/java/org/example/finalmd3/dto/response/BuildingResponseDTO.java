package org.example.finalmd3.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingResponseDTO {
    Long id;
    String buildingName;
    Double buildingArea;
    String areaUnit;
    LocalDate startDate;
    Integer time;
    String timeUnit;
    String design;
    String content;
    Short status;
}
