package org.application.filetrackerapi.api.controllers;


import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.application.filetrackerapi.api.dto.AskDto;
import org.application.filetrackerapi.api.dto.ProjectDto;
import org.application.filetrackerapi.api.exceptions.BadRequestException;
import org.application.filetrackerapi.api.exceptions.NotFoundException;
import org.application.filetrackerapi.api.factories.ProjectDtoFactory;
import org.application.filetrackerapi.store.entities.ProjectEntity;
import org.application.filetrackerapi.store.repository.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class ProjectController {


    ProjectRepository projectRepository;
    ProjectDtoFactory projectDtoFactory;

    public static final String EDIT_PROJECT = "/api/projects/{project_id}";
    public static final String FETCH_PROJECT = "/api/projects";
    public static final String CREATE_PROJECT = "/api/projects";
    public static final String DELETE_PROJECT = "/api/projects/{project_id}";


    @PostMapping(CREATE_PROJECT)
    public ProjectDto createProject(@RequestParam String name) {
        if (name.trim().isEmpty()) {
            throw new BadRequestException("Name cannot be empty");
        }

        projectRepository
                .findByName(name)
                .ifPresent((project) -> {
                    throw new BadRequestException("Project %s already exists".formatted(name));

                });
        ProjectEntity project = projectRepository.saveAndFlush(
                ProjectEntity.builder()
                        .name(name)
                        .build()
        );
        return projectDtoFactory.createProjectDto(project);
    }


    @GetMapping(FETCH_PROJECT)
    public List<ProjectDto> fetchProject(@RequestParam(value = "prefix_name", required = false)
                                         Optional<String> optionalPrefixName) {

        optionalPrefixName = optionalPrefixName.filter(prefixName -> !prefixName.
                trim().
                isEmpty());

        Stream<ProjectEntity> projectStream = optionalPrefixName
                .map(projectRepository::streamAllByNameStartsWithIgnoreCase)
                .orElseGet(() -> projectRepository.streamAll());

        if (optionalPrefixName.isPresent()) {
            projectRepository.streamAllByNameStartsWithIgnoreCase(optionalPrefixName.get());
        } else {
            projectStream = projectRepository.streamAll();
        }


        return projectStream
                .map(projectDtoFactory::createProjectDto)
                .collect(Collectors.toList());
    }


    @PatchMapping(EDIT_PROJECT)
    public ProjectDto editProject(@PathVariable("project_id") Long projectId,
                                  @RequestParam String name) {

        if (name.trim().isEmpty()) {
            throw new BadRequestException("Name cannot be empty");
        }

        ProjectEntity project = projectRepository
                .findById(projectId)
                .orElseThrow(() -> new NotFoundException("Project %s not found".formatted(projectId)));

        projectRepository
                .findByName(name)
                .filter(anotherProject -> !Objects.equals(projectId, anotherProject.getId()))
                .ifPresent((anotherProject) -> {
                    throw new BadRequestException("Project %s already exists".formatted(name));

                });

        project.setName(name);
        project = projectRepository.saveAndFlush(project);
        return projectDtoFactory.createProjectDto(project);
    }


    @DeleteMapping(DELETE_PROJECT)
    public AskDto deleteProject(@PathVariable("project_id") Long projectId) {
        projectRepository
                .findById(projectId)
                .orElseThrow(() -> new NotFoundException("Project %s not found".formatted(projectId)));
        projectRepository.deleteById(projectId);
        return AskDto.makeDefault(true);

    }
}
