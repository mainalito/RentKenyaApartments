package com.ndovuheights.ndovuheights.Service.apartment;

import com.ndovuheights.ndovuheights.DTO.ApartmentDto;
import com.ndovuheights.ndovuheights.DTO.SingleApartmentDto;
import com.ndovuheights.ndovuheights.model.Apartment;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApartmentDTOservices {
    private final ApartmentServices services;

    public List<ApartmentDto> getAllApartments() {
        return services.fetchAllApartments()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }
    public List<SingleApartmentDto> getApartment(UUID uuid){
        return services.fetchOneApartment(uuid).stream().map(
                this::convertSingleApartmentDto
        ).collect(Collectors.toList());
    }
    public SingleApartmentDto convertSingleApartmentDto(Apartment savedApartment){
        return new SingleApartmentDto(
                savedApartment.getId(),
                savedApartment.getName(),
                savedApartment.getLatitudeCoordinates(),
                savedApartment.getLongitudeCoordinates(),
                savedApartment.getHouseTypes(),
                savedApartment.getPhoneNumber(),
                savedApartment.getEmail(),
                savedApartment.getAmenities(),
                savedApartment.getImages(),
                savedApartment.isFeatured(),
                savedApartment.getLocationTown()
        );
    }

    public ApartmentDto convertEntityToDTO(@NotNull Apartment savedApartment) {

        return new ApartmentDto(
                savedApartment.getId(),
                savedApartment.getName(),
                savedApartment.getLatitudeCoordinates(),
                savedApartment.getLongitudeCoordinates(),
                savedApartment.isFeatured(),
                savedApartment.getLocationTown(),
                savedApartment.getImages()

        );
    }
}
