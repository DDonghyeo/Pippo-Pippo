package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.auth.CustomUserDetail;
import com.pippo.ppiyong.dto.CheckListDto;
import com.pippo.ppiyong.service.CheckListService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/checklist")
public class CheckListController {

    @Autowired
    private CheckListService checkListService;


    //체크리스트 조회
    @Operation(summary = "체크리스트 조회", description = "Parameter : X \n return : 체크리스트 전체")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<?> getCheckLists(@AuthenticationPrincipal CustomUserDetail customUserDetail) {
        return ResponseEntity.ok(checkListService.getCheckList(customUserDetail.getUserEmail()));
    }

    //체크리스트 생성
    @Operation(summary = "체크리스트 생성", description= "Parameter : 생성하려는 체크리스트 전체 \n return : 변경 후 체크리스트 전체")
    @PostMapping("")
    public ResponseEntity<?> createCheckList(@AuthenticationPrincipal CustomUserDetail customUserDetail, @RequestBody CheckListDto.Request req) {
        return ResponseEntity.ok(checkListService.createCheckList(req, customUserDetail.getUser()));
    }

    //체크리스트 삭제
    @Operation(summary = "체크리스트 삭제", description = "Parameter : (PathVariable) 삭제하려는 체크리스트 id \n return : 변경 후 체크리스트 전체")
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{checkListId}")
    public ResponseEntity<?> deleteCheckList(@AuthenticationPrincipal CustomUserDetail customUserDetail, @PathVariable Long checkListId) {
        return ResponseEntity.ok(checkListService.deleteCheckList(checkListId, customUserDetail.getUserEmail()));
    }


    //체크리스트 수정
    @Operation(summary = "체크리스트 수정", description = "Parameter : (PathVariable) 수정하려는 체크리스트 id, 내용 \n return : 변경 후 체크리스트 전체")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{checkListId}")
    public ResponseEntity<?> updateCheckList(@AuthenticationPrincipal CustomUserDetail customUserDetail, @PathVariable Long checkListId, @RequestBody CheckListDto.Request req) {
        return ResponseEntity.ok(checkListService.updateCheckList(checkListId, req, customUserDetail.getUserEmail()));
    }

    //작업 삭제
    @Operation(summary = "작업 삭제", description = "Parameter : (PathVariable) 삭제하려는 작업 id \n return : 변경 후 체크리스트 전체")
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/task/{taskId}")
    public ResponseEntity<?> deleteTask(@AuthenticationPrincipal CustomUserDetail customUserDetail, @PathVariable Long taskId) {
        return ResponseEntity.ok(checkListService.deleteTask(taskId, customUserDetail.getUserEmail()));
    }

}
