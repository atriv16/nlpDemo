package com.refinitv.nlpdemo.service;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@Component()
public class Pipeline {

    private static Properties properties;
    private static String propertiesName = "tokenize, ssplit, pos, lemma, ner, parse, sentiment";
    private static StanfordCoreNLP stanfordCoreNLP;

    private Pipeline(){

    }

    static {
        properties = new Properties();
        properties.setProperty("annotators",propertiesName);
    }

    @Bean(name = "stanfordCoreNLP")
    public static StanfordCoreNLP getInstance(){
        if(stanfordCoreNLP == null){
            stanfordCoreNLP = new StanfordCoreNLP(properties);
        }
        return stanfordCoreNLP;
    }
}
