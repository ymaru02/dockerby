package com.ssafy.dockerby.core.docker;

import com.ssafy.dockerby.core.docker.dto.ContainerConfig;
import com.ssafy.dockerby.core.docker.dto.ContainerConfig.FrameworkType;
import com.ssafy.dockerby.core.util.CommandInterpreter;
import com.ssafy.dockerby.util.FileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DockerBuilder {

  private final String rootDir;
  private final DockerfileMaker dockerfileMaker;
  private final DockerCommandMaker dockerCommandMaker;

  public DockerBuilder(String projectName, ContainerConfig config) {
    StringBuilder sb = new StringBuilder();
    sb.append("/home/").append(projectName).append("/");
    this.rootDir = sb.toString();
    this.dockerfileMaker = new DockerfileMaker(this.rootDir);
    this.dockerCommandMaker = new DockerCommandMaker(projectName, this.rootDir);
  }

  public void execDockerfile(ContainerConfig config) throws IOException {
    dockerfileMaker.make(config);
  }

  private String build(ContainerConfig config) {
    return dockerCommandMaker.build(config);
  }

  private String run(ContainerConfig config) {
    return dockerCommandMaker.run(config);
  }

  private String network() {
    return dockerCommandMaker.bridge();
  }

  private String removeContainer(ContainerConfig config) {
    return dockerCommandMaker.removeContainer(config);
  }

  public List<String> execRun(List<ContainerConfig> configs) {
    List<String> commands = new ArrayList<>();
    commands.add(network());

    configs.forEach(config -> commands.add(run(config)));

    return commands;
  }

  public List<String> execBuildAndRun(List<ContainerConfig> configs) {
    List<String> commands = new ArrayList<>();
    commands.add(network());

    List<String> buildCommands = new ArrayList<>();
    List<String> runCommands = new ArrayList<>();
    configs.forEach(config -> {
      buildCommands.add(build(config));
      runCommands.add(run(config));
    });

    commands.addAll(buildCommands);
    commands.addAll(runCommands);

    return commands;
  }
}
