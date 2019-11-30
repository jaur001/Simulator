package main;

import org.apache.commons.math3.distribution.NormalDistribution;

public class Main3 {
    public static void main(String[] args) {
        int minPrice = 1;
        int maxPrice = 3;
        double mean = 2;
        double sd = Math.sqrt((Math.pow(Math.abs(minPrice-mean),2)+Math.pow(Math.abs(maxPrice-mean),2)+
                Math.pow(Math.abs(minPrice-mean),2)+Math.pow(Math.abs(2-mean),2)+
                Math.pow(Math.abs(2-mean),2)+Math.pow(Math.abs(2-mean),2))/6);
        System.out.println(sd);
        //System.out.println(Math.round(Math.abs(new NormalDistribution(0,1).sample()))+1);




    }
}
