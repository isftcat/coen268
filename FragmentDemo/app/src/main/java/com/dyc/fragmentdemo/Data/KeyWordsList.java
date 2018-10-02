package com.dyc.fragmentdemo.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2018/2/12.
 */

public class KeyWordsList {

    private static final List<String> AnimalList = new ArrayList<String>(){{
        add("Dog");
        add("Cat");
        add("Tiger");
        add("Deer");
    }};

    private static final List<String> DogList = new ArrayList<String>(){{
        add("Afghan Hound");
        add("Airedale Terrier");
        add("Alaskan Malamute");
        add("Beagle");
    }};

    private static final List<String> CatList = new ArrayList<String>(){{
        add("American Wirehair");
        add("Birman");
        add("Himalayan");
        add("Ragdoll");
    }};

    private static final List<String> TigerList = new ArrayList<String>(){{
        add("Siberian Tiger");
        add("South China Tiger");
        add("Sumatran Tiger");
        add("Malayan Tiger");
    }};

    private static final List<String> DeerList = new ArrayList<String>(){{
        add("Fallow Deer");
        add("Red Deer");
        add("Water Deer");
        add("Sika Deer");
    }};

    private static final List<String> DogUrls = new ArrayList<String>(){{
        add("https://en.wikipedia.org/wiki/Afghan_Hound");
        add("https://en.wikipedia.org/wiki/Airedale_Terrier");
        add("https://en.wikipedia.org/wiki/Alaskan_Malamute");
        add("https://en.wikipedia.org/wiki/Beagle");
    }};

    private static final List<String> CatUrls = new ArrayList<String>(){{
        add("https://en.wikipedia.org/wiki/American_Wirehair");
        add("https://en.wikipedia.org/wiki/Birman");
        add("https://en.wikipedia.org/wiki/Himalayan_cat");
        add("https://en.wikipedia.org/wiki/Ragdoll");
    }};

    private static final List<String> TigerUrls = new ArrayList<String>(){{
        add("https://en.wikipedia.org/wiki/Siberian_tiger");
        add("https://en.wikipedia.org/wiki/South_China_tiger");
        add("https://en.wikipedia.org/wiki/Sumatran_tiger");
        add("https://en.wikipedia.org/wiki/Malayan_tiger");
    }};

    private static final List<String> DeerUrls = new ArrayList<String>(){{
        add("https://en.wikipedia.org/wiki/Fallow_deer");
        add("https://en.wikipedia.org/wiki/Red_deer");
        add("https://en.wikipedia.org/wiki/Water_deer");
        add("https://en.wikipedia.org/wiki/Sika_deer");
    }};

    public static List<String> getAnimals(){
        return AnimalList;
    }

    public static List<String> getDogs(){
        return DogList;
    }

    public static List<String> getCats(){
        return CatList;
    }

    public static List<String> getTigers(){
        return TigerList;
    }

    public static List<String> getDeers(){
        return DeerList;
    }

    public static List<String> getSubList(int i){
        switch(i){
            case 0:
                return DogList;
            case 1:
                return CatList;
            case 2:
                return TigerList;
            case 3:
                return DeerList;
        }
        return AnimalList;
    }

    public static String getUrls(int i, int j){
        switch(i){
            case 0:
                return DogUrls.get(j);
            case 1:
                return CatUrls.get(j);
            case 2:
                return TigerUrls.get(j);
            case 3:
                return DeerUrls.get(j);
        }
        return DogUrls.get(0);
    }


}
