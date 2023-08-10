package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.Shelter;
import com.pippo.ppiyong.type.Category;
import lombok.Getter;

@Getter
public class ShelterResponseDto {

    private Category category;

    private Double latitude;

    private Double longitude;

    private String name;

    private String address;

    public ShelterResponseDto(Shelter shelter) {
        this.category = shelter.getCategory();
        this.latitude = shelter.getLatitude();
        this.longitude = shelter.getLongitude();
        this.name = shelter.getName();
        this.address = shelter.getAddress();
    }
}
