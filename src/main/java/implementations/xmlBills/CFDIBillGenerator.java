package implementations.xmlBills;

import model.restaurant.Eating;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import view.billsGenerator.BillGenerator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class CFDIBillGenerator implements BillGenerator {
    private static final AtomicInteger billsCount = new AtomicInteger();
    public void generateBill(Eating eating, String url){
        try {
            appendData(eating, url);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }

    private void appendData(Eating eating, String url) throws ParserConfigurationException, TransformerException {
        Document doc = getXMLDocument();
        Element bill = appendTagName(doc);
        appendRestaurantData(eating, doc, bill);
        appendDetails(eating, doc, bill);
        appendClientData(eating, doc, bill);
        saveXMLInFile(eating, url, doc);
    }

    private void appendDetails(Eating eating, Document doc, Element bill) {
        appendAmount(eating, doc, bill);
        appendDate(eating,doc, bill);
        appendCommensalNumber(eating, doc, bill);
    }

    private void appendClientData(Eating eating, Document doc, Element bill) {
        appendClientName(eating, doc, bill);
        appendClientNIF(eating, doc, bill);
    }

    private void appendRestaurantData(Eating eating, Document doc, Element bill) {
        appendRestaurantName(eating, doc, bill);
        appendRestaurantNIF(eating, doc, bill);
    }

    private void saveXMLInFile(Eating eating, String url, Document doc) throws TransformerException {
        Transformer transformer = getTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(url + billsCount.getAndIncrement() + " - "  + eating.getRestaurant().getName() + "-" + eating.getClient().getLastName() + ".xml"));
        transformer.transform(source, result);
    }

    private Transformer getTransformer() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        return transformerFactory.newTransformer();
    }

    private void appendClientNIF(Eating eating, Document doc, Element bill) {
        Element clientNIF = doc.createElement("ClientNIF");
        clientNIF.setTextContent(Integer.toString(eating.getClient().getNIF()));
        bill.appendChild(clientNIF);
    }

    private void appendClientName(Eating eating, Document doc, Element bill) {
        Element clientName = doc.createElement("ClientName");
        clientName.setTextContent(eating.getClient().getFirstName());
        bill.appendChild(clientName);
    }

    private void appendCommensalNumber(Eating eating, Document doc, Element bill) {
        Element commensal = doc.createElement("CommensalNumber");
        commensal.setTextContent(Integer.toString(eating.getInvitedPeople()));
        bill.appendChild(commensal);
    }

    private void appendDate(Eating eating, Document doc, Element bill) {
        Element date = doc.createElement("Date");
        date.setTextContent(eating.getDate().toString());
        bill.appendChild(date);
    }

    private void appendAmount(Eating eating, Document doc, Element bill) {
        Element amount = doc.createElement("Amount");
        amount.setTextContent(Double.toString(eating.getBill().getFinalPrice()));
        bill.appendChild(amount);
    }

    private void appendRestaurantNIF(Eating eating, Document doc, Element bill) {
        Element restaurantNIF = doc.createElement("RestaurantNIF");
        bill.appendChild(restaurantNIF);
        restaurantNIF.setTextContent(Integer.toString(eating.getRestaurant().getNIF()));
    }

    private void appendRestaurantName(Eating eating, Document doc, Element bill) {
        Element restaurantName = doc.createElement("RestaurantName");
        bill.appendChild(restaurantName);
        restaurantName.setTextContent(eating.getRestaurant().getName());
    }

    private Element appendTagName(Document doc) {
        Element bill = doc.createElement("cfdi:Bill");
        doc.appendChild(bill);
        return bill;
    }

    private Document getXMLDocument() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        return docBuilder.newDocument();
    }
}
