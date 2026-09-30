package org.example.finalmd3.service;

import org.example.finalmd3.dto.request.BuildingCreateDTO;
import org.example.finalmd3.dto.request.BuildingUpdateDTO;
import org.example.finalmd3.dto.response.BuildingResponseDTO;
import org.example.finalmd3.dto.response.PageResponse;
import org.example.finalmd3.entity.Building;
import org.springframework.web.multipart.MultipartFile;

public interface BuildingService {
    PageResponse<BuildingResponseDTO> getAll(int page, int size);
    BuildingResponseDTO getById(Long id);
    BuildingResponseDTO create(BuildingCreateDTO dto);
    BuildingResponseDTO update(Long id, BuildingUpdateDTO dto, MultipartFile File);
    BuildingResponseDTO updateStatus(Long id, Short status);
    PageResponse<BuildingResponseDTO> search(String name, Short status,  int page, int size);


}
