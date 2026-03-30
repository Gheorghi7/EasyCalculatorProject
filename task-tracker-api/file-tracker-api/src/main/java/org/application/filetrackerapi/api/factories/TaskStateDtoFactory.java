package org.application.filetrackerapi.api.factories;


import org.application.filetrackerapi.api.dto.ProjectDto;
import org.application.filetrackerapi.api.dto.TaskStateDto;
import org.application.filetrackerapi.store.entities.ProjectEntity;
import org.application.filetrackerapi.store.entities.TaskStateEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskStateDtoFactory {

     public TaskStateDto createProjectDto(TaskStateEntity projectEntity) {
         return TaskStateDto.builder()
                 .id(projectEntity.getId())
                 .name(projectEntity.getName())
                 .createdAt(projectEntity.getCreatedAt())
                 .ordinal(projectEntity.getOrdinal())
                 .build();

     }
}
