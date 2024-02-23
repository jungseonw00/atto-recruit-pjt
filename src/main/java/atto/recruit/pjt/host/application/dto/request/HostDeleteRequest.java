package atto.recruit.pjt.host.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HostDeleteRequest {
	private String userId;
}
