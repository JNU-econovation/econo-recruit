package com.econovation.recruitdomain.domains.applicant.elements;


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
public class CheckBoxElement extends BlockElement {
    public static final String TYPE = "checkout";
    private final String type = TYPE;
    private String name;
}
