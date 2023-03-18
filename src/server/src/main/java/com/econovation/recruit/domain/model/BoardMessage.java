package com.econovation.recruit.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardMessage {
    private String message;
    private LocalDateTime sendDate;
    private Integer navLoc;
    private Integer colLoc;
}
