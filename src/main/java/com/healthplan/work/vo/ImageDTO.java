package com.healthplan.work.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ImageDTO {

    private String uuid;

    private String imgName;

    private String path;

    private String imgType;

    private String imageURL;

    private String thumbnailURL;

    private int cbno;

    // private String imgURL;


    //    public String getImageURL(){
//        try {
//            return URLEncoder.encode(path+"/"+uuid+"_"+imgName,"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//    public String getThumbnailURL(){
//        try {
//            return URLEncoder.encode(path+"/s_"+uuid+"_"+imgName,"UTF-8");
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
    public boolean isEmpty() {
        return false;
    }

    // Getter와 Setter
    public void setCbno(int cbno) {
        this.cbno = cbno;
    }
    public int getCbno() {
        return cbno;
    }
}
