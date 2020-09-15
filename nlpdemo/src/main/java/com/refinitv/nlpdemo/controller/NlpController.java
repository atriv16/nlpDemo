package com.refinitv.nlpdemo.controller;

import com.refinitv.nlpdemo.enums.Type;
import com.refinitv.nlpdemo.service.ImageProcessingService;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/nlp")
public class NlpController {

    @Autowired
    private StanfordCoreNLP stanfordCoreNLP;

    @Autowired
    private ImageProcessingService imageProcessingService;

    @PostMapping
    @RequestMapping(value = "/ner")
    public Set ner(@RequestBody final String input, @RequestParam final Type type) {

        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabels = coreDocument.tokens();

        return collectList(coreLabels, type);
    }

    private Set collectList(List<CoreLabel> coreLabels, final Type type) {

        coreLabels
                .stream()
                .forEach(coreLabels1 -> System.out.println(coreLabels1.originalText() + " = " + coreLabels1.get(CoreAnnotations.NamedEntityTagAnnotation.class)));
        return coreLabels
                .stream()
                .filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
                .map(CoreLabel::originalText)
                .collect(Collectors.toSet());
    }

    @PostMapping
    @RequestMapping(value = "/tokenize")
    public Set tokenize(@RequestBody final String input) {

        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabels = coreDocument.tokens();

        return coreLabels.stream().map(coreLabel -> coreLabel.originalText()).collect(Collectors.toSet());
    }

    @PostMapping
    @RequestMapping(value = "/sentence")
    public Set sentence(@RequestBody final String input) {

        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreSentence> coreSentences = coreDocument.sentences();

        return coreSentences.stream().map(CoreSentence::text).collect(Collectors.toSet());
    }

    @PostMapping
    @RequestMapping(value = "/pos")
    public Map<String, String> partsOfSpeech(@RequestBody final String input) {

        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabels = coreDocument.tokens();

        return coreLabels.stream().collect(Collectors.toMap(CoreLabel::originalText, coreLabel -> coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class), (n,o)-> o));

    }

    @RequestMapping(value = "/lemma")
    public Map<String, String> lemma(@RequestBody final String input) {

        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabels = coreDocument.tokens();

        return coreLabels.stream().collect(Collectors.toMap(CoreLabel::originalText, coreLabel -> coreLabel.lemma(), (n,o)-> o));

    }

    @PostMapping
    @RequestMapping(value = "/sentiment")
    public Map<String, String> sentiment(@RequestBody final String input) {

        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreSentence> coreSentences = coreDocument.sentences();

        HashMap hashMap = new HashMap();


        for(CoreSentence coreSentence : coreSentences){
            hashMap.put(coreSentence.text(),coreSentence.sentiment());
        }

        return hashMap;
    }

    @GetMapping
    @RequestMapping(value = "/api")
    public String getMessage() {
        return imageProcessingService.processImage();
    }

}
