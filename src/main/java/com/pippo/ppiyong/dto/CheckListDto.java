package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.CheckList;
import com.pippo.ppiyong.domain.Task;
import com.pippo.ppiyong.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Check;

import java.util.List;
import java.util.stream.Collectors;


public class CheckListDto {


    @Builder
    @Getter
    public static class Request {

        String title;

        List<TaskRequest> task;


    }

    public static class TaskRequest{
        String content;

        public Task toEntity(CheckList checkList){
            return Task.builder()
                    .content(content)
                    .isComplete(false)
                    .checkList(checkList)
                    .build();
        }
    }

    @Builder

    @Getter
    public static class Response {

        Long check_list_id;

        String title;

        List<TaskResponse> task;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class TaskResponse {

        Long task_id;

        String content;

        boolean isComplete;

        public TaskResponse(Task task) {
            this.task_id = task.getId();
            this.content = task.getContent();
            this.isComplete = task.isComplete();
        }
    }
}
