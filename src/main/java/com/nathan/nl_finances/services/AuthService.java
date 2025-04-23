package com.nathan.nl_finances.services;

import com.nathan.nl_finances.exceptions.ForbiddenException;
import com.nathan.nl_finances.model.Role;
import com.nathan.nl_finances.model.User;
import com.nathan.nl_finances.model.projections.UserDetailsProjection;
import com.nathan.nl_finances.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = userRepository.searchUserAndRolesByUsername(username);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return customLoadUser(username, result);
    }

    public void validateSelfOrAdmin(Long userId) {
        User me = userService.authenticated();
        if (!me.getId().equals(userId) && !me.hasRole("ROLE_ADMIN")) {
            throw new ForbiddenException("Access denied");
        }
    }

    private User customLoadUser(String email, List<UserDetailsProjection> projections) {
        User user = new User();
        user.setEmailAddress(email);
        user.setPassword(projections.get(0).getPassword());
        for (UserDetailsProjection projection : projections) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }
}
