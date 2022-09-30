package com.ndovuheights.ndovuheights.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Apartment_Amenities")
@Table(name = "apartment_amenities")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ApartmentAmenities implements Serializable {
    @Serial
    private static final long serialVersionUID = 2620244833041938302L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ApartmentAmenities that = (ApartmentAmenities) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}