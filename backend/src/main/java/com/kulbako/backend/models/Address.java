package com.kulbako.backend.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Data @Embeddable
public class Address implements Serializable {

    @Transient private static final long serialVersionUID = 4L;
    @Id @GeneratedValue private long id;
    @NotNull private String city;
    @NotNull private String street;
    @NotNull private int house;
    private int apartment;

    public Address() {}
    //get и set методы определены аннотацией lombok

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return house == address.house &&
                city.equals(address.city) &&
                street.equals(address.street);
    }

    @Override
    public int hashCode() { return Objects.hash(city, street, house); }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Address{" +
                "id = " + id +
                ", city = " + city +
                ", street = " + street +
                ", house = " + house);
        if (apartment != 0) builder.append(", apartment = " + apartment);
        builder.append("}");
        return builder.toString();
    }
}
