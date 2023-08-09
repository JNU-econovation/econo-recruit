package com.econovation.recruitdomain.domains.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.dsl.PathInits;
import javax.annotation.processing.Generated;

/** QBoard is a Querydsl query type for Board */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -743284855L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final com.econovation.recruitdomain.domains.QBaseTimeEntity _super =
            new com.econovation.recruitdomain.domains.QBaseTimeEntity(this);

    public final NumberPath<Integer> colLoc = createNumber("colLoc", Integer.class);

    public final StringPath colTitle = createString("colTitle");

    // inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lowLoc = createNumber("lowLoc", Integer.class);

    public final QNavigation navigation;

    // inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.navigation =
                inits.isInitialized("navigation")
                        ? new QNavigation(forProperty("navigation"))
                        : null;
    }
}
