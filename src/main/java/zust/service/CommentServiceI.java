package zust.service;

import java.util.List;

import zust.dto.CommentDto;
import zust.dto.Json;
import zust.dto.MovieDto;
import zust.entity.MovieComment;

public interface CommentServiceI {

	public List<MovieComment> getComment(MovieDto dto);

	public void add(CommentDto dto);

	public List<MovieComment> getComments(String currentPage,int rows);

	public int getCount(int rows);

	public Json delete(int id);
}
