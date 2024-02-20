package atto.recruit.pjt.host.domain.entity;

import atto.recruit.pjt.common.EnumMapperType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AliveStatus implements EnumMapperType {
	ALIVE("연결가능"),
	NOTALIVE("연결불가");

	private final String status;

	@Override
	public String getCode() {
		return name();
	}

	@Override
	public String getDescription() {
		return status;
	}
}
