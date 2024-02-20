package atto.recruit.pjt.host.domain.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HostDeleteResponse {
	private Long hostId;
	private String name;
	private String ip;
}
