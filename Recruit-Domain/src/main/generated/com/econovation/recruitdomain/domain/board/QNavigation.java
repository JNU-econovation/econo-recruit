package com.econovation.recruitdomain.domain.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNavigation is a Querydsl query type for Navigation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNavigation extends EntityPathBase<Navigation> {

    private static final long serialVersionUID = 581444144L;

    public static final QNavigation navigation = new QNavigation("navigation");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> navLoc = createNumber("navLoc", Integer.class);

    public final StringPath navTitle = createString("navTitle");

    public QNavigation(String variable) {
        super(Navigation.class, forVariable(variable));
    }

    public QNavigation(Path<? extends Navigation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNavigation(PathMetadata metadata) {
        super(Navigation.class, metadata);
    }

}

