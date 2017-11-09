package com.example.vikta.smartwarmup;

import java.util.ArrayList;

public class Weight {

    private int mWeight;
    private int mSet;
    private String mNumSets;

    public Weight(){

    }

    public static void multiplyArray(double mainWeight, ArrayList<Integer> percentsWeight,
                                     String exercise){
        if (mainWeight<=100){
            for (int nomerWesa=1; nomerWesa<percentsWeight.size();nomerWesa++){
                double calcWeight=percentsWeight.get(nomerWesa)*mainWeight;
                int roundNumb=(int) Math.round((calcWeight / 100)/5)*5;
                percentsWeight.set(nomerWesa, roundNumb);
            }
        } if (mainWeight>100&&mainWeight<=150){
            for (int nomerWesa=1; nomerWesa<percentsWeight.size();nomerWesa++){
                double calcWeight=percentsWeight.get(nomerWesa)*mainWeight;
                if (nomerWesa==percentsWeight.size()-1){
                    int roundNumb=(int) Math.round((calcWeight / 100)/5)*5;
                    percentsWeight.set(nomerWesa,roundNumb);
                }else{
                    int roundNumb=(int) Math.round((calcWeight / 100)/10)*10;
                    percentsWeight.set(nomerWesa,roundNumb);
                }
            }
        }
        switch (exercise) {
            case "bench":
                if (mainWeight > 150) {
                    for (int nomerWesa = 1; nomerWesa < percentsWeight.size(); nomerWesa++) {
                        double calcWeight = percentsWeight.get(nomerWesa) * mainWeight;
                        if (nomerWesa == percentsWeight.size() - 1) {
                            int roundNumb = (int) Math.round((calcWeight / 100) / 5) * 5;
                            percentsWeight.set(nomerWesa, roundNumb);
                        } else {
                            int roundNumb = (int) Math.round((calcWeight / 100) / 10) * 10;
                            percentsWeight.set(nomerWesa, roundNumb);
                        }
                    }
                }
                break;

            default:
                if (mainWeight > 150) {
                    for (int nomerWesa = 0; nomerWesa < percentsWeight.size(); nomerWesa++) {
                        double calcWeight = percentsWeight.get(nomerWesa) * mainWeight;
                        if (nomerWesa == percentsWeight.size() - 1) {
                            int roundNumb = (int) Math.round((calcWeight / 100) / 5) * 5;
                            percentsWeight.set(nomerWesa, roundNumb);
                        } else {
                            int roundNumb = (int) Math.round((calcWeight / 100) / 10) * 10;
                            percentsWeight.set(nomerWesa, roundNumb);
                        }
                    }
                }
        }

    }

    public static ArrayList<Integer> calcWarmUp(Double mainWeight,
                                                ArrayList<Integer> percentsWeight,
                                                String exercise){
        ArrayList<Integer> result=new ArrayList<>(percentsWeight);
        multiplyArray(mainWeight,result,exercise);
        return result;
    }

    public int getWeight() {
        return mWeight;
    }

    public void setWeight(int weight) {
        mWeight = weight;
    }

    public int getSet() {
        return mSet;
    }

    public void setSet(int set) {
        mSet = set;
    }

    public String getNumSets() {
        return mNumSets;
    }

    public void setNumSets(String numSets) {
        mNumSets = numSets;
    }
}