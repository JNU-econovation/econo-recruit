package com.econovation.recruitdomain.domain.applicant.elements;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RadioElement extends BlockElement {
    /*radio 는 중복 선택이 가능하다*/
    public static final String TYPE = "radio";
    private final String type = TYPE;
    private String name;
}
