package org.example.finalmd3.mapper;

import org.example.finalmd3.dto.request.BuildingCreateDTO;
import org.example.finalmd3.dto.request.BuildingUpdateDTO;
import org.example.finalmd3.dto.response.BuildingResponseDTO;
import org.example.finalmd3.entity.Building;

public class BuildingMapper {
    private BuildingMapper() {}
    public static Building toEntity(BuildingCreateDTO dto){
        return Building.builder()
                .buildingName(dto.getBuildingName().trim())
                .buildingArea(dto.getBuildingArea())
                .areaUnit(dto.getAreaUnit().trim())
                .startDate(dto.getStartDate())
                .time(dto.getTime())
                .timeUnit(dto.getTimeUnit().trim())
                .design(dto.getDesign())
                .content(dto.getContent().trim())
                .status(dto.getStatus() != null ? dto.getStatus() : 1)
                .build();

    }
    public static void updateEntity(Building building,  BuildingUpdateDTO dto){
        building.setBuildingName(dto.getBuildingName().trim());
        building.setBuildingArea(dto.getBuildingArea());
        building.setAreaUnit(dto.getAreaUnit().trim());
        building.setStartDate(dto.getStartDate());
        building.setTime(dto.getTime());
        building.setTimeUnit(dto.getTimeUnit().trim());
        if(dto.getDesign() != null & !dto.getDesign().isBlank()){
            building.setDesign(dto.getDesign());
        }
        building.setContent(dto.getContent().trim());
        if(dto.getStatus() != null ){
            building.setStatus(dto.getStatus());
        }
    }
    public static BuildingResponseDTO toResponse(Building building){
        return BuildingResponseDTO.builder()
                .id(building.getId())
                .buildingName(building.getBuildingName())
                .buildingArea(building.getBuildingArea())
                .areaUnit(building.getAreaUnit())
                .startDate(building.getStartDate())
                .time(building.getTime())
                .timeUnit(building.getTimeUnit())
                .design(building.getDesign())
                .content(building.getContent())
                .status(building.getStatus())
                .build();
    }
}
