package com.mycompany.chatbotservice.application.post;

import com.mycompany.chatbotservice.application.pojo.Question;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/weka")
public class PostExampleClass {

    @POST       //Dışarıya veri işliyecek
    @Consumes(MediaType.APPLICATION_JSON)   //Post metodunun hangi tipte veri alacağını belirten notasyon
    @Path("/postAValue")
    public void postAValue(Question question) throws Exception {
        
        System.out.println(question.getQuestion());
        
    }
}