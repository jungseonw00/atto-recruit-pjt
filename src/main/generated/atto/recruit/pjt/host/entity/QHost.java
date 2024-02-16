package atto.recruit.pjt.host.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHost is a Querydsl query type for Host
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHost extends EntityPathBase<Host> {

    private static final long serialVersionUID = -1241821167L;

    public static final QHost host = new QHost("host");

    public final atto.recruit.pjt.common.QBaseTimeEntity _super = new atto.recruit.pjt.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ip = createString("ip");

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QHost(String variable) {
        super(Host.class, forVariable(variable));
    }

    public QHost(Path<? extends Host> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHost(PathMetadata metadata) {
        super(Host.class, metadata);
    }

}

