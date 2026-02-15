package com.econovation.recruitdomain.domains.comment.domain;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CommentDisclosure extends BaseTimeEntity {

    public static final Long SINGLETON_ID = 1L;

    @Id private Long id;

    @Column(nullable = false)
    private boolean isPublic;

    public void changeViewMode() {
        this.isPublic = !isPublic;
    }
}
