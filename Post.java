package com.example.javaxml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class Post {
    public Document doc;
    private String path;

    public Post(String paramPath) throws ParserConfigurationException, IOException, SAXException {
        path = paramPath;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        File file = new File(path);
        if(file.exists()){
            doc = documentBuilder.parse(path);
        }else{
            doc = documentBuilder.newDocument();
        }
    }

    public void transformerDoc() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult streamResult = new StreamResult(new File(path));
            DOMSource domSource = new DOMSource(doc);
            transformer.transform(domSource, streamResult);
        }
        catch (TransformerException e){
            System.out.println(e);
        }
    }
}
