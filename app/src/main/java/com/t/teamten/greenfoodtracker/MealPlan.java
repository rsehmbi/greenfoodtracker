package com.t.teamten.greenfoodtracker;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class MealPlan {
    private List<Pair<String, Integer>> lists;

    public MealPlan(List<Pair<String, Integer>> userList){
        this.lists = userList;
    }

    public boolean chickenEmpty(){
        boolean noChicken = true;
        for(Pair<String, Integer> pair: lists){
            if(pair.first.equals("Chicken")){
                noChicken = false;
                break;
            }
        }
        return noChicken;
    }

    public boolean vegeEmpty(){
        boolean empty = true;
        for(Pair<String, Integer> pair: lists){
            if(pair.first.equals("Vegetables")){
                empty = false;
                break;
            }
        }
        return empty;
    }
    public List<Pair<String, Integer>>  meatEaterPlan() {
        boolean lamb = false;
        int addToChickenWithLamb = 0;
        int addToEmptyChicken = 0;
        List<Pair<String, Integer>> newList = new ArrayList<>();
        for(Pair<String, Integer> pair: lists) {
            if(pair.first.equals("Lamb")){
                int newAmountLamb = pair.second/2;
                addToChickenWithLamb = newAmountLamb;
                newList.add(new Pair<String, Integer>("Lamb",newAmountLamb));
                lamb = true;
            }
            else if(pair.first.equals("Chicken")){
                int currChicken = pair.second;
                int addLamb = 0;
                if(lamb==true){
                    addLamb = addToChickenWithLamb;
                }
                int addToChicken = addLamb;
                int newAmountChicken = currChicken + addToChicken;
                newList.add(new Pair<String, Integer>("Chicken",newAmountChicken));
            }

            else{
                newList.add(pair);
            }


        }
        //if user does not eat chicken
        if(chickenEmpty()) {
            addToEmptyChicken = addToChickenWithLamb ;
            newList.add(new Pair<String, Integer>("Chicken",addToEmptyChicken));
        }

        return newList;

    }

    public List<Pair<String, Integer>>  lowMeatPlan() {
        boolean lamb = false;
        int addToVegeWithLamb = 0;
        int addToEmptyVege = 0;
        List<Pair<String, Integer>> newList = new ArrayList<>();
        for(Pair<String, Integer> pair: lists) {
            if(pair.first.equals("Lamb")){
                int newAmountLamb = pair.second/2;
                addToVegeWithLamb = newAmountLamb;
                newList.add(new Pair<String, Integer>("Lamb",newAmountLamb));
                lamb = true;
            }
            else if(pair.first.equals("Vegetables")){
                int currVege = pair.second;
                int addLamb = 0;
                if(lamb==true){
                    addLamb = addToVegeWithLamb;
                }
                int addToVege = addLamb;
                int newAmountVege = currVege + addToVege;
                newList.add(new Pair<String, Integer>("Vegetables",newAmountVege));
            }
            else{
                newList.add(pair);
            }
        }
        if(vegeEmpty()){
            addToEmptyVege= addToVegeWithLamb ;
            newList.add(new Pair<String, Integer>("Vegetables",addToEmptyVege));
        }

        return newList;

    }

    public List<Pair<String, Integer>>  veggOnlyPlan() {

        List<Pair<String, Integer>> newList = new ArrayList<>();
        int totalMeat = 0;
        for(Pair<String, Integer> pair: lists) {
            if(pair.first.equals("Lamb")||pair.first.equals("Beef")||
                    pair.first.equals("Chicken")||pair.first.equals("Pork")
                    ||pair.first.equals("Fish")) {
                int add = pair.second;
                totalMeat = totalMeat + add;
            }
            else if(pair.first.equals("Vegetables")){
                int currVege = pair.second;
                int addVege = 0;

                addVege = totalMeat;
                int addToVege = addVege;
                int newAmountVege = currVege + addToVege;
                newList.add(new Pair<String, Integer>("Vegetables",newAmountVege));
            }
            else{
                newList.add(pair);
            }
        }
        if(vegeEmpty()){
            newList.add(new Pair<String, Integer>("Vegetables",totalMeat));
        }
        return newList;

    }


}
