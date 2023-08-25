package com.econovation.recruit.api.interviewer.helper;

import com.econovation.recruitcommon.annotation.Helper;
import com.econovation.recruitinfrastructure.idp.client.IdpClient;
import com.econovation.recruitinfrastructure.idp.dto.InterviewerResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@Helper
@RequiredArgsConstructor
public class IdpHelper {
    private final IdpClient idpClient;

    public List<InterviewerResponse> createInterviewers(List<Long> idpIds) {
        return idpIds.stream().map(idpClient::loadById).collect(Collectors.toList());
    }

    public List<InterviewerResponse> loadByNames(List<String> names) {
        return names.stream()
                .map(name -> idpClient.loadByName(name).stream().findFirst().get())
                .collect(Collectors.toList());
    }
}
