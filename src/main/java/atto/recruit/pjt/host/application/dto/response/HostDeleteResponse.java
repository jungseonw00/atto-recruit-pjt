package atto.recruit.pjt.host.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HostDeleteResponse {
	private Long id;

	public static HostDeleteResponse of(Long id) {
		return HostDeleteResponse.builder()
			.id(id)
			.build();
	}
}
