package atto.recruit.pjt;

import org.junit.jupiter.api.Test;

class AttoRecruitPjtApplicationTests {

	@Test
	void contextLoads() {
		String[] paths =
			{
				"/member/login",
				"/member/logout"
			};

		for (String path : paths) {
			boolean result = path.startsWith("/member");
			System.out.println("result = " + result);
		}
	}

}
