package com.econovation.recruitdomain.domain.applicant.blocks;


import com.econovation.recruitdomain.domain.applicant.elements.BlockElement;
import com.slack.api.model.block.LayoutBlock;
import java.util.Arrays;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Blocks {
    public static List<LayoutBlock> asBlocks(LayoutBlock... blocks) {
        return Arrays.asList(blocks);
    }

    // BooleanBlock
    public static QuestionBlock binary(
            ModelConfigurator<QuestionBlock.QuestionBlockBuilder> configurator) {
        return configurator.configure(QuestionBlock.builder()).build();
    }

    public static QuestionBlock binary(String blockId, List<BlockElement> elements) {
        return QuestionBlock.builder().blockId(blockId).elements(elements).build();
    }
}
