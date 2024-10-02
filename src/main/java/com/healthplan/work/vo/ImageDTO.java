package com.healthplan.work.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDTO {

    private String uuid;

    private String imgName;

    private String path;

    private String imgType;

    private String imageURL;

    private String thumbnailURL;

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
}
