package xmlBills;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class lab {
    public static void main(String argv[]) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //Elemento raíz
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("cfdi:Bill");
            doc.appendChild(rootElement);

            //Primer elemento
            Element elemento1 = doc.createElement("Restaurant");
            rootElement.appendChild(elemento1);

            //Se agrega un atributo al nodo elemento y su valor
            elemento1.setTextContent("Pizzeria Da Luigi");

            Element elemento2 = doc.createElement("Amount");
            elemento2.setTextContent("15");
            rootElement.appendChild(elemento2);

            //Se escribe el contenido del XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("./XMLFiles/prueba.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
