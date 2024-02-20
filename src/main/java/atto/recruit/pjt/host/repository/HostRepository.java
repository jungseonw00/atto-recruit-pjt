package atto.recruit.pjt.host.repository;

import atto.recruit.pjt.host.domain.entity.Host;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long>, HostRepositoryCustom {
	Long deleteHostByIpAndName(String host, String ip);
	Optional<Host> findByName(String name);
	Optional<Host> findByIp(String ip);
	long count();
}
