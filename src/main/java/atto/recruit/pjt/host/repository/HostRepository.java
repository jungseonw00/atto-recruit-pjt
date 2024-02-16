package atto.recruit.pjt.host.repository;

import atto.recruit.pjt.host.entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long>, HostRepositoryCustom {
	Long deleteHostByIpAndName(String host, String ip);
}
