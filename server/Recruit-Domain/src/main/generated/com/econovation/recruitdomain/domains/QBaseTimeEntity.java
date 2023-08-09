package com.econovation.recruitdomain.domains;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QBaseTimeEntity is a Querydsl query type for BaseTimeEntity */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseTimeEntity extends EntityPathBase<BaseTimeEntity> {

    private static final long serialVersionUID = 797184310L;

    public static final QBaseTimeEntity baseTimeEntity = new QBaseTimeEntity("baseTimeEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt =
            createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt =
            createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QBaseTimeEntity(String variable) {
        super(BaseTimeEntity.class, forVariable(variable));
    }

    public QBaseTimeEntity(Path<? extends BaseTimeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseTimeEntity(PathMetadata metadata) {
        super(BaseTimeEntity.class, metadata);
    }
}
