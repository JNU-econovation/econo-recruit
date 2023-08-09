package com.econovation.recruitdomain.domains.card;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.dsl.PathInits;
import javax.annotation.processing.Generated;

/** QCard is a Querydsl query type for Card */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCard extends EntityPathBase<Card> {

    private static final long serialVersionUID = -866134055L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCard card = new QCard("card");

    public final com.econovation.recruitdomain.domains.QBaseTimeEntity _super =
            new com.econovation.recruitdomain.domains.QBaseTimeEntity(this);

    public final NumberPath<Applicant> applicant = createNumber("applicant", Applicant.class);

    public final com.econovation.recruitdomain.domains.board.QBoard board;

    // inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    // inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath workCardInfo = createString("workCardInfo");

    public QCard(String variable) {
        this(Card.class, forVariable(variable), INITS);
    }

    public QCard(Path<? extends Card> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCard(PathMetadata metadata, PathInits inits) {
        this(Card.class, metadata, inits);
    }

    public QCard(Class<? extends Card> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board =
                inits.isInitialized("board")
                        ? new com.econovation.recruitdomain.domains.board.QBoard(
                                forProperty("board"), inits.get("board"))
                        : null;
    }
}
