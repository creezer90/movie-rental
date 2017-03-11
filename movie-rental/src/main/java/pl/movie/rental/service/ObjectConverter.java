package pl.movie.rental.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Order;

import pl.movie.rental.DTO.PageDTO;

public interface ObjectConverter<OBJFROM, OBJTO> {

	OBJTO convert(OBJFROM model);

	default Collection<OBJTO> convert(Collection<OBJFROM> model) {
		Collection<OBJTO> converted = new ArrayList<>();
		model.forEach(m -> converted.add(convert(m)));
		return converted;
	}

	@SuppressWarnings("unchecked")
	default PageDTO<OBJTO> convert(Page<OBJFROM> page) {
		Collection<OBJFROM> content = page.getContent();

		Iterator<Order> orders = page.getSort().iterator();
		Order order = orders.next();

		String sortBy = order.getProperty();
		String direction = order.getDirection().name();
		String lastSortBy = orders.next().getProperty();

		return (PageDTO<OBJTO>) PageDTO.builder()//
				.pageNumber(page.getNumber())//
				.pageSize(page.getSize())//
				.sortDirection(direction)//
				.sortBy(sortBy)//
				.lastSortBy(sortBy)//
				.totalPages(page.getTotalPages())//
				.totalElements(page.getTotalElements())//
				.result((List<Object>) convert(content))//
				.build();//
	}
}
