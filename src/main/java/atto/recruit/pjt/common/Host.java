package atto.recruit.pjt.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Host {

	MEMBER_LIMIT(100);

	private final int num;

	public String getCode() {
		return name();
	}
}
