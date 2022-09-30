package com.ndovuheights.ndovuheights.controller;

import com.ndovuheights.ndovuheights.DTO.SingleApartmentDto;
import com.ndovuheights.ndovuheights.Responses.ResponseHandler;
import com.ndovuheights.ndovuheights.Service.apartment.ApartmentDTOservices;
import com.ndovuheights.ndovuheights.Service.apartment.ApartmentServices;
import com.ndovuheights.ndovuheights.model.Apartment;
import com.ndovuheights.ndovuheights.model.House;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5500", maxAge = 3600)
@AllArgsConstructor
@RequestMapping("/api/apartment")
public class ApartmentRestController {

    private final ApartmentServices apartmentServices;
    private final ApartmentDTOservices apartmentDTOservices;


    @GetMapping("/{apartmentNumber}")
    public ResponseEntity<?> fetchOneApartment(@PathVariable UUID apartmentNumber) {

        List<SingleApartmentDto> apartment = apartmentDTOservices.getApartment(apartmentNumber);
        if (apartment.isEmpty()) {
            return ResponseHandler.generateResponse("Not Found", HttpStatus.NOT_FOUND, null);
        }
        return ResponseHandler.generateResponse("Successfully retrieved Data!", HttpStatus.OK, apartment);
    }

    @GetMapping("/featured")
    public ResponseEntity<?> featuredApartments() {
        try {
            // filter to true to indicate available rooms
            List<Apartment> apartments = apartmentServices.fetchAllApartments().stream()
                    .filter(Apartment::isFeatured).collect(Collectors.toList());
            return ResponseHandler.generateResponse("featured Apartments", HttpStatus.OK, apartments);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }


    @GetMapping("/")
    public ResponseEntity<?> fetchAllApartments() {
        try {
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, apartmentDTOservices.getAllApartments());
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<?> saveApartment(@RequestPart("apartment") @Validated Apartment apartment,
                                           @RequestPart(name = "imagefile", required = false) MultipartFile[] files) {
        try {
            House house = new House();
            Apartment saveApartment = apartmentServices.saveApartment(apartment, files);
            return ResponseHandler.generateResponse("Successfully added data", HttpStatus.CREATED, saveApartment);

        } catch (IOException e) {

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{apartmentNumber}")
    public ResponseEntity<?> deleteRoom(@PathVariable UUID apartmentNumber) {
        try {
            apartmentServices.deleteApartment(apartmentNumber);
            return ResponseHandler.generateResponse("Deleted", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping(value = "/{apartmentNumber}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRoom(@PathVariable UUID apartmentNumber, @Validated @RequestBody Apartment apartment, MultipartFile[] multipartFile) {
        try {

            Apartment savedApartment = apartmentServices.updateApartment(apartmentNumber, apartment, multipartFile);

            return ResponseHandler.generateResponse("Successfully updated!", HttpStatus.OK, savedApartment);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
