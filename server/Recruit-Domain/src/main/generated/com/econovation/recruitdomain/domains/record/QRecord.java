package com.econovation.recruitdomain.domains.record;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QRecord is a Querydsl query type for Record */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecord extends EntityPathBase<Record> {

    private static final long serialVersionUID = 1253820345L;

    public static final QRecord record1 = new QRecord("record1");

    public final NumberPath<Applicant> applicant = createNumber("applicant", Applicant.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath record = createString("record");

    public final StringPath url = createString("url");

    public QRecord(String variable) {
        super(Record.class, forVariable(variable));
    }

    public QRecord(Path<? extends Record> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecord(PathMetadata metadata) {
        super(Record.class, metadata);
    }
}
