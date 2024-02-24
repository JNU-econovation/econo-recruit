package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.label.domain.Label;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LabelRecordPort {
    Label save(Label label);

    void delete(Label label);

    void deleteAll(List<Label> label);

    @Query("delete from Label l where l.idpId = :idpId")
    void deleteByInterviewerId(@Param("idpId") Long idpId);
}
