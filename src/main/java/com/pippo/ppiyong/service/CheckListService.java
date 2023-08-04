package com.pippo.ppiyong.service;

import com.pippo.ppiyong.dto.CheckListDto;

import java.util.List;

public interface CheckListService {
    //체크리스트 조회
    List<CheckListDto.Response> getCheckList();
}
