package atto.recruit.pjt;

import static atto.recruit.pjt.host.domain.entity.AliveStatus.ALIVE;
import static atto.recruit.pjt.host.domain.entity.AliveStatus.NOT_ALIVE;
import static java.time.LocalDateTime.now;

import atto.recruit.pjt.host.domain.entity.Host;
import atto.recruit.pjt.host.domain.entity.HostStatusHistory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitDB {

    private final InitService initService;

    @PostConstruct
    @Profile("DEV")
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
        initService.dbInit3();
        initService.dbInit4();
        initService.dbInit5();
        initService.dbInit6();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            log.info("Init1 = {}", this.getClass());
            Host entity = Host.builder()
                    .name("홍길동")
                    .ip("192.168.0.1")
                    .build();
            em.persist(HostStatusHistory.builder()
                .aliveStatus(ALIVE)
                .aliveTime(now())
                .host(entity)
                .build());
            em.persist(HostStatusHistory.builder()
                .aliveStatus(NOT_ALIVE)
                .aliveTime(now())
                .host(entity)
                .build());
        }

        public void dbInit2() {
            log.info("Init2 = {}", this.getClass());
            Host entity = Host.builder()
                    .name("마석도")
                    .ip("192.168.0.2")
                    .build();
            em.persist(HostStatusHistory.builder()
                .aliveStatus(ALIVE)
                .aliveTime(now())
                .host(entity)
                .build());
            em.persist(HostStatusHistory.builder()
                .aliveStatus(NOT_ALIVE)
                .aliveTime(now())
                .host(entity)
                .build());
        }

        public void dbInit3() {
            log.info("Init3 = {}", this.getClass());
            Host entity = Host.builder()
                    .name("허석웅")
                    .ip("192.168.0.3")
                    .build();
            em.persist(HostStatusHistory.builder()
                .aliveStatus(ALIVE)
                .aliveTime(now())
                .host(entity)
                .build());
            em.persist(HostStatusHistory.builder()
                .aliveStatus(NOT_ALIVE)
                .aliveTime(now())
                .host(entity)
                .build());
        }

        public void dbInit4() {
            log.info("Init4 = {}", this.getClass());
            Host entity = Host.builder()
                    .name("엄규학")
                    .ip("172.16.254.1")
                    .build();
            em.persist(HostStatusHistory.builder()
                .aliveStatus(ALIVE)
                .aliveTime(now())
                .host(entity)
                .build());
            em.persist(HostStatusHistory.builder()
                .aliveStatus(NOT_ALIVE)
                .aliveTime(now())
                .host(entity)
                .build());
        }

        public void dbInit5() {
            log.info("Init5 = {}", this.getClass());
            Host entity = Host.builder()
                    .name("남제충")
                    .ip("172.16.254.2")
                    .build();

            em.persist(HostStatusHistory.builder()
                .aliveStatus(ALIVE)
                .aliveTime(now())
                .host(entity)
                .build());

            em.persist(HostStatusHistory.builder()
                .aliveStatus(NOT_ALIVE)
                .aliveTime(now())
                .host(entity)
                .build());
        }

        public void dbInit6() {
            log.info("Init6 = {}", this.getClass());
            Host entity = Host.builder()
                .name("김진태")
                .ip("182.16.254.2")
                .build();

            em.persist(HostStatusHistory.builder()
                .aliveStatus(NOT_ALIVE)
                .aliveTime(now())
                .host(entity)
                .build());

            em.persist(HostStatusHistory.builder()
                .aliveStatus(NOT_ALIVE)
                .aliveTime(now().minusDays(100L))
                .host(entity)
                .build());
        }
    }
}