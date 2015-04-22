package com.mycompany.automoviles.util;

import java.util.List;
import java.util.Map;

import org.hibernate.ScrollableResults;
import org.hibernate.ejb.QueryImpl;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class QueryDataModel<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 3929975525572614478L;

	private String jpql;

	@SuppressWarnings("unchecked")
	public QueryDataModel(String jpql) {
		this.jpql = jpql;
		QueryImpl<T> query = (QueryImpl<T>) JPAUtil.getEntityManager()
				.createQuery(jpql);
		ScrollableResults scroll = query.getHibernateQuery().scroll();
		scroll.last();
		int count = scroll.getRowNumber();
		setRowCount(count);
	}

	@SuppressWarnings("unchecked")
	public List<T> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		return JPAUtil.getEntityManager().createQuery(jpql)
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

}
