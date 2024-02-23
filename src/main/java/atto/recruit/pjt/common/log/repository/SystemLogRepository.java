package atto.recruit.pjt.common.log.repository;

import atto.recruit.pjt.common.log.domain.entity.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemLogRepository extends JpaRepository<SystemLog, Long>, SystemLogRepositoryCustom {

}
