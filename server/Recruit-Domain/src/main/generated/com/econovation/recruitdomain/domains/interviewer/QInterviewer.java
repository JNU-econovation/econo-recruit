package com.econovation.recruitdomain.domains.interviewer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QInterviewer is a Querydsl query type for Interviewer */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterviewer extends EntityPathBase<Interviewer> {

    private static final long serialVersionUID = 1860132633L;

    public static final QInterviewer interviewer = new QInterviewer("interviewer");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public QInterviewer(String variable) {
        super(Interviewer.class, forVariable(variable));
    }

    public QInterviewer(Path<? extends Interviewer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInterviewer(PathMetadata metadata) {
        super(Interviewer.class, metadata);
    }
}
