package org.example.finalmd3.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusUpdate {
    @NotNull(message = "Trạng thái không được để trống")
    Short status;
}
