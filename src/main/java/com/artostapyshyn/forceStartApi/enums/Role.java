package com.artostapyshyn.forceStartApi.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ROLE_USER, ROLE_MENTOR;

	@Override
	public String getAuthority() {
		return name();
	}
}