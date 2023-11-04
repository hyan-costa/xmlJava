package com.example.javaxml;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import java.util.Scanner;

public class ManipulaXML {
    public static void main(String[] args) {
        try {
            String path = "arquivo.xml";
            Scanner input = new Scanner(System.in);
            Post newPost = new Post(path);

            System.out.println("digite o texto:");
            String textInput = input.next();
            Element post = newPost.doc.createElement("post");
            Attr id = newPost.doc.createAttribute("id");
            long tempo = System.currentTimeMillis();

            //adiciona o valor no elemento id
            id.setValue(String.valueOf(tempo));

            //insere o atributo id na tag post
            post.setAttributeNode(id);

            //aqui criamos um ememento text e inserimos o texto digitado no teclado.
            // Depois disso inserimos o text como filho da tag post.
            Element text = newPost.doc.createElement("text");
            text.appendChild(newPost.doc.createTextNode(textInput));
            post.appendChild(text);


            //verifica se existe elementos no arquivo. se sim, o arquivo já existe
            boolean docbool = newPost.doc.getDocumentElement() == null;

            if(docbool){
                //CRIANDO ELEMENTOS..
                Element raiz = newPost.doc.createElement("posts");
                newPost.doc.appendChild(raiz);
                raiz.appendChild(post);
            }else {
                Element raiz = (Element) newPost.doc.getDocumentElement().getChildNodes();
                raiz.appendChild(post);
            }

            newPost.transformerDoc();
            System.out.println("criado!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
