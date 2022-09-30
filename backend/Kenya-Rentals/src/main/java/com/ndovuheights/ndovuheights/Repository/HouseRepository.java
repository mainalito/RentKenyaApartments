package com.ndovuheights.ndovuheights.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ndovuheights.ndovuheights.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, UUID> {


}
