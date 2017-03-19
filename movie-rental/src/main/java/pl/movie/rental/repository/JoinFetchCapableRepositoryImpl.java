package pl.movie.rental.repository;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.PathBuilder;

public class JoinFetchCapableRepositoryImpl<T, ID extends Serializable> extends QueryDslJpaRepository<T, ID>
		implements JoinFetchCapableRepository<T, ID> {

	private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;

	private final EntityPath<T> path;

	private final PathBuilder<T> builder;

	private final Querydsl querydsl;

	public JoinFetchCapableRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
		this(entityInformation, entityManager, DEFAULT_ENTITY_PATH_RESOLVER);
	}

	public JoinFetchCapableRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager,
			EntityPathResolver resolver) {
		super(entityInformation, entityManager, resolver);
		this.path = resolver.createPath(entityInformation.getJavaType());
		this.builder = new PathBuilder<>(path.getType(), path.getMetadata());
		this.querydsl = new Querydsl(entityManager, builder);

	}

	@Override
	public Page<T> findAll(Predicate predicate, Pageable pageable, JoinDescriptor... joinDescriptors) {
		JPQLQuery countQuery = createQuery(predicate);
		JPQLQuery query = querydsl.applyPagination(pageable, createFetchQuery(predicate, joinDescriptors));
		Long total = countQuery.count();
		List<T> content = total > pageable.getOffset() ? query.list(path) : Collections.<T>emptyList();
		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public List<T> findAll(Predicate predicate, JoinDescriptor... joinDescriptors) {
		// TODO Auto-generated method stub
		return null;
	}

	protected JPQLQuery createFetchQuery(Predicate predicate, JoinDescriptor... joinDescriptors) {
		JPQLQuery query = querydsl.createQuery(path).distinct();
		for (JoinDescriptor joinDescriptor : joinDescriptors)
			join(joinDescriptor, query);
		return query.where(predicate);

	}

	private JPQLQuery join(JoinDescriptor joinDescriptor, JPQLQuery query) {
		switch (joinDescriptor.type) {
		case DEFAULT:
			throw new IllegalArgumentException("cross join not supported");
		case INNERJOIN:
			query.innerJoin(joinDescriptor.path);
			break;
		case JOIN:
			query.join(joinDescriptor.path);
			break;
		case LEFTJOIN:
			query.leftJoin(joinDescriptor.path);
			break;
		case RIGHTJOIN:
			query.rightJoin(joinDescriptor.path);
			break;
		case FULLJOIN:
			query.fullJoin(joinDescriptor.path);
			break;
		}
		return query.fetch();
	}
}
