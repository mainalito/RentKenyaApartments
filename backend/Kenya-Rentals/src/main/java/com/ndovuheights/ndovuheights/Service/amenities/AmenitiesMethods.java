package com.ndovuheights.ndovuheights.Service.amenities;

import com.ndovuheights.ndovuheights.model.Amenities;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AmenitiesMethods {
    Amenities saveAmenities(Amenities amenities);
    List<Amenities> fetchAllAmenities();
    Optional<Amenities> fetchOneAmenity(UUID id);
    void deleteAmenity(UUID id);
}
