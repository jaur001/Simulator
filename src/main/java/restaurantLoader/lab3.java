package restaurantLoader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class lab3 {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.tripadvisor.es/Restaurant_Review-g562819-d16759492-Reviews-Coco_Food_wines-Playa_del_Ingles_Maspalomas_Gran_Canaria_Canary_Islands.html").get();
        Elements els = doc.getElementsByClass("restaurants-detail-overview-cards-DetailsSectionOverviewCard__tagText--1OH6h");
        System.out.println(els.size());
        for (Element el : els){
            System.out.println(el.text());
        }

    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}
