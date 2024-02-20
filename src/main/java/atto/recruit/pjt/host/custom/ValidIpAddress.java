package atto.recruit.pjt.host.custom;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = IpAddressValidator.class)
public @interface ValidIpAddress {

	String message() default "유효하지 않은 IP 주소입니다."; // 기본 에러 메시지

	Class<?>[] groups() default {}; // 그룹화 기능에 사용

	Class<? extends Payload>[] payload() default {}; // 메타데이터 제공에 사용
}
