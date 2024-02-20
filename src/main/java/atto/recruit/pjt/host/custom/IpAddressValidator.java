package atto.recruit.pjt.host.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IpAddressValidator implements ConstraintValidator<ValidIpAddress, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) {
			return false; // null이나 빈 문자열을 유효한 값으로 간주하려면 true를 반환
		}

		// IP 주소 유효성 검사 로직
		return value.matches("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$"); // 간단한 IPv4 유효성 검사 예
	}
}
