package com.econovation.recruit.api.record.dto;

import com.econovation.recruit.utils.vo.PageInfo;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.record.domain.Record;
import java.util.List;
import java.util.Map;

public record FilteredRecordsApplicantsDto(
        List<Record> records,
        List<MongoAnswer> applicants,
        Map<String, Double> scoreMap,
        PageInfo pageInfo) {}
