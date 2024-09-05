package com.healthplan.work.vo;


import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Data
@Builder
public class SearchCriteria extends Criteria{
    private String searchType=null;
    private String keyword=null;

    public String getSearchType() {
        return searchType;
    }
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public String toString() {
        return super.toString() + " SearchCriteria "
                + "[searchType=" + searchType + ", keyword="
                + keyword + "]";
    }
}
