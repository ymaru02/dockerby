package com.ssafy.dockerby.service.project;

import com.ssafy.dockerby.common.exception.UserDefindedException;
import com.ssafy.dockerby.dto.project.ProjectRequestDto;
import com.ssafy.dockerby.dto.project.ProjectResponseDto;
import com.ssafy.dockerby.dto.project.StateRequestDto;
import com.ssafy.dockerby.dto.project.StateResponseDto;
import com.ssafy.dockerby.entity.project.ProjectState;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.io.IOException;

public interface ProjectService {
  ProjectResponseDto createProject(ProjectRequestDto projectRequestDto) throws IOException, UserDefindedException;

  ProjectState build(ProjectRequestDto projectRequestDto) throws ChangeSetPersister.NotFoundException;

  StateResponseDto checkState(StateRequestDto stateRequestDto) throws ChangeSetPersister.NotFoundException;
}
