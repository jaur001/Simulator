package threads.initializers.provider;

import implementations.loaders.provider.product.DistributionProductPriceInitializer;
import implementations.loaders.provider.product.RandomProductInitializer;
import model.provider.Provider;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ProductInitializerThread extends Thread{
    private final Provider provider;

    private ProductInitializerThread(Provider provider) {
        this.provider = provider;
    }


    @Override
    public void run(){
        provider.setProduct(new RandomProductInitializer().init());
        provider.setProductPrice(new DistributionProductPriceInitializer().getPrice(provider.getProduct()));
    }


    public static void executeThreads(List<Provider> providerList){
        ProductInitializerThread[] ProductInitializerThreadsList = getThreads(providerList);
        startThreads(ProductInitializerThreadsList);
        joinThreads(ProductInitializerThreadsList);
    }

    private static ProductInitializerThread[] getThreads(List<Provider> providerList){
        return providerList.stream()
                .map(ProductInitializerThread::new)
                .toArray(ProductInitializerThread[]::new);
    }

    private static void startThreads(ProductInitializerThread[] threads) {
        IntStream.range(0,threads.length)
                .forEach(pos -> threads[pos].start());
    }

    private static void joinThreads(ProductInitializerThread[] threads) {
        Arrays.stream(threads).forEach(ProductInitializerThread::joinThread);
    }

    private static void joinThread(ProductInitializerThread thread){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
