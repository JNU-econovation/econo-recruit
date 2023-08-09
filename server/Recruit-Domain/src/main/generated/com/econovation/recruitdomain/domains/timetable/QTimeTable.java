package com.econovation.recruitdomain.domains.timetable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QTimeTable is a Querydsl query type for TimeTable */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTimeTable extends EntityPathBase<TimeTable> {

    private static final long serialVersionUID = 1109745439L;

    public static final QTimeTable timeTable = new QTimeTable("timeTable");

    public final NumberPath<Applicant> applicant = createNumber("applicant", Applicant.class);

    public final StringPath day = createString("day");

    public final StringPath endTime = createString("endTime");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath startTime = createString("startTime");

    public QTimeTable(String variable) {
        super(TimeTable.class, forVariable(variable));
    }

    public QTimeTable(Path<? extends TimeTable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimeTable(PathMetadata metadata) {
        super(TimeTable.class, metadata);
    }
}
