package com.healthplan.work.util;

// import org.mindrot.jbcrypt.BCrypt;
import com.healthplan.work.vo.MemberEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class PasswordEncoderUtils {

@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(MemberEntity mem) {
		String pw = mem.getUpw();

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(pw);

		System.out.println("해시된 비밀번호:" + hashedPassword);

		boolean isPasswordMatch = passwordEncoder.matches(pw, hashedPassword);

		System.out.println("비밀번호 일치 여부:" + isPasswordMatch);
	}

	public static String hashPassword(String pw) {
		return BCrypt.hashpw(pw, BCrypt.gensalt());
	}

	public static boolean verifyPassword(String pw, String hashedPassword) {
		return BCrypt.checkpw(pw, hashedPassword);
	}

	public static String encode(String pw) {
		return null;
	}


}