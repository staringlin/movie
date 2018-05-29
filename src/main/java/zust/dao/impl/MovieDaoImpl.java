package zust.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import zust.dao.MovieDaoI;
import zust.entity.MovieRecommend;
import zust.entity.movie;

@Repository
public class MovieDaoImpl extends BaseDaoImpl<movie> implements MovieDaoI {


	public List<movie> find(String hql, List<Integer> ids, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (ids != null && ids.size()>0) {
				q.setParameterList("ids", ids);
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

}
