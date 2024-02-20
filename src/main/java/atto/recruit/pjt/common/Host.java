package atto.recruit.pjt.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Host implements EnumMapperType {

	MEMBER_LIMIT(100);

	private final int num;

	@Override
	public String getCode() {
		return name();
	}

	@Override
	public String getDescription() {
		return null;
	}
}
