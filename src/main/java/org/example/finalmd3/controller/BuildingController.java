package org.example.finalmd3.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finalmd3.dto.request.BuildingCreateDTO;
import org.example.finalmd3.dto.request.BuildingUpdateDTO;
import org.example.finalmd3.dto.request.StatusUpdate;
import org.example.finalmd3.dto.response.ApiResponse;
import org.example.finalmd3.dto.response.BuildingResponseDTO;
import org.example.finalmd3.dto.response.PageResponse;
import org.example.finalmd3.service.BuildingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/buildings")
@RequiredArgsConstructor
public class BuildingController {
    private final BuildingService buildingService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<BuildingResponseDTO>>> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size){
            PageResponse<BuildingResponseDTO> result = buildingService.getAll(page, size);
            return  ResponseEntity.ok(ApiResponse.success(result, "Lấy danh sách tòa nhà thành công", HttpStatus.OK));
        }
    @PostMapping
    public ResponseEntity<ApiResponse<BuildingResponseDTO>> create(
            @Valid @RequestBody BuildingCreateDTO request){
        BuildingResponseDTO result = buildingService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(result, "Thêm mới tòa nhà thành công", HttpStatus.OK));
    }


    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<BuildingResponseDTO>> update(
            @PathVariable long id,
            @Valid @RequestPart("data") BuildingUpdateDTO request,
            @RequestPart(value = "file", required = false) MultipartFile file){
        BuildingResponseDTO result = buildingService.update(id, request, file);
        return ResponseEntity.ok(ApiResponse.success(result, "Cập nhật tòa nhà thành công ", HttpStatus.OK));
    }
   @PutMapping("/{id}/json")
   public ResponseEntity<ApiResponse<BuildingResponseDTO>> updateJson(
           @PathVariable long id,
           @Valid @RequestBody BuildingUpdateDTO request) {
        BuildingResponseDTO result = buildingService.update(id, request, null);
        return ResponseEntity.ok(ApiResponse.success(result, "Cập nhật tòa nhà thành công", HttpStatus.OK));

   }
   @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<BuildingResponseDTO>> updateStatus(
            @PathVariable long id,
            @Valid @RequestBody StatusUpdate request){
        BuildingResponseDTO result = buildingService.updateStatus(id, request.getStatus());
        return ResponseEntity.ok(ApiResponse.success(result, "Cập nhật tòa nhà thành công ", HttpStatus.OK));
   }
   @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<BuildingResponseDTO>>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Short status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        PageResponse<BuildingResponseDTO> result = buildingService.search(name, status, page, size);
        return ResponseEntity.ok(ApiResponse.success(result, "Tìm kiếm thành công", HttpStatus.OK));
   }
}
