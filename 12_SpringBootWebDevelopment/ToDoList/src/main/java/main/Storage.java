package main;

import main.model.Affair;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {
    private static Map<Integer, Affair> affairs = Collections.synchronizedMap(new HashMap<>());
    private static AtomicInteger currentId = new AtomicInteger(0);

    public static int addAffair(Affair affair) {
        int id = currentId.incrementAndGet();
        affair.setId(id);
        affairs.put(id, affair);
        return id;
    }

    public static void getAllAffair() {
        for (Map.Entry<Integer, Affair> affairsMap : affairs.entrySet()) {
            System.out.println("Affair number: " + affairsMap.getKey() + "Affair: " + affairsMap.getValue());
        }
    }

    public static Affair getAffairById(Integer id) {
        if (affairs.containsKey(id)) {
            return affairs.get(id);
        }
        return null;
    }

    public static void deleteAffairById(Integer id) {
        if (affairs.containsKey(id)) {
            affairs.remove(id);
        } else {
            System.out.println("unknown Affair");
        }
    }

    public static void deleteAllAffair() {
        affairs.clear();
    }


    public static boolean updateAffairById(Affair affair, Integer id) {
        if (affairs.containsKey(id)) {
            affairs.put(id, affair);
            return true;
        }
        return false;
    }

    public static boolean updateAffairs(List<Affair> affairList) {
        affairList.forEach(affair -> {
            if (affairs.containsKey(affair.getId())) {
                affairs.put(affair.getId(), affair);
            }
        });

        return true;
    }
}

