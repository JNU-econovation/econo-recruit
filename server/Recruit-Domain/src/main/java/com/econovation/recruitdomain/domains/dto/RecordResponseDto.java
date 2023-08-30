package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitdomain.domains.record.domain.Record;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RecordResponseDto {
    private String url;
    private String record;

    public static RecordResponseDto from(Record record) {
        return RecordResponseDto.builder().url(record.getUrl()).record(record.getRecord()).build();
    }

    public static List<RecordResponseDto> from(List<Record> records) {
        return records.stream().map(RecordResponseDto::from).collect(Collectors.toList());
    }
}
