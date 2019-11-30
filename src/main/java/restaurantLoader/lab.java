package restaurantLoader;


import model.restaurant.Restaurant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sun.util.logging.LoggingSupport.log;
// class: header_links
public class lab {
    public static void main(String[] args) throws IOException {
        List<Restaurant> restaurantList = new ArrayList<Restaurant>();
        Document doc = Jsoup.connect("https://www.tripadvisor.es/Restaurants-g187471-Gran_Canaria_Canary_Islands.html").get();
        Element topRestaurant = doc.getElementById("component_2");
        Elements topRestaurantList = topRestaurant.getElementsByClass("restaurants-list-ListCell__restaurantName--2aSdo");

        for (Element i : topRestaurantList){
            doc = Jsoup.connect("https://www.tripadvisor.es"+i.attr("href")).get();

            //Name
            System.out.println(i.text());

            //menu
            Elements menuList = doc.getElementsByClass("is-hidden-mobile blEntry menu  ui_link");
            if (menuList.hasText()) {
                String menu = menuList.first().attr("onclick");
                menu = menu.indexOf("https:")>=0?menu.substring(menu.indexOf("https:"),menu.length()-2):"";
                System.out.print(menu);
            }

            //Telephone number
            Elements numberList = doc.getElementsByClass("detail  is-hidden-mobile");
            if (numberList.hasText()) {
                String telephoneNumber = numberList.first().text();
                System.out.println(telephoneNumber);
            }
            //Price
            Elements els = doc.getElementsByClass("restaurants-detail-overview-cards-DetailsSectionOverviewCard__tagText--1OH6h");
            if(els.size()>0){
                String text = els.first().text();
                String price = text.substring(0,text.indexOf("€",text.indexOf("-"))+1);
                System.out.println(price);
                int minPrice = Integer.parseInt(price.substring(0,price.indexOf("€")-1));
                int maxPrice = Integer.parseInt(price.substring(price.indexOf("-")+2,price.length()-2));
                System.out.println(minPrice);
                System.out.println(maxPrice);
            } else {
                els = doc.getElementsByClass("restaurants-details-card-TagCategories__tagText--Yt3iG");
                String text = els.first().text();
                if(els.size()>0){
                    String price = text.substring(0,text.indexOf("€",text.indexOf("-"))+1);
                    System.out.println(price);
                    int minPrice = Integer.parseInt(price.substring(0,price.indexOf("€")-1));
                    int maxPrice = Integer.parseInt(price.substring(price.indexOf("-")+2,price.length()-2));
                    System.out.println(minPrice);
                    System.out.println(maxPrice);
                }
            }
        }

    }
}
