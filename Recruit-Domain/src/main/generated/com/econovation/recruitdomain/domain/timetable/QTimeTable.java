package com.econovation.recruitdomain.domain.timetable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTimeTable is a Querydsl query type for TimeTable
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTimeTable extends EntityPathBase<TimeTable> {

    private static final long serialVersionUID = 1627186144L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTimeTable timeTable = new QTimeTable("timeTable");

    public final com.econovation.recruitdomain.domain.applicant.QApplicant applicant;

    public final StringPath day = createString("day");

    public final StringPath endTime = createString("endTime");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath startTime = createString("startTime");

    public QTimeTable(String variable) {
        this(TimeTable.class, forVariable(variable), INITS);
    }

    public QTimeTable(Path<? extends TimeTable> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTimeTable(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTimeTable(PathMetadata metadata, PathInits inits) {
        this(TimeTable.class, metadata, inits);
    }

    public QTimeTable(Class<? extends TimeTable> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.applicant = inits.isInitialized("applicant") ? new com.econovation.recruitdomain.domain.applicant.QApplicant(forProperty("applicant")) : null;
    }

}

