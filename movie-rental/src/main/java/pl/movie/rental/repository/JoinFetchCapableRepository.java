package pl.movie.rental.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.mysema.query.types.Predicate;

@NoRepositoryBean
public interface JoinFetchCapableRepository<T, ID extends Serializable>
		extends JpaRepository<T, ID>, QueryDslPredicateExecutor<T> {

	Page<T> findAll(Predicate predicate, Pageable pageable, JoinDescriptor... joinDescriptors);

	List<T> findAll(Predicate predicate, JoinDescriptor... joinDescriptors);

}
