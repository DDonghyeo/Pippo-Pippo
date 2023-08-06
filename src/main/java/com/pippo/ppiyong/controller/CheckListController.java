package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.dto.CheckListDto;
import com.pippo.ppiyong.service.CheckListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/checklist")
public class CheckListController {

    @Autowired
    private CheckListService checkListService;


    //체크리스트 조회
    @GetMapping("")
    public ResponseEntity<?> getCheckLists() {
        return ResponseEntity.ok(checkListService.getCheckList());
    }

    //체크리스트 생성
    @PostMapping("")
    public ResponseEntity<?> createCheckList(@RequestBody CheckListDto.Request req) {
        return ResponseEntity.ok(checkListService.createCheckList(req));
    }

    //체크리스트 삭제
    @DeleteMapping("/{checkListId}")
    public ResponseEntity<?> deleteCheckList(@PathVariable Long checkListId) {
        return ResponseEntity.ok(checkListService.deleteCheckList(checkListId));
    }



}
