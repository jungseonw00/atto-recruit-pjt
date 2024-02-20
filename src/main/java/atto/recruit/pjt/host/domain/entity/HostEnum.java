package atto.recruit.pjt.host.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HostEnum {

	MEMBER_LIMIT(100),
	REACHABLE_TIMEOUT_LIMIT(1000);

	private final int num;

	public String getCode() {
		return name();
	}
}
