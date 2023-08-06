package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.CheckList;
import com.pippo.ppiyong.domain.Task;
import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.dto.CheckListDto;
import com.pippo.ppiyong.exception.CustomException;
import com.pippo.ppiyong.exception.ErrorCode;
import com.pippo.ppiyong.repository.CheckListRepository;
import com.pippo.ppiyong.repository.TaskRepository;
import com.pippo.ppiyong.repository.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    //체크리스트 조회
    @Override
    public List<CheckListDto.Response> getCheckList(){
        //TODO : 유저 정보 가져오기
        Long user_id = 1L;
s
        return getCheckListResponse(checkListRepository.findAllByUser_Id(user_id));
    }

    //체크리스트 생성
    @Override
    public List<CheckListDto.Response> createCheckList(CheckListDto.Request request) {
        try {
            CheckList checkList = checkListRepository.save(CheckList.builder()
                    .user(getUser())
                    .title(request.getTitle()).build());

            List<Task> tasks = request.getTask().stream().map(taskRequest -> taskRequest.toEntity(checkList)).toList();
            taskRepository.saveAll(tasks);
            return getCheckList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ErrorCode.SERVER_ERROR);
        }

    }

    //체크리스트 삭제
    @Override
    public List<CheckListDto.Response> deleteCheckList(Long checkListId) {
        try {
            checkListRepository.deleteById(checkListId);
            taskRepository.deleteAllByCheckList_Id(checkListId);
            return getCheckList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ErrorCode.SERVER_ERROR);
        }

    }


    //체크리스트 수정
    @Override
    public List<CheckListDto.Response> updateCheckList(Long checkListId, CheckListDto.Request request) {
        CheckList checkList = checkListRepository.findById(checkListId).orElseThrow(
                () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)
        );

        if (!checkList.getTitle().equals(request.getTitle())) {
           checkList.updateTitle(request.getTitle());
            checkListRepository.save(checkList);
        }

        List<CheckListDto.TaskRequest> task_req = request.getTask();

        List<Task> tasks = taskRepository.findAllByCheckList_Id(checkListId).orElseThrow(
                () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)
        );

        tasks.stream().map(task -> {
            String content = task_req.get(tasks.indexOf(task)).getContent();
            if (!task.getContent().equals(content)) {
                task.updateContent(content);
            }
            return task;
        }).collect(Collectors.toList());

        taskRepository.saveAll(tasks);

        return getCheckList();

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

    private User getUser() {
        return userRepository.findById(1L).orElseThrow(
                () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)
        );
    }
}
