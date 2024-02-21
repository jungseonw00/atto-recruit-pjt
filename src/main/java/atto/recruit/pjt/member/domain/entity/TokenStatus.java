package atto.recruit.pjt.member.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TokenStatus {
	VALID, INVALID;

	public String getCode() {
		return name();
	}
}
