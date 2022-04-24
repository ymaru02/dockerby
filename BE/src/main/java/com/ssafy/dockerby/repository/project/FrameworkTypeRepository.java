package com.ssafy.dockerby.repository.project;

import com.ssafy.dockerby.entity.project.frameworks.FrameworkType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrameworkTypeRepository extends JpaRepository<FrameworkType,Long> {
}
