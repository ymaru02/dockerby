package com.ssafy.dockerby.core.docker.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerConfig {

  // 프레임워크 혹은 라이브러리
  // Ex) SpringBoot, Vue, React, Django, MySQL...
  private FrameworkType framework;
  
  // 프레임워크/라이브러리 프로젝트 이름 or 해당 프로세스 별칭
  private String name;

  // 빌드할 언어 버전
  // Docker hub official image tag
  private String version;

  // Option : Spring Boot의 경우 Gradle, Maven
  private String type;

  // Build할 프로젝트 디렉토리 위치
  @Builder.Default
  private String projectDirectory = ".";

  // Build path (Option) : 사용자가 임의로 Build output directory path를 변경한 경우
  private String buildPath;

  // Build 속성 들 (port, volume, env, buildPath...)
  private Map<String, List<String>> properties;

  public enum FrameworkType {
    SpringBoot, Vue, React, Django, MySQL
  }

  public static class ContainerConfigBuilder {
    public ContainerConfigBuilder properties(String key, String values) {
      if(this.properties == null)
        this.properties = new LinkedHashMap<>();
      if(!this.properties.containsKey(key)) {
        ArrayList<String> strings = new ArrayList<>();
        this.properties.put(key,strings);
      }
      List<String> list = this.properties.get(key);
      list.add(values);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContainerConfig that = (ContainerConfig) o;
    return framework == that.framework && version.equals(that.version) && Objects.equals(
        type, that.type) && Objects.equals(properties, that.properties) && projectDirectory.equals(that.projectDirectory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(framework, version, type, properties, projectDirectory);
  }
}