package com.ndovuheights.ndovuheights.Repository;

import com.ndovuheights.ndovuheights.model.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AmenitiesRepository extends JpaRepository<Amenities, UUID> {
}
