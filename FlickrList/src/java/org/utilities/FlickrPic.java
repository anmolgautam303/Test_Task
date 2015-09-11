/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utilities;

import java.util.Date;

/**
 *
 * @author Jossy
 */
public class FlickrPic {
    private Date dateUpload;
    private String tagsList;
    private String url;
    private String title;

    public FlickrPic(Date dateUpload, String tagsList, String url, String title) {
        this.dateUpload = dateUpload;
        this.tagsList = tagsList;
        this.url = url;
        this.title = title;
    }

 
    
    public Date getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(Date dateUpload) {
        this.dateUpload = dateUpload;
    }

    public String getTagsList() {
        return tagsList;
    }

    public void setTagsList(String tagsList) {
        this.tagsList = tagsList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
