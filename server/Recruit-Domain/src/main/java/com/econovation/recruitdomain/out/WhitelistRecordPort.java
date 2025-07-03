package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.whitelist.domain.AccessToken;

public interface WhitelistRecordPort {
    void save(AccessToken accessToken);
    void deleteById(Long idpId);
}
