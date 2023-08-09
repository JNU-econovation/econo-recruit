package com.econovation.recruitdomain.domains.applicant.blocks;

import com.econovation.recruitdomain.domains.applicant.elements.BlockElement;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionBlock implements Block {
    private String blockId;
    private String name;
    private List<BlockElement> elements;
}
