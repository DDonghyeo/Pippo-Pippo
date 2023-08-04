package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.service.CheckListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CheckListController {

    @Autowired
    private CheckListService checkListService;


    //체크리스트 조회
    public ResponseEntity<?> getCheckLists() {
        return ResponseEntity.ok(checkListService.getCheckList());
    }


}
