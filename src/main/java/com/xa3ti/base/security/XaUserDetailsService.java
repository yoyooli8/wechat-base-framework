package com.xa3ti.base.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xa3ti.base.entity.XaCmsUser;
import com.xa3ti.base.repository.XaCmsResourceRepository;
import com.xa3ti.base.repository.XaCmsUserRepository;

@Service("UserDetailsService")
@Transactional(readOnly = true)
public class XaUserDetailsService implements UserDetailsService {

	@Autowired
	private XaCmsUserRepository msCmsUserRepository;

	@Autowired
	private XaCmsResourceRepository msCmsResourceRepository;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		XaUserDetails msUserDetails = new XaUserDetails();
		try {
			XaCmsUser user = msCmsUserRepository.findByUserName(username);
			if (user != null) {
				List<String> rList = msCmsResourceRepository
						.findRoleNameByUserName(username);
				msUserDetails.setUsername(user.getUserName());
				msUserDetails.setPassword(user.getPassword());
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				for (String roleName : rList) {
					GrantedAuthority authority = new SimpleGrantedAuthority(
							roleName);

					authorities.add(authority);
				}
				msUserDetails.setAuthorities(authorities);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msUserDetails;
	}

}
