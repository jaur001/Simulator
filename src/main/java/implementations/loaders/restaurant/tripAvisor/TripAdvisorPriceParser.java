package implementations.loaders.restaurant.tripAvisor;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import view.loaders.restaurant.PriceParser;

public class TripAdvisorPriceParser implements PriceParser {

    public static final String DOLLAR = "€";
    public static final String BAR = "-";
    public static final String PRICE_FIRST_OPTION = "restaurants-detail-overview-cards-DetailsSectionOverviewCard__tagText--1OH6h";
    public static final String PRICE_SECOND_OPTION = "restaurants-details-card-TagCategories__tagText--Yt3iG";

    @Override
    public PriceRange getPrice(Document doc){
        Elements els = doc.getElementsByClass(PRICE_FIRST_OPTION);
        if(containsPrice(els)) return parsePrice(getText(els));
        els = doc.getElementsByClass(PRICE_SECOND_OPTION);
        if(containsPrice(els)) return parsePrice(getText(els));
        return new PriceRange();
    }

    private static boolean containsPrice(Elements els) {
        if(els.size()>0){
            String text = getText(els);
            return isValidPrice(text);
        }
        return false;
    }

    private static String getText(Elements els) {
        return els.first().text();
    }

    private static boolean isValidPrice(String text) {
        return text.contains(DOLLAR);
    }

    private static PriceRange parsePrice(String text) {
        String price = getPriceSection(text); // Sometimes it has more details, not only price
        return new PriceRange(getMinPrice(price),getMaxPrice(price));
    }

    private static int getMaxPrice(String price) {
        return Integer.parseInt(price.substring(price.indexOf(BAR) + 2, price.length() - 2));
    }

    private static int getMinPrice(String price) {
        return Integer.parseInt(price.substring(0, price.indexOf(DOLLAR) - 1));
    }

    private static String getPriceSection(String text) {
        return text.substring(0, text.indexOf(DOLLAR, text.indexOf(BAR)) + 1);
    }
}
