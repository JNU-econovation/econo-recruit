package com.econovation.recruitdomain.domains.comment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QComment is a Querydsl query type for Comment */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = 1401397755L;

    public static final QComment comment = new QComment("comment");

    public final com.econovation.recruitdomain.domains.QBaseTimeEntity _super =
            new com.econovation.recruitdomain.domains.QBaseTimeEntity(this);

    public final NumberPath<Applicant> applicant = createNumber("applicant", Applicant.class);

    public final StringPath content = createString("content");

    // inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> idpId = createNumber("idpId", Integer.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    // inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QComment(String variable) {
        super(Comment.class, forVariable(variable));
    }

    public QComment(Path<? extends Comment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComment(PathMetadata metadata) {
        super(Comment.class, metadata);
    }
}
