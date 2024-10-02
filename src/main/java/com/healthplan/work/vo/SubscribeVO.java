package com.healthplan.work.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeVO {

    private int sno;
    private int mno;
    private String uuid;
    private String stype;
    private String title;
    private String contents;
    private String wdate;
    private int counts;
    private String[] fileid;
    private String[] imgtype;
    private String titleimg;
    private String replycnt;
    private int spoint;

    @Builder.Default
    private List<ImageDTO> imageDTOList = new ArrayList<>();

}
