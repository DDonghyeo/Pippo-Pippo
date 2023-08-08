package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.dto.CheckListDto;
import com.pippo.ppiyong.service.CheckListService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "체크리스트 조회", notes = "Parameter : X \n return : 체크리스트 전체")
    @GetMapping("")
    public ResponseEntity<?> getCheckLists() {
        return ResponseEntity.ok(checkListService.getCheckList());
    }

    //체크리스트 생성
    @ApiOperation(value = "체크리스트 생성", notes = "Parameter : 생성하려는 체크리스트 전체 \n return : 변경 후 체크리스트 전체")
    @PostMapping("")
    public ResponseEntity<?> createCheckList(@RequestBody CheckListDto.Request req) {
        return ResponseEntity.ok(checkListService.createCheckList(req));
    }

    //체크리스트 삭제
    @ApiOperation(value = "체크리스트 삭제", notes = "Parameter : (PathVariable) 삭제하려는 체크리스트 id \n return : 변경 후 체크리스트 전체")
    @DeleteMapping("/{checkListId}")
    public ResponseEntity<?> deleteCheckList(@PathVariable Long checkListId) {
        return ResponseEntity.ok(checkListService.deleteCheckList(checkListId));
    }


    //체크리스트 수정
    @ApiOperation(value = "체크리스트 수정", notes = "Parameter : (PathVariable) 수정하려는 체크리스트 id, 내용 \n return : 변경 후 체크리스트 전체")
    @PutMapping("/{checkListId}")
    public ResponseEntity<?> updateCheckList(@PathVariable Long checkListId, @RequestBody CheckListDto.Request req) {
        return ResponseEntity.ok(checkListService.updateCheckList(checkListId, req));
    }

    //작업 삭제
    @ApiOperation(value = "작업 삭제", notes = "Parameter : (PathVariable) 삭제하려는 작업 id \n return : 변경 후 체크리스트 전체")
    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(checkListService.deleteTask(taskId));
    }

}