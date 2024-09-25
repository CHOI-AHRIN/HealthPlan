package com.healthplan.work.vo;

public class Criteria {

    private int page;
    
    // 한 페이지에 보여줄 list 갯수
    private int perPageNum;

    private String pageStart;
    private String pageEnd;

    public Criteria() {
        this.page = 1;
        this.perPageNum = 10;
    }

    public void setPage(int page) {

        if (page <= 0) {
            this.page = 1;
            return;
        }
        this.page = page;
    }

    public void setPerPageNum(int perPageNum) {

        if (perPageNum <= 0 || perPageNum > 100) {
            this.perPageNum = 10;
            return;
        }
        this.perPageNum = perPageNum;
    }

    public int getPage() {
        return page;
    }

    // method for MyBatis SQL Mapper -
    public int getPageStart() {
        return (this.page - 1) * perPageNum + 1;
    }

    // method for MyBatis SQL Mapper
    public int getPerPageNum() {
        return this.perPageNum;
    }

    // 아린 추가
    public int getPageEnd() {
        return getPageStart() + perPageNum - 1;
    }

    @Override
    public String toString() {
        return "Criteria [page=" + page + ", "
                + "perPageNum=" + perPageNum + "]";
    }
}
