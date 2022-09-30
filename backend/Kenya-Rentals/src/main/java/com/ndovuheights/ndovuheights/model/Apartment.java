package com.ndovuheights.ndovuheights.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "apartment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;
    private double latitudeCoordinates;
    private double longitudeCoordinates;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "apartmentHouse_fid", referencedColumnName = "id")
    @ToString.Exclude
    private List<House> houseTypes = new ArrayList<>();

    private String phoneNumber;
    private String email;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "apartment_apartment_amenitieses",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "apartment_amenitieses_id"))
    private List<ApartmentAmenities> amenities = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "apartment_images",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "images_id")
    )
    @ToString.Exclude
    private List<Images> images = new ArrayList<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdOn")
    private Date createdOn;

    @Column(columnDefinition = "boolean default false")
    private boolean featured;

    private String locationTown;


}