package com.ssafy.dockerby.dto.project;

import com.sun.istack.NotNull;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BuildConfigDto {

  @NotNull
  private Long frameworkId;

  @NotNull
  private String name;

  @NotNull
  private String version;

  // Optional
  private String type;

  @NotNull
  private String projectDirectory;

  private String buildPath;

  private List<ConfigProperty> properties;

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class ConfigProperty {
    private String property;
    private String first;
    private String second;
  }
}
