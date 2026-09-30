package org.example.finalmd3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "buildings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "building_name", nullable = false, unique = true, length = 100)
    private String buildingName;

    @Column(name = "building_area", nullable = false)
    private Double buildingArea;

    @Column(name = "area_unit", nullable = false, length = 10)
    private String areaUnit;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "time", nullable = false)
    private Integer time;

    @Column(name = "time_unit", nullable = false, length = 10)
    private String timeUnit;

    @Column(name = "design", nullable = false, length = 255)
    private String design;

    @Column(name = "content", nullable = false, length = 255)
    private String content;

    @Column(name = "status", nullable = false)
    @Builder.Default
    private Short status = 1;

}
