package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.CheckList;
import com.pippo.ppiyong.domain.Task;
import com.pippo.ppiyong.dto.CheckListDto;
import com.pippo.ppiyong.repository.CheckListRepository;
import com.pippo.ppiyong.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CheckListServiceImpl implements CheckListService{

    @Autowired
    CheckListRepository checkListRepository;

    @Autowired
    TaskRepository taskRepository;


    //체크리스트 조회
    @Override
    public List<CheckListDto.Response> getCheckList(){
        //TODO : 유저 정보 가져오기
        Long user_id = 1L;

        return getCheckListResponse(checkListRepository.findAllByUser_Id(user_id));
    }


    private List<CheckListDto.Response> getCheckListResponse(List<CheckList> checkList) {
        if (checkList.isEmpty()) return null;

        return checkList.stream()
                .map(origin -> CheckListDto.Response.builder()
                        .check_list_id(origin.getId())
                        .title(origin.getTitle())
                        .task(getTaskResponseList(origin.getId())).build()
                ).collect(Collectors.toList());
    }

    private List<CheckListDto.TaskResponse> getTaskResponseList(Long check_list_id) {
        Optional<List<Task>> taskList = taskRepository.findAllByCheckList_Id(check_list_id);

        return taskList.map(tasks -> tasks.stream().map(CheckListDto.TaskResponse::new).collect(Collectors.toList())).orElse(null);


    }
}
