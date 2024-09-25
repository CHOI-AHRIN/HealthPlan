package com.healthplan.work.dao;

import java.util.List;

import com.healthplan.work.vo.DietEntity;
import com.healthplan.work.vo.ExerciseEntity;
import com.healthplan.work.vo.Criteria;
import com.healthplan.work.vo.SearchCriteria;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExerciseMapper {

  public List<ExerciseEntity> listExercise();

 /* public void create(ExerciseEntity community) throws Exception;

  public ExerciseEntity read(Integer cno) throws Exception;
  public List<ExerciseEntity> listPage(int page) throws Exception;

  public void update(ExerciseEntity community) throws Exception;

  public void delete(Integer cno) throws Exception;

  public List<ExerciseEntity> listAll() throws Exception;

  public List<ExerciseEntity> listCriteria(Criteria cri) throws Exception;

  public int countPaging(Criteria cri) throws Exception;
  
  //use for dynamic sql
  
  public List<ExerciseEntity> listSearch(SearchCriteria cri)throws Exception;
  
  public int listSearchCount(SearchCriteria cri)throws Exception;
  
  public int countExerciseList(Criteria cri) throws Exception;

  
  // 댓글 작성 시 댓글 수 plus
  public void updateReplyCnt(Integer cno)throws Exception;
  
  // 댓글 삭제 시 댓글 수 minus
  public void updateReplyCntm(Integer cno) throws Exception;

  public void updateViewCnt(Integer cno)throws Exception;*/
 /* // 첨부파일
  public void addAttach(Map<String, String> map)throws Exception;
  
  public List<String> getAttach(Integer cno)throws Exception;  
   
  public void deleteAttach(Integer cno)throws Exception;
  
  public void replaceAttach(@Param("fileName") String fileName, @Param("cno") Integer cno)throws Exception;*/
  
//  public int currval() throws Exception;
  
}
