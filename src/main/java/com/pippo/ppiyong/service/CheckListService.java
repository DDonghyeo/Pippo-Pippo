package com.pippo.ppiyong.service;

import com.pippo.ppiyong.dto.CheckListDto;

import java.util.List;

public interface CheckListService {
    //체크리스트 조회
    List<CheckListDto.Response> getCheckList();

    List<CheckListDto.Response> createCheckList(CheckListDto.Request request);

    //체크리스트 삭제
    List<CheckListDto.Response> deleteCheckList(Long checkListId);

    //체크리스트 수정
    List<CheckListDto.Response> updateCheckList(Long checkListId, CheckListDto.Request request);
}
