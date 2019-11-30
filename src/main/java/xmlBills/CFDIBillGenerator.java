package xmlBills;

import model.client.Client;
import model.restaurant.Eating;
import model.restaurant.Restaurant;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CFDIBillGenerator {
    private static int billsCount = 0;

    public static void generateBill(Eating eating){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            //Bill Element
            Element bill = doc.createElement("cfdi:Bill");
            doc.appendChild(bill);

            //Bills child Elements
            Element restaurantName = doc.createElement("RestaurantName");
            bill.appendChild(restaurantName);
            restaurantName.setTextContent(eating.getRestaurant().getName());

            Element restaurantNIF = doc.createElement("RestaurantNIF");
            bill.appendChild(restaurantNIF);
            restaurantNIF.setTextContent(Integer.toString(eating.getRestaurant().getNIF()));

            Element amount = doc.createElement("Amount");
            amount.setTextContent(Double.toString(eating.getAmount()));
            bill.appendChild(amount);


            Element date = doc.createElement("Date");
            date.setTextContent("10/10/2020");
            bill.appendChild(date);

            Element commensal = doc.createElement("CommensalNumber");
            commensal.setTextContent(Integer.toString(eating.getInvitedPeople()));
            bill.appendChild(commensal);

            Element clientName = doc.createElement("ClientName");
            clientName.setTextContent(eating.getClient().getName());
            bill.appendChild(clientName);

            Element clientNIF = doc.createElement("ClientNIF");
            clientNIF.setTextContent(Integer.toString(eating.getClient().getNIF()));
            bill.appendChild(clientNIF);

            //save XML in a file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("./XMLFiles/"+ billsCount++ + " - "  + eating.getRestaurant().getName() + "-" + eating.getClient().getSurname() + ".xml"));
            transformer.transform(source, result);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
