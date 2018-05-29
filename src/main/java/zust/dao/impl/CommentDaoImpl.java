package zust.dao.impl;

import org.springframework.stereotype.Repository;

import zust.dao.CommentDaoI;
import zust.entity.MovieComment;

@Repository
public class CommentDaoImpl extends BaseDaoImpl<MovieComment> implements CommentDaoI{

}
