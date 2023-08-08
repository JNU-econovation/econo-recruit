package com.econovation.recruitdomain.domains.applicant.blocks;


import com.econovation.recruitdomain.domains.applicant.elements.BlockElement;
import java.util.Arrays;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Blocks {
    public static List<Block> asBlocks(Block... blocks) {
        return Arrays.asList(blocks);
    }

    // BooleanBlock
    public static QuestionBlock question(
            ModelConfigurator<QuestionBlock.QuestionBlockBuilder> configurator) {
        return configurator.configure(QuestionBlock.builder()).build();
    }

    public static QuestionBlock question(String blockId, List<BlockElement> elements) {
        return QuestionBlock.builder().blockId(blockId).elements(elements).build();
    }

    public static QuestionBlock question(List<BlockElement> elements) {
        return QuestionBlock.builder().elements(elements).build();
    }
}
