package webscrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class lab2 {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://example.com/").get();


        Elements links = doc.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkText = link.text();
            System.out.println(linkHref);
            System.out.println(linkText);
        }
    }
}
