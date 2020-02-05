package time;

import model.client.Client;
import model.provider.Provider;
import model.restaurant.Restaurant;
import threads.RoutineCheckerThread;
import java.util.List;

public class Day {
    private Hour[] dayHours = new Hour[24];
    private int actualHour = 0;
    private static final int lastHour = 23;

    private int holidayDayCount = 0;
    private static final int holidayDay = 7;
    private boolean isHolidayDay = false;


    public boolean passTime(List<Restaurant> restaurantList, List<Client> clientList, List<Provider> providerList) {
        dayHours[actualHour].passTime(restaurantList,clientList,providerList);
        actualHour = ++actualHour>lastHour?0 : actualHour;
        isHolidayDay = ++holidayDayCount == holidayDay;
        if(actualHour == 1) receiveClients(clientList);
        return actualHour==0;
    }

    private void receiveClients(List<Client> clientList) {
        RoutineCheckerThread.executeThreads(clientList);
    }

    public void initialize() {
        for (int i = 0; i < dayHours.length; i++) {
            dayHours[i] = new Hour();
        }
    }
}
