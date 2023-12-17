package com.TeamProject.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.TeamProject.Config.auth.JWTAuthorizationFilter;
import com.TeamProject.Config.filter.JWTAuthenticationFilter;
import com.TeamProject.Repository.MembersRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private MembersRepository memberrepository;

	@Autowired
	private AuthenticationConfiguration authConfig;
	
	@Bean // 리턴값을 IOC컨테이너에 올린다. 즉 외부 클래스에서 사용이 가능하다.
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    public CorsConfigurationSource withCorsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // 허용된 출처 설정, 보안을 고려하여 더 제한적으로 설정할 수 있음
        configuration.addAllowedMethod("*"); // 허용된 HTTP 메서드 설정
        configuration.addAllowedHeader("*"); // 허용된 헤더 설정
        configuration.addAllowedHeader("*"); // 허용된 헤더 설정
        configuration.addAllowedHeader("*"); // 허용된 헤더 설정
        configuration.setAllowCredentials(false); // 교차 출처 요청에 쿠키/인증 정보 포함을 허용
        configuration.addExposedHeader("Authorization"); //헤더에 (Authorization) 포함
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 구성 적용
        return source;
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable()); // CSRF 보호 비활성화 (JS에서 호출 가능)
		http.cors().configurationSource(withCorsConfigurationSource());
		// http.cors(cors->cors.disable()); // CORS 보호 비활성화 (React에서 호출 가능):RestAPI로 호출할 때

		// 모두, member, admin 접근 권한 설정
		http.authorizeHttpRequests(security->{
			security//.requestMatchers("/history").authenticated()
					.anyRequest().permitAll();
		});
		
		http.formLogin(frmLogin->frmLogin.disable()); 
		// Form을 이용한 로그인을 사용하지 않겠다는 설정 즉 .html파일을 작성하지 않아도 된다		
		http.sessionManagement(ssmg->ssmg.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // STATELESS : 지속하지 않는다.
		// 시큐리티 세션을 만들지 않겠다고 설정
		// 그럼 어떻게?
		// 로그인 정보를 넘겨주면 세션을 만들어 jwt 토큰을 생성해서 반환한다. 이후에 삭제한다. 
		// 결론적으로 : 로그인(요청)할 때마다 세션을 생성하고 처리가 끝나면 세션을 지운다.
		// 요청 > 세션생성 > 반응 > 세션삭제 > 토큰반환
		
		// 시큐리티 세션을 만들지 않았기 때문에 필터를 쓰는 건가???
		//// 필터 1
		//http.addFilter(new JWTAuthenticationFilter()); 
		
		//// 필터 2
		http.addFilter(new JWTAuthenticationFilter(authConfig.getAuthenticationManager(), memberrepository)); // 1. 로그인 시도 > 토큰 생성 및 반환
		http.addFilter(new JWTAuthorizationFilter(authConfig.getAuthenticationManager(), memberrepository)); // 2. 토큰으로 로그인 시도 확인 및 인증 및 권한 부여
		return http.build();
	}
}
