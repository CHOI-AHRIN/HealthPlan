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

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
    public boolean isEmpty() {
        return false;
    }
}
