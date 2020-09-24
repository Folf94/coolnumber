package main;

import response.Affair;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private static HashMap<Integer, Affair> affairs = new HashMap<>();
    private static int currentId = 1;

    public static int addAffair(Affair affair) {
        int id = currentId;
        affair.setId(id);
        affairs.put(id, affair);
        currentId++;
        return id;
    }

    public static void getAllAffair() {
        for (Map.Entry<Integer, Affair> affairsMap : affairs.entrySet()) {
            System.out.println("Affair number: " + affairsMap.getKey() + "Affair: " + affairsMap.getValue());
        }
    }

    public static Affair getAffairById(Affair id) {
        if (affairs.containsKey(id)) {
            return affairs.get(id);
        }
        return null;
    }

    public static void deleteAffairById(Affair id) {
        if (affairs.containsKey(id)) {
            affairs.remove(id);
        } else {
            System.out.println("unknown Affair");
        }
    }

    public static void deleteAllAffair() {
        affairs.clear();
    }


    public static boolean updateAffairById(Affair affair, int id) {
        if (affairs.containsKey(id)) {
            affairs.put(id, affair);
            return true;
        }
        return false;
    }
    /*public static boolean updateAffairs(Affair affair) {
        if (affairs.contains(affair)) {
            affair.setName(affair.toString());
            return true;
        }
        return false;
    }*/
}

