package org.example.finalmd3.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuildingCreateDTO {
    @NotBlank(message = "Tên tòa nhà không được để trống")
    @Size(max = 100, message = "Tên tòa nhà tối đa 100 ký tự")
    private String buildingName;

    @NotNull(message = "Diện tích xây dựng không được để trống")
    @Positive(message = "Diện tích phải lớn hơn 0")
    private Double buildingArea;

    @NotBlank(message = "Đơn vị diện tích không được để trống")
    @Size(max = 10, message = "Đơn vị diện tích tối đa 10 ký tự")
    private String areaUnit;

    @NotNull(message = "Ngày khởi công không được để trống")
    private LocalDate startDate;

    @NotNull(message = "Thời gian xây dựng không được để trống")
    private Integer time;

    @NotBlank(message = "Đơn vị thời gian không được để trống")
    @Size(max = 10,message = "Đơn vị thời gian tối đa 10 ký tự")
    private String timeUnit;

    @NotBlank(message = "Đường dẫn thiết kế không được để trống")
    private String design;

    @NotBlank(message = "Nội dung xây dựng không được để trống")
    @Size(max = 255, message = "Nội dung tối đa 255 ký tự")
    private String content;

    private Short status;
}

