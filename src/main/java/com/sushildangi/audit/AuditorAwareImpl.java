package com.sushildangi.audit;


import com.sushildangi.model.User;
import com.sushildangi.security.UserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditorAwareImpl implements AuditorAware<String> {


    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            return ((UserPrincipal) auth.getPrincipal()).getUser();
        } else {
            return null;
        }
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return Optional.empty();
        }
        return Optional.of(currentUser.getId());
    }

}
