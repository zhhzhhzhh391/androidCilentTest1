package com.test.natsuyasumi.testdemo21;

import android.content.Context;

import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class readXML {
    Context context;
    org.dom4j.Document document;

    public org.dom4j.Document readXML(Context context){
        this.context = context;

        try {
            final InputStream input = context.getResources().openRawResource(R.raw.texttest1);
            DocumentBuilderFactory dBuliderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuliderFactory.newDocumentBuilder();
            org.w3c.dom.Document domDocument = dBuilder.parse(input);
            DOMReader reader = new DOMReader();
            document = reader.read(domDocument);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    return  document;
    }

    //根据主线程中传来的路线值来返回文档的位置
    public Element getCurrentRoad(String road){
        Element correctRoad = null;
        List getDialogues = document.selectNodes("//allText/dialogues");
        Iterator it = getDialogues.iterator();
        while(it.hasNext()){
            Element e = (Element)it.next();
            if(e.attributeValue("road").equals(road)){
                correctRoad = e;
                break;
            }
        }
        return correctRoad;
    }


}

/*
* //            while(it.hasNext()){
//                Element e = (Element)it.next();
//                System.out.println("type = " + e.attributeValue("type"));
//                if(e.attributeValue("type").equals("dialog")) {
//                    Element xmlText = e.element("text");
//                    System.out.println("text = " + xmlText.getText());
//                }
//                if(e.attributeValue("type").equals("button")) {
//                    Element TextA = e.element("textA");
//                    System.out.println("TextA = " + TextA.getText());
//                    Element toRoad = e.element("toroadA");
//                    System.out.println("toRoad = " + toRoad.getText());
//                }
//            }

*              该类使用说明书
* */
