package com.plachkovskyy.xmlparser.utils;

import com.plachkovskyy.xmlparser.model.Student;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DOMParser {

    public static void test() throws ParserConfigurationException, SAXException, IOException {
        // Список для сотрудников из XML файла
        ArrayList<Student> students = new ArrayList<Student>();

        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        Document document = builder.parse(new File("resources/group.xml"));

        // Получение списка всех элементов employee внутри корневого элемента (getDocumentElement возвращает ROOT элемент XML файла).
        NodeList studentElements = document.getDocumentElement().getElementsByTagName("student");

        // Перебор всех элементов employee
        for (int i = 0; i < studentElements.getLength(); i++) {
            Node student = studentElements.item(i);

            // Получение атрибутов каждого элемента
            NamedNodeMap attributes = student.getAttributes();

            // Добавление сотрудника. Атрибут - тоже Node, потому нам нужно получить значение атрибута с помощью метода getNodeValue()
            students.add(new Student(attributes.getNamedItem("firstname").getNodeValue(),
                                     attributes.getNamedItem("lastname").getNodeValue(),
                                     attributes.getNamedItem("groupnumber").getNodeValue()));
        }
        // Вывод информации о каждом сотруднике
        for (Student student : students)
            System.out.println(String.format("Информации о студенте: имя - %s, фамилия - %s, группа - %s.",
                                            student.getFirstname(), student.getLastname(), student.getGroupnumber()));
    }


    public static void correctAverage(File inputFile, FileOutputStream outputFile)
                                throws ParserConfigurationException, IOException, SAXException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);  // if the factory configured for syntax analyse of XML content during analyse.
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(null); // use the default error handler.
        Document document = builder.parse(inputFile);
        boolean errorFound = false;

            NodeList studentElements = document.getDocumentElement().getElementsByTagName("student");
            for (int i = 0; i < studentElements.getLength(); i++) {
                NodeList nodeList = studentElements.item(i).getChildNodes();
                for (int j = 0; j < nodeList.getLength(); j++) {
                    if (nodeList.item(j) instanceof Element && "average".equals(nodeList.item(j).getNodeName())) {
                        Double average = Double.parseDouble(nodeList.item(j).getTextContent());
                        if (!average.equals(averageCalculate(nodeList))) {
                            nodeList.item(j).setTextContent(String.valueOf(averageCalculate(nodeList)));
                            errorFound = true;
                        }
                    }
                }
            }
            if (errorFound) {
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "resources/group.dtd");
                transformer.transform(new DOMSource(document), new StreamResult(outputFile));
                System.out.println("Document has been corrected successfully.");
            } else {
                System.out.println("Document does not have any errors.");
            }
    }

    private static Double averageCalculate(NodeList nodeList) {
        Double summ = 0d;
        Integer quantity = 0;
        for (int j = 0; j < nodeList.getLength(); j++) {
            if ((nodeList.item(j) instanceof Element) && ("subject".equalsIgnoreCase(((Element) nodeList.item(j)).getTagName()))) {
                Double value = Double.parseDouble(((Element) nodeList.item(j)).getAttribute("value"));
                summ += value;
                quantity++;
            }
        }
        return Math.round(100.0 * summ / quantity) / 100.0;
    }

}
