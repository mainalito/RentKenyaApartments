package com.ndovuheights.ndovuheights.Repository;

import com.ndovuheights.ndovuheights.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<Apartment, UUID> {

}
