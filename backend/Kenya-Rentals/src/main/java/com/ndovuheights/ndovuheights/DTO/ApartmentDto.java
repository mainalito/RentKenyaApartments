package com.ndovuheights.ndovuheights.DTO;

import com.ndovuheights.ndovuheights.model.Apartment;
import com.ndovuheights.ndovuheights.model.Images;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * A DTO for the {@link Apartment} entity
 */
public record ApartmentDto(UUID id, String name, double latitudeCoordinates, double longitudeCoordinates,
                           boolean featured, String locationTown, List<Images> images) implements Serializable {
}