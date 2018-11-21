package com.t.teamten.greenfoodtracker.resultscreenactivities;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class MealPlan {
    private List<Pair<String, Integer>> lists;

    public MealPlan(List<Pair<String, Integer>> lists){
        this.lists = lists;
    }

    public boolean chickenEmpty(){
        boolean empty = true;
        for(Pair<String, Integer> pair: lists){
            if(pair.first.equals("Chicken")){
                empty = false;
            }
        }
        return empty;
    }

    public boolean vegeEmpty(){
        boolean empty = true;
        for(Pair<String, Integer> pair: lists){
            if(pair.first.equals("Vegetables")){
                empty = false;
                //break;
            }
        }
        return empty;
    }
    public List<Pair<String, Integer>>  meatEaterPlan() {
        List<Pair<String, Integer>> newList = new ArrayList<>();
        int totalMeat = 0;
        for(Pair<String, Integer> pair: lists) {
            if(pair.first.equals("Lamb")||pair.first.equals("Beef")) {
                int add = pair.second/2;
                totalMeat = totalMeat + add;
                newList.add(new Pair<String, Integer>(pair.first,add));
            }
            else if(pair.first.equals("Chicken")){
                int curr = pair.second;
                int add = 0;

                add= totalMeat;
                int addToVege = add;
                int newAmountVege = curr + addToVege;
                newList.add(new Pair<String, Integer>("Chicken",newAmountVege));
            }
            else{
                newList.add(pair);
            }
        }
        if(chickenEmpty()){
            newList.add(new Pair<String, Integer>("Chicken",totalMeat));
        }
        return newList;

    }

    public List<Pair<String, Integer>>  lowMeatPlan() {
        List<Pair<String, Integer>> newList = new ArrayList<>();
        int totalMeat = 0;
        for(Pair<String, Integer> pair: lists) {
            if(pair.first.equals("Lamb")||pair.first.equals("Beef")) {
                int add = pair.second/2;
                totalMeat = totalMeat + add;
                newList.add(new Pair<String, Integer>(pair.first,add));
            }
            else if(pair.first.equals("Vegetables")){
                int curr = pair.second;
                int add = 0;

                add = totalMeat;
                int addToVege = add;
                int newAmountVege = curr + addToVege;
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
