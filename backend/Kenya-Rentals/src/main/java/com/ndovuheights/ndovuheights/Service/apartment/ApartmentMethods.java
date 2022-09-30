package com.ndovuheights.ndovuheights.Service.apartment;

import com.ndovuheights.ndovuheights.model.Apartment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApartmentMethods {
    Apartment saveApartment(Apartment apartment, MultipartFile[] multipartFile) throws IOException;
    List<Apartment> fetchAllApartments();
    Optional<Apartment> fetchOneApartment(UUID apartmentNumber);
    void deleteApartment(UUID apartmentNumber);
    List<Apartment>availableHouses();

    Apartment updateApartment(UUID apartmentNumber, Apartment apartment, MultipartFile[] multipartFile);

}
