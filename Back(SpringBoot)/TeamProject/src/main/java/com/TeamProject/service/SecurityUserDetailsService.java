package com.TeamProject.Service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.TeamProject.Domain.Members;
import com.TeamProject.Repository.MembersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

	private final MembersRepository memberrepository; // JPA상속받은 클래스를 통해 요청한 데이터를 DB에서 불러올 수 있다. 
	
	@Override
	public UserDetails loadUserByUsername(String loginEmail) throws UsernameNotFoundException {
		Members member = memberrepository.findByLoginEmail(loginEmail);
//				.orElseThrow(() -> 
//				new UsernameNotFoundException("Not Found!"));
		// username이 존재하지 않으면 "NOT Found" 를 리턴한다. (아이디가 없을 경우)
		// .orElseThrow를 쓰지 않고 조건문으로 직접 로직을 구현해도 된다. 
		// 대신 member는 객체(Object)기 때문에 객체로 저장해야한다. 

		//System.out.println(member.getLoginEmail());
		
		//System.out.println(petMember.getPassword());   // 확인용
		System.out.println(member.getAuthorities());// 확인용
		
		return new User(member.getLoginEmail(), 
						member.getPassword(), 
						member.getAuthorities());
	}

}
