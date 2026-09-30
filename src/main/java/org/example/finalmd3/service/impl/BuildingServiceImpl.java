package org.example.finalmd3.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.finalmd3.dto.request.BuildingCreateDTO;
import org.example.finalmd3.dto.request.BuildingUpdateDTO;
import org.example.finalmd3.dto.response.BuildingResponseDTO;
import org.example.finalmd3.dto.response.PageResponse;
import org.example.finalmd3.entity.Building;
import org.example.finalmd3.exception.DuplicateResourceException;
import org.example.finalmd3.exception.ResourceNotFoundException;
import org.example.finalmd3.mapper.BuildingMapper;
import org.example.finalmd3.repository.BuildingRepository;
import org.example.finalmd3.service.BuildingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final String UPLOAD_DIR = "uploads/";

    @Override
    @Transactional(readOnly = true)
    public PageResponse<BuildingResponseDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BuildingResponseDTO> result = buildingRepository.findAll(pageable)
                .map(BuildingMapper::toResponse);
        return PageResponse.from(result);
    }

    @Override
    @Transactional(readOnly = true)
    public BuildingResponseDTO getById(Long id) {
        return BuildingMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public BuildingResponseDTO create(BuildingCreateDTO dto) {
        if(buildingRepository.existsBuildingByBuildingName(dto.getBuildingName().trim())){
            throw new DuplicateResourceException("Tên tòa nhà đã tồn tại: " + dto.getBuildingName());
        }
        Building building = BuildingMapper.toEntity(dto);
        Building saved =  buildingRepository.save(building);
        return BuildingMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public BuildingResponseDTO update(Long id, BuildingUpdateDTO dto, MultipartFile file) {
        Building building = findOrThrow(id);
        if(buildingRepository.existsBuildingByBuildingNameAndIdNot(dto.getBuildingName().trim(), id)){
            throw new DuplicateResourceException("Tên tòa nhà đã tồn tại ở bản ghi khác: " + dto.getBuildingName());
        }
        if(file != null && !file.isEmpty()){
            String fileName = saveFile(file);
            dto.setDesign(fileName);
        }
        BuildingMapper.updateEntity(building, dto);
        Building updated = buildingRepository.save(building);
        log.info("Cập nhật tòa nhà ID: {}", id);
        return BuildingMapper.toResponse(building);
    }

    @Override
    public BuildingResponseDTO updateStatus(Long id, Short status) {
        Building building = findOrThrow(id);

        if(building.getStatus() != null && building.getStatus() == 2){
            throw new IllegalStateException("Tòa nhà đã hoàn thành (status = 2), không thể thay đổi trạng thái!");

        }
        building.setStatus(status);
        Building updated = buildingRepository.save(building);
        return BuildingMapper.toResponse(updated);
    }

    @Override
    public PageResponse<BuildingResponseDTO> search(String name, Short status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        String searchNmae =(name != null && !name.isBlank()) ? name.trim() : null;
        Page<BuildingResponseDTO> result = buildingRepository.search(searchNmae, status, pageable)
                .map(BuildingMapper::toResponse);
        return PageResponse.from(result);
    }

    private Building findOrThrow(Long id){
        return buildingRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy nhà có ID = " + id));
    }
    private String saveFile(MultipartFile file){
        try{
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            String uiqueName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(uiqueName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        }
        catch (IOException e){
            throw new RuntimeException("Lỗi upload ảnh: " + e.getMessage());
        }
    }
}
