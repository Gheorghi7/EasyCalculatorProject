package org.application.filetrackerapi.api.factories;


import org.application.filetrackerapi.api.dto.ProjectDto;
import org.application.filetrackerapi.api.dto.TaskDto;
import org.application.filetrackerapi.store.entities.ProjectEntity;
import org.application.filetrackerapi.store.entities.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoFactory {

    public TaskDto createProjectDto(TaskEntity projectEntity) {
        return TaskDto.builder()
                .id(projectEntity.getId())
                .name(projectEntity.getName())
                .createdAt(projectEntity.getCreatedAt())
                .description(projectEntity.getDescription())
                .build();

    }
}
