package com.luxoftmarket.service.impl;

import com.luxoftmarket.dao.IUserDaoSec;
import com.luxoftmarket.domain.Role;
import com.luxoftmarket.domain.User;
import com.luxoftmarket.domain.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service("userDetailsService")
public class UserDetalisServiceImpl implements UserDetailsService {

    @Autowired
    private IUserDaoSec userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByName(username); // our own User model class
        if (user != null) {
            String password = user.getPassword();
            // additional information on the security object
            boolean enabled = user.getStatus().equals(UserStatus.ACTIVE);
            boolean accontNonExpired = user.getStatus().equals(UserStatus.ACTIVE);
            boolean credentialsNonExpired = user.getStatus().equals(UserStatus.ACTIVE);
            boolean accontNonLocked = user.getStatus().equals(UserStatus.ACTIVE);

            // Let`s populate user roles
            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for (Role role : user.getRoles()) {
                authorities.add(new GrantedAuthorityImpl(role.getName()));
            }

            //Now lte`s create Spring Security User object
            org.springframework.security.core.userdetails.User securityUser = new
                    org.springframework.security.core.userdetails.User(username, password, enabled, accontNonExpired, credentialsNonExpired, accontNonLocked, authorities);
            return securityUser;
        } else {
            throw new UsernameNotFoundException("User not fount !!!");
        }

    }


}

