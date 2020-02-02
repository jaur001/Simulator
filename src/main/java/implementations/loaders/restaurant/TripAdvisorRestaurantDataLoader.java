package implementations.loaders.restaurant;

import model.restaurant.Restaurant;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import utils.Utils;
import view.loaders.restaurant.RestaurantDataLoader;

public class TripAdvisorRestaurantDataLoader implements RestaurantDataLoader {

    public static final int MIN_MIN = 5;
    public static final int MIN_MAX = 15;
    public static final int MAX_MIN = 20;
    public static final int MAX_MAX = 50;
    public static final String NAME = "ui_header h1";
    public static final String TELEPHONE = "detail  is-hidden-mobile";
    public static final String STREET = "street-address";
    private int numberTables = 20;
    private int numberWorkers = 10;
    private double socialCapital = 10000;

    @Override
    public Restaurant loadData(Document doc) {
        String name = getName(doc);
        String telephoneNumber = getTelephoneNumber(doc);
        String street = getStreet(doc);
        PriceRange prices = new TripAdvisorPriceParser().getPrice(doc).isAvailable()? new TripAdvisorPriceParser().getPrice(doc): addRandomPrice();
        return new Restaurant(name,street,telephoneNumber,prices.getMinPrice(),prices.getMaxPrice(),numberTables,numberWorkers,socialCapital);
    }

    private PriceRange addRandomPrice() {
        return new PriceRange(generatePrice(MIN_MIN, MIN_MAX),generatePrice(MAX_MIN, MAX_MAX));
    }

    private int generatePrice(int min,int max){
        return Utils.Random(min, max);
    }



    private String getName(Document doc){
        Elements nameList = doc.getElementsByClass(NAME);
        return nameList.first().text();
    }

    private String getTelephoneNumber(Document doc){
        Elements numberList = doc.getElementsByClass(TELEPHONE);
        if (numberList.hasText()) {
            return numberList.first().text();
        }
        return "";
    }

    private String getStreet(Document doc) {
        Elements streetList = doc.getElementsByClass(STREET);
        if (streetList.hasText()) {
            return streetList.first().text();
        }
        return "";
    }
}
