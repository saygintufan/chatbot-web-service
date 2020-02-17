/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chatbotservice.application.models;

import com.mycompany.chatbotservice.application.pojo.Question;
import javax.ws.rs.Path;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.converters.ArffLoader;
import weka.core.Instance;
import java.io.*;
import java.net.URL;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import weka.core.Instances;

/**
 *
 * @author Makina
 */
@Path("/postExample")
public class ExampleModelClass {

    double[] rsp = new double[4];

    @POST //İşlem tipi
    @Produces(MediaType.APPLICATION_JSON) //Döndürülen veri tipi
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/example")
    public Question example(Question question) throws Exception {

        System.out.println("example");
        ClassLoader classLoader = new ExampleModelClass().getClass().getClassLoader();

        try {

            FileWriter arff = new FileWriter("C:\\Users\\Makina\\Desktop\\test.arff");
            System.out.println(question.getQuestion());

            arff.append("@relation question\n");
            arff.append("@attribute question string\n");
            arff.append(
                    "@attribute class { rsp1, rsp2, rsp3, rsp4, rsp5, rsp6, rsp7, rsp8, rsp9, rsp10, rsp11, rsp12, rsp13 ,rsp14, rsp15, rsp16, rsp17, rsp18, rsp19, rsp20, rsp21, rsp22, rsp23, rsp24, rsp25, rsp26, rsp27, rsp28 ,rsp29, rsp30, rsp31, rsp32, rsp33, rsp34, rsp35, rsp36, rsp37, rsp38, rsp39, rsp40, rsp41, rsp42, rsp43, rsp44, rsp45, rsp46, rsp47, rsp48, rsp49, rsp50, rsp51, rsp52, rsp53, rsp54, rsp55, rsp56, rsp57, rsp58, rsp59, rsp60, rsp61, rsp62, rsp63, rsp64, rsp65, rsp66, rsp67, rsp68, rsp69, rsp70, rsp71, rsp72, rsp73, rsp74, rsp75, rsp76, rsp77, rsp78, rsp79, rsp80, rsp81, rsp82, rsp83, rsp84, rsp85, rsp86, rsp87, rsp88, rsp89, rsp90, rsp91, rsp92, rsp93, rsp94, rsp95, rsp96, rsp97, rsp98, rsp99, rsp100 ,rsp101, rsp103, rsp104, rsp105, rsp106, rsp107, rsp108, rsp109, rsp110, rsp111, rsp112, rsp113, rsp115, rsp116, rsp117, rsp118}\n");
            arff.append("@data\n");
            arff.append("'" + question.getQuestion() + "'" + ",");
            arff.append("?");

            arff.flush();
            arff.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Model yüklendi
        System.out.println("modeller yükleniyor");

        String J48ModelPath = "models/fcJ48.model";
        String BayesModelPath = "models/fcBayes.model";
        String IbkModelPath = "models/fcIbk.model";

        //resource
        FilteredClassifier NBM = (FilteredClassifier) weka.core.SerializationHelper.read(classLoader.getResource(BayesModelPath).getFile());
        FilteredClassifier J48 = (FilteredClassifier) weka.core.SerializationHelper.read(classLoader.getResource(J48ModelPath).getFile());
        FilteredClassifier IBK = (FilteredClassifier) weka.core.SerializationHelper.read(classLoader.getResource(IbkModelPath).getFile());

        //System.out.println("geldi");
        //BufferedReader breader = null;
        //breader = new BufferedReader(new FileReader("models/test.arff"));

        BufferedReader breader = null;
        breader = new BufferedReader(new FileReader("C:\\Users\\Makina\\Desktop\\test.arff"));

        Instances test = new Instances(breader);
        test.setClassIndex(test.numAttributes() - 1);
        Instances labeled = new Instances(test);

        rsp[0] = NBM.classifyInstance(test.instance(0));       //response'un indisini döndürür.
        rsp[1] = J48.classifyInstance(test.instance(0));      //j48 algoritmasından dönen response'indisini tutar 
        rsp[2] = IBK.classifyInstance(test.instance(0));     //IBK algoritmasından dönen response'indisini tutar 

        //algoritmalardan dönen index değerleri
        System.out.println("clsNBM --> " + rsp[0]);
        System.out.println("clsJ48 --> " + rsp[1]);
        System.out.println("clsIBK --> " + rsp[2]);

        return question;
    }
}
