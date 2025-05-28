package com.econovation.recruitdomain.domains.whitelist.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.whitelist.domain.AccessToken;
import com.econovation.recruitdomain.domains.whitelist.domain.WhitelistRepository;
import com.econovation.recruitdomain.out.WhitelistRecordPort;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class WhitelistAdaptor implements WhitelistRecordPort {
    private final WhitelistRepository whitelistRepository;

    @Override
    public void save(AccessToken accessToken) {
        whitelistRepository.save(accessToken);
    }

    @Override
    public void deleteById(Long idpId) {
        whitelistRepository.findById(idpId)
                .ifPresent(whitelistRepository::delete);

    }
}
