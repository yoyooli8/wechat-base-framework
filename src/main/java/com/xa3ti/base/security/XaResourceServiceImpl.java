package com.xa3ti.base.security;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xa3ti.base.entity.XaCmsResource;
import com.xa3ti.base.repository.XaCmsResourceRepository;

@Service("XaResourceService")
@Transactional(readOnly = true)
public class XaResourceServiceImpl implements XaResourceService {

	@Autowired
	private XaCmsResourceRepository msCmsResourceRepository;

	public Map<String, String> findResourceAndRole() {
		Map<String, String> result = new HashMap<String, String>();
		Iterator<XaCmsResource> iterator = msCmsResourceRepository.findAll()
				.iterator();
		while (iterator.hasNext()) {
			XaCmsResource resource = iterator.next();
			String resourceId = resource.getResourceId();
//			List<String> roleNameList = new ArrayList<String>();
			List<String> roleNameList = msCmsResourceRepository
					.findRoleNameByResourceId(resourceId);

			String roles = "";
			for (int i = 0; i < roleNameList.size(); i++) {
				String role = roleNameList.get(i);
				if ("ROLE_ALL".equals(role)) {
					roles = "permitAll";
					break;
				}
				if (i > 0) {
					roles = roles + " and hasRole('" + role + "')";
				} else {
					roles = roles + "hasRole('" + role + "')";
				}
			}
			result.put(resource.getResourceUrl(), roles);
		}
		return result;
	}

}
