package atto.recruit.pjt.host.repository;

import atto.recruit.pjt.host.domain.entity.HostStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostStatusHistoryRepository extends JpaRepository<HostStatusHistory, Long>, HostStatusHistoryRepositoryCustom {

}
