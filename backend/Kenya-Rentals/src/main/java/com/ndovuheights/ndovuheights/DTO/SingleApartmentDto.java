package com.ndovuheights.ndovuheights.DTO;

import com.ndovuheights.ndovuheights.model.Apartment;
import com.ndovuheights.ndovuheights.model.House;
import com.ndovuheights.ndovuheights.model.Images;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * A DTO for the {@link Apartment} entity
 */
public record SingleApartmentDto(UUID id, String name, double latitudeCoordinates, double longitudeCoordinates,
                                 List<House> houseTypes, String phoneNumber, String email, List<com.ndovuheights.ndovuheights.model.ApartmentAmenities> amenities,
                                 List<Images> images, boolean featured,
                                 String locationTown) implements Serializable {
}