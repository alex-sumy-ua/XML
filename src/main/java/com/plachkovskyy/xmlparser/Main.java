package com.plachkovskyy.xmlparser;

import com.plachkovskyy.xmlparser.utils.DOMParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, SAXException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter source input file: ");
        String inputFileName = scanner.next();
//        File inputFile = new File("resources/" + inputFileName + ".xml");
        File inputFile = new File(inputFileName + ".xml");
        if (!inputFile.exists()) {
            System.out.println("Sorry, file does not exist...");
            System.exit(0);
        }
        String outputFileName = "resources/" + inputFileName + "-out.xml";
        FileOutputStream outputFile = new FileOutputStream(outputFileName);

//        DOMParser.test();
        DOMParser.correctAverage(inputFile, outputFile);
    }

}
