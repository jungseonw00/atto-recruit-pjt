package atto.recruit.pjt.common.log.repository;

import atto.recruit.pjt.common.log.application.dto.response.SystemLogResponse;
import java.util.List;

public interface SystemLogRepositoryCustom {
	List<SystemLogResponse> findAllSystemLog();
}
