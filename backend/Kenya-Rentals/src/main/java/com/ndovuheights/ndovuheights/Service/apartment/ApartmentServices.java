package com.ndovuheights.ndovuheights.Service.apartment;

import com.ndovuheights.ndovuheights.Repository.ApartmentRepository;
import com.ndovuheights.ndovuheights.model.Apartment;
import com.ndovuheights.ndovuheights.model.Images;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@AllArgsConstructor
@Service
public class ApartmentServices implements ApartmentMethods {

    private final ApartmentRepository apartmentRepository;


    @Override
    public Apartment saveApartment(Apartment apartment, MultipartFile[] multipartFile) throws IOException {
        if (multipartFile != null){
            List<Images> images = uploadImage(multipartFile);
            apartment.setImages(images);
        }
        else{
            List<Images> empty= new ArrayList<>();
            apartment.setImages(empty);
        }
        return apartmentRepository.save(apartment);
    }
    public List<Images> uploadImage(MultipartFile[] files) throws IOException {
        List<Images> images = new ArrayList<>();
        for(MultipartFile file: files){
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            Images apartmentImages = new Images();
            apartmentImages.setImageName(fileName);
            apartmentImages.setImageData(file.getBytes());
            apartmentImages.setImageType(file.getContentType());
            images.add(apartmentImages);
        }
        return images;
    }

    @Override
    public List<Apartment> fetchAllApartments() {
        return apartmentRepository.findAll();
    }

    @Override
    public Optional<Apartment> fetchOneApartment(UUID apartmentNumber) {
        return Optional.ofNullable(apartmentRepository.findById(apartmentNumber).orElseThrow(() -> new RuntimeException("" +
                "Apartment with " + apartmentNumber + " not found")));
    }

    @Override
    public void deleteApartment(UUID apartmentNumber) {
        apartmentRepository.deleteById(apartmentNumber);
    }

    @Override
    public List<Apartment> availableHouses() {
        return null;
    }

    @Override
    public Apartment updateApartment(UUID apartmentNumber, Apartment apartment, MultipartFile[] multipartFile) {
        return apartmentRepository.findById(apartmentNumber).map(apartmentSave -> {

            apartmentSave.setPhoneNumber(apartment.getPhoneNumber());
            apartmentSave.setEmail(apartment.getEmail());
            apartmentSave.setName(apartment.getName());
            apartmentSave.setLatitudeCoordinates(apartment.getLatitudeCoordinates());
            apartmentSave.setLongitudeCoordinates(apartment.getLongitudeCoordinates());
            apartmentSave.setHouseTypes(apartment.getHouseTypes());

            try {
                return saveApartment(apartmentSave, multipartFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).orElseGet(() -> {
            apartment.setId(apartmentNumber);
            try {
                return saveApartment(apartment, multipartFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //TODO PREFILL LIST IN THE FORM FOR UPDATE
        // VALIDATE PHONE_NUMBERS, EMAIL USING REGEX
    }
}
