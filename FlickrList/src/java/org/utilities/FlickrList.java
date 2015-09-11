/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *    Author     : anmol
 */
package org.utilities;

import com.flickr4java.flickr.*;
import com.flickr4java.flickr.photos.*;
import com.flickr4java.flickr.REST;
import java.util.HashSet;
import java.util.Set;



public class FlickrList {
   
    String apikey;
    String secret;
    Flickr flickr;
    
    
    public FlickrList() {
        this.apikey = null;
        this.secret = null;
        this.flickr = null;
    }
    
    //Initialize apikey and secret
    public void FlickrList(String apikey, String secret) {
        this.apikey = apikey;
        this.secret = secret;
        flickr = new Flickr(apikey, secret, new REST());
    }
    
    //Return a list of pics with the specified tag(s)
    public PhotoList<Photo> getPics(String[] tags) throws Exception  {
        SearchParameters searchParameters = new SearchParameters();
        searchParameters.setTags(tags);
        Set<String> fields = new HashSet<String>();
        fields.add("date_upload");
        fields.add("tags");
        searchParameters.setExtras(fields);
        PhotoList<Photo> list = flickr.getPhotosInterface().search(searchParameters, 500, 500);
     
        return list;
    }
    
    
}

