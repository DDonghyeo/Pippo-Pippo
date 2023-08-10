package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.Shelter;
import com.pippo.ppiyong.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShelterServiceImpl implements ShelterService {

    @Autowired
    ShelterRepository shelterRepository;

    @Override
    public List<Shelter> findShelters(Double latitudeStart, Double latitudeEnd, Double longitudeStart, Double longitudeEnd) {
        try {
            return shelterRepository.findByLatitudeBetweenAndLongitudeBetween(latitudeStart, latitudeEnd, longitudeStart, longitudeEnd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
