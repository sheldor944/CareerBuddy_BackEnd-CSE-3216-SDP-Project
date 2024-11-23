package com.example.demo.usermanagement.profileManagement.helperModels;

import com.example.demo.usermanagement.profileManagement.ProfileRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "addresses")
@Data
public class Address {
    @Id
    @GeneratedValue
    private UUID id;

    private String addressLine1;
    private String city;
    private String country;

    public Address (ProfileRequest.AddressRequest addressRequest){
        this.addressLine1 = addressRequest.getAddressLine1();
        this.city = addressRequest.getCity();
        this.country = addressRequest.getCountry();
    }
}
