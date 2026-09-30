package org.example.finalmd3.repository;

import org.example.finalmd3.entity.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    boolean existsBuildingByBuildingName(String buildingName);
    boolean existsBuildingByBuildingNameAndIdNot(String buildingName, Long id);

    @Query("""
        SELECT b FROM Building b 
        WHERE (:name IS NULL OR LOWER(b.buildingName) LIKE LOWER(CONCAT('%', :name, '%')))
               AND (:status IS NULL OR b.status = :status)
         """)
    Page<Building> search(@Param("name")String name, @Param("status") Short status,  Pageable pageable);
}
