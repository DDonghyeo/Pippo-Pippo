package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class CheckListDto {

    @Builder
    @Getter
    public static class Response{

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
