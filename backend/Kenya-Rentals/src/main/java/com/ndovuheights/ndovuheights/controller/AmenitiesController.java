package com.ndovuheights.ndovuheights.controller;

import com.ndovuheights.ndovuheights.Responses.ResponseHandler;
import com.ndovuheights.ndovuheights.Service.amenities.AmenitiesMethodsService;
import com.ndovuheights.ndovuheights.model.Amenities;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
@AllArgsConstructor
@RequestMapping("/api/amenities")
public class AmenitiesController {

    private final AmenitiesMethodsService amenitiesMethodsService;


    @GetMapping("/{id}")
    public ResponseEntity<?> fetchOneAmenity(@PathVariable UUID id) {

        Optional<Amenities> amenities = amenitiesMethodsService.fetchOneAmenity(id);
        if (amenities.isEmpty()) {
            return ResponseHandler.generateResponse("Not Found", HttpStatus.NOT_FOUND, "");
        }
        return ResponseHandler.generateResponse("Successfully retrieved Data!", HttpStatus.OK, amenities.get());
    }



    @GetMapping("/")
    public ResponseEntity<?> fetchAllAmenities() {
        try {
            List<Amenities> amenities = amenitiesMethodsService.fetchAllAmenities();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, amenities);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, "");
        }
    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<?> saveAmenity(@RequestBody @Validated Amenities amenities) {
        try {
            amenities = amenitiesMethodsService.saveAmenities(amenities);

            return ResponseHandler.generateResponse("Successfully added data", HttpStatus.CREATED, amenities);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, "");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAmenity(@PathVariable UUID id) {
        try {
           amenitiesMethodsService.deleteAmenity(id);
            return ResponseHandler.generateResponse("Deleted", HttpStatus.OK, new ArrayList<>());
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, "");
        }
    }

}
