package com.econovation.recruit.api.record.dto;

import com.econovation.recruit.utils.vo.PageInfo;
import com.econovation.recruitdomain.domains.dto.RecordResponseDto;
import com.econovation.recruitdomain.domains.record.domain.Record;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RecordsResponseDto {
    private List<RecordResponseDto> records;
    private PageInfo pageInfo;

    public static List<RecordResponseDto> from(List<Record> records) {
        return records.stream().map(RecordResponseDto::from).toList();
    }

    public static RecordsResponseDto of(PageInfo pageInfo, List<Record> records) {
        return RecordsResponseDto.builder().pageInfo(pageInfo).records(from(records)).build();
    }
}
