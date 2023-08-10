package com.econovation.recruitdomain.domains.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardMessage {
    private String message;
    private LocalDateTime sendDate;
    private Integer navLoc;
    private Integer colLoc;
}
