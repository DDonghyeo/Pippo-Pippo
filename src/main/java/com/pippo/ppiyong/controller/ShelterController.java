package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.domain.Shelter;
import com.pippo.ppiyong.dto.ShelterResponseDto;
import com.pippo.ppiyong.service.ShelterServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ShelterController {

    @Autowired
    ShelterServiceImpl shelterService;

    @Operation(summary = "대피소 조회", description = "query string:\nlatitude_start(위도 시작) latitude_end(위도 끝)\nlongitude_start(경도 시작) longitude_end(경도 끝)")
    @GetMapping("/shelter")
    public ResponseEntity<?> getShelters(@RequestParam(value = "latitude_start") Double latitudeStart,
                                         @RequestParam(value = "latitude_end") Double latitudeEnd,
                                         @RequestParam(value = "longitude_start") Double longitudeStart,
                                         @RequestParam(value = "longitude_end") Double longitudeEnd) {
        try {
            List<Shelter> shelterList = shelterService.findShelters(latitudeStart, latitudeEnd, longitudeStart, longitudeEnd);
            return ResponseEntity.ok().body(shelterList.stream().map(ShelterResponseDto::new).collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
