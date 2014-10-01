/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import beans.QuizBean;
import beans.UserBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.QuizQuestion;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author James
 */
@Path("quiz")
@RequestScoped
public class QuizResource {

    @Context
    private UriInfo context;

    @Inject
    private UserBean userBean;
    
    @Inject
    private QuizBean quizBean;
    
    /**
     * Creates a new instance of QuizResource
     */
    public QuizResource() {
    }

    private String jsonError(String err) {
        return "{'error': '"+err+"'}";
    }
    
    /**
     * Retrieves representation of an instance of api.QuizResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{chapter}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllChapterQuestions(@PathParam("chapter") Integer chapter) {
//        if(!userBean.isLoggedIn()) {
//            return null;
//        }
//        
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            return mapper.writeValueAsString(quizBean.lookupQuestionsByChapter(chapter));
        }
        catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        
        return "";
    }
    
    @GET
    @Path("{chapter}/{section}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllSectionQuestions(@PathParam("chapter") Integer chapter, @PathParam("section") Integer section) {
//        if(!userBean.isLoggedIn()) {
//            return null;
//        }
//        
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            return mapper.writeValueAsString(quizBean.lookupQuestionsBySection(chapter, section));
        }
        catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        
        return "";
    }
    
    @GET
    @Path("question/{chapter}/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSpecificQuestion(@PathParam("chapter") Integer chapter, 
            @PathParam("number") Integer number) {
//        if(!userBean.isLoggedIn()) {
//            return null;
//        }
//        
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            return mapper.writeValueAsString(quizBean.lookupQuestion(chapter, number));
        }
        catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        
        return "";
    }
    
    @GET
    @Path("check/{chapter}/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String checkAnswer(@PathParam("chapter") Integer chapter, 
            @PathParam("number") Integer number,
            @QueryParam("answer") List<String> answers) {

        boolean result = quizBean.checkAnswer(chapter, number, answers);
        
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            return mapper.writeValueAsString(Collections.singletonMap("isCorrect", result));
        }
        catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        
        return jsonError("Unknown json failure :(");
    }
    
    @GET
    @Path("submit/{chapter}/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String submitAnswer(@PathParam("chapter") Integer chapter, 
            @PathParam("number") Integer number,
            @QueryParam("answer") List<String> answers) {

        if(!userBean.isLoggedIn()) {
            return jsonError("Gotta be logged in bruv.");
        }
        
        boolean result = quizBean.submitAnswer(chapter, number, answers, userBean);

        ObjectMapper mapper = new ObjectMapper();
        
        try {
            return mapper.writeValueAsString(Collections.singletonMap("isCorrect", result));
        }
        catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        
        return jsonError("Unknown json failure :(");
    }
    
    @GET
    @Path("userstatus/{chapter}/{number}/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserAnswerStatus(@PathParam("chapter") Integer chapter, 
            @PathParam("number") Integer number,
            @PathParam("username") String username) {

        int status = quizBean.answerStatus(chapter, number, username);
        
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            return mapper.writeValueAsString(Collections.singletonMap("answerStatus", QuizQuestion.statusToString(status)));
        }
        catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        
        return jsonError("Unknown json failure :(");
    }
    
    /**
     * PUT method for updating or creating an instance of QuizResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
