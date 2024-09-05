package com.healthplan.work.service;

import java.util.List;

import com.healthplan.work.vo.ExerciseEntity;
import com.healthplan.work.vo.SearchCriteria;

public interface ExerciseService {

  public void regist(ExerciseEntity community) throws Exception;

  public ExerciseEntity read(Integer cno) throws Exception;

  public void modify(ExerciseEntity community) throws Exception;

  public void remove(Integer cno) throws Exception;

  public List<ExerciseEntity> listAll() throws Exception;

  public List<ExerciseEntity> listCriteria(SearchCriteria cri) throws Exception;

  public int listCountCriteria(SearchCriteria cri) throws Exception;

  public List<ExerciseEntity> listSearchCriteria(SearchCriteria cri)  throws Exception;

  public int listSearchCount(SearchCriteria cri) throws Exception;
  
  public List<String> getAttach(Integer bno)throws Exception;
  

}
