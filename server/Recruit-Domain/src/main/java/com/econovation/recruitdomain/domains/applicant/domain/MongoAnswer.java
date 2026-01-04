package com.econovation.recruitdomain.domains.applicant.domain;

import com.econovation.recruitdomain.domains.applicant.domain.state.ApplicantState;
import com.econovation.recruitdomain.domains.applicant.domain.state.PeriodStates;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "applicant")
@CompoundIndexes({
        @CompoundIndex(
                name = "unique_class_of_per_year",
                def = "{'year': 1, 'qna.classOf': 1}",
                unique = true
        ),
        @CompoundIndex(
                name = "idx_year_name",
                def = "{'year': 1, 'name': 1}"
        )
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MongoAnswer extends MongoBaseTimeEntity {
    @Id
    /* @GeneratedValue(
               generator =
    "com.econovation.recruitdomain.domains.idGenerator.SnowFlakeGenerator")*/
    private String id;

    @Field("year")
    private Integer year;

    @Field("name")
    private String name;

    // shemaless
    @Field("qna")
    private Map<String, Object> qna;

    @TextIndexed private String qnaSearchIndex;

    // 합,불 상태
    @Field("state")
    private ApplicantState applicantState;

    public void pass(PeriodStates period) {
        this.applicantState.pass(period);
    }

    public void nonPass(PeriodStates period) {
        this.applicantState.nonPass(period);
    }

    public boolean stateEmptyCheckAndInit() {
        if (this.applicantState == null) {
            this.applicantState = new ApplicantState();
            return true;
        }
        return false;
    }

    public ApplicantState getApplicantStateOrDefault() {
        if (this.applicantState == null) return new ApplicantState();
        return this.applicantState;
    }

    public MongoAnswer(String id, Integer year, Map<String, Object> qna) {
        this.id = id;
        this.year = year;
        this.qna = qna;
        this.name = String.valueOf(qna.get("name"));
        this.applicantState = new ApplicantState();
        this.qnaSearchIndex =
                qna.values().stream().map(Object::toString).collect(Collectors.joining(" "));
    }
}
