package zust.dao;

import java.util.List;

import zust.entity.movie;

public interface MovieDaoI extends BaseDaoI<movie> {

	List<movie> find(String hql, List<Integer> ids, int page, int rows);

}
