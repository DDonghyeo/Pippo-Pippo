package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.Shelter;

import java.util.List;

public interface ShelterService {

    List<Shelter> findShelters(Double latitudeStart, Double latitudeEnd, Double longitudeStart, Double longitudeEnd);
}
