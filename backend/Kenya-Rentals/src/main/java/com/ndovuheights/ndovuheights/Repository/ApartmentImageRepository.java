package com.ndovuheights.ndovuheights.Repository;

import com.ndovuheights.ndovuheights.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApartmentImageRepository extends JpaRepository<Images, UUID> {
}
