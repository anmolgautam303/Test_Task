/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
    Author     : anmol
 */
package web.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.utilities.FlickrList;
import com.flickr4java.flickr.photos.*;
import com.flickr4java.flickr.tags.Tag;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.utilities.FlickrPic;


/**
 *
 * @author 
 */
@WebServlet(name = "searchPics", urlPatterns = {"/searchPics"})
public class searchPics extends HttpServlet {
    
    FlickrList flickrPics;
    ArrayList <FlickrPic> picList;

    
    //An API key is required to use this script. You can request one on Flickr.
        String apikey = "68b7a9f8a266c7c2b6a816ba540126b4"; //dbbb0f9961a61177a7d5108e2e48dbd3
        String secret = "56790118d7685cc0"; //7ae6043718c599ba
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            flickrPics = new FlickrList();
            picList = new ArrayList();
            response.setContentType("text/html;charset=UTF-8");
            String title;
            String [] listTags;
            String tag = request.getParameter("Tag");
            String tagsPic;
            if (tag.contains(",")) {
                listTags = tag.split(",");
            } else {
                listTags = new String[1];
                listTags[0]=tag;
            }
            
            flickrPics.FlickrList(apikey,secret);
            
           
                Date dateCreated;
                Collection<Photo> listPics = flickrPics.getPics(listTags);
             
                for(Photo photo : listPics) {
               
                    title = photo.getTitle();
                    if (title.isEmpty())
                        title = "untitled";
                    
                  
                    dateCreated = photo.getDatePosted();
                    
                    tagsPic = "";
                    
                    for(Tag t:photo.getTags()) {
                        if (!"".equals(tagsPic))
                            tagsPic = tagsPic.concat(",");
                        tagsPic = tagsPic.concat(t.getValue());
                    }
                    
                    picList.add(new FlickrPic(dateCreated,tagsPic,photo.getLargeUrl(),title));
              
            }
                HttpSession session = request.getSession();
		session.setAttribute("lista", picList);
                session.setAttribute("search", tag);
		RequestDispatcher a = request.getRequestDispatcher("results.jsp");
		a.forward(request, response);
        }   catch (Exception ex) {
            Logger.getLogger(searchPics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
