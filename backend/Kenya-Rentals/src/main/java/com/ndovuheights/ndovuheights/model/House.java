package com.ndovuheights.ndovuheights.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "House_Types")
@Table(name = "house_types")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class House implements Serializable {
    @Serial
    private static final long serialVersionUID = 1859546844560952501L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private RoomType type;


    @Column(columnDefinition = "boolean default false")
    private boolean available;
    private String price;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        House that = (House) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}