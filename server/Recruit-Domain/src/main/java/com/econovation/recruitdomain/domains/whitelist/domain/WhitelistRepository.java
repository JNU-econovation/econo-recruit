package com.econovation.recruitdomain.domains.whitelist.domain;

import org.springframework.data.repository.CrudRepository;

public interface WhitelistRepository extends CrudRepository<AccessToken, Long> {
    boolean existsByToken(String token);
}
