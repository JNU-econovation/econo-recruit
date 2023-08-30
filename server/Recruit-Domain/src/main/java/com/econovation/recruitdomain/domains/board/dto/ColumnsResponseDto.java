package com.econovation.recruitdomain.domains.board.dto;

import com.econovation.recruitdomain.domains.board.domain.Columns;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ColumnsResponseDto {
    private Integer columnsId;
    private String title;
    private Integer navigationId;

    public static ColumnsResponseDto from(Columns columns) {
        return new ColumnsResponseDto(
                columns.getId(), columns.getTitle(), columns.getNavigationId());
    }

    public static List<ColumnsResponseDto> from(List<Columns> columns) {
        return columns.stream().map(ColumnsResponseDto::from).collect(Collectors.toList());
    }
}
