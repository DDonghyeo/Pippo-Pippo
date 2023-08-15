package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.dto.CheckListDto;

import java.util.List;

public interface CheckListService {


    //체크리스트 조회
    List<CheckListDto.Response> getCheckList(String email);

    //체크리스트 생성
    List<CheckListDto.Response> createCheckList(CheckListDto.Request request, User user);


    //체크리스트 삭제
    List<CheckListDto.Response> deleteCheckList(Long checkListId, String email);


    //체크리스트 수정
    List<CheckListDto.Response> updateCheckList(Long checkListId, CheckListDto.Request request, String email);


    //작업 삭제
    List<CheckListDto.Response> deleteTask(Long taskId, String email);
}
