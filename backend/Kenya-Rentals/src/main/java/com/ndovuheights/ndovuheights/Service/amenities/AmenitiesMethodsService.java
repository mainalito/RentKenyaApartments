package com.ndovuheights.ndovuheights.Service.amenities;

import com.ndovuheights.ndovuheights.Repository.AmenitiesRepository;
import com.ndovuheights.ndovuheights.model.Amenities;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AmenitiesMethodsService implements AmenitiesMethods {

    private final AmenitiesRepository amenitiesRepository;
    @Override
    public Amenities saveAmenities(Amenities amenities ) {
        return amenitiesRepository.save(amenities);
    }

    @Override
    public List<Amenities> fetchAllAmenities() {
        return amenitiesRepository.findAll();
    }

    @Override
    public Optional<Amenities> fetchOneAmenity(UUID id) {
        return Optional.ofNullable(amenitiesRepository.findById(id).orElseThrow(() -> new RuntimeException("" +
                "Apartment with " + id + " not found")));
    }

    @Override
    public void deleteAmenity(UUID id) {
    amenitiesRepository.deleteById(id);
    }
}
