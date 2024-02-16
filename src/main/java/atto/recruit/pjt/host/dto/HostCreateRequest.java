package atto.recruit.pjt.host.dto;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor(access = PRIVATE)
@Data
public class HostCreateRequest {
	// 필수값
	private String name;
	private String ip;
}
