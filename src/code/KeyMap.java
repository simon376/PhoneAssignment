package code;

import java.util.*;

//TODO: Singleton / static / whatever
public class KeyMap {

    private static final Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

    KeyMap(){
        initializeMap();
    }

    private void initializeMap() {
        map.put("0", new ArrayList<>(Arrays.asList("0", " ")));
        map.put("1", new ArrayList<>(Arrays.asList("1", "@")));
        map.put("2", new ArrayList<>(Arrays.asList("2", "a", "b", "c")));
        map.put("3", new ArrayList<>(Arrays.asList("3", "d", "e", "f")));
        map.put("4", new ArrayList<>(Arrays.asList("4", "g", "h", "i")));
        map.put("5", new ArrayList<>(Arrays.asList("5", "j", "k", "l")));
        map.put("6", new ArrayList<>(Arrays.asList("6", "m", "n", "o")));
        map.put("7", new ArrayList<>(Arrays.asList("7", "p", "q", "r", "s")));
        map.put("8", new ArrayList<>(Arrays.asList("8", "t", "u", "v")));
        map.put("9", new ArrayList<>(Arrays.asList("9", "w", "x", "y", "z")));
    }

    public static String getString(String key, int timesPressed){
        ArrayList<String> list = map.get(key);
        if(list != null){
            return list.get(timesPressed % list.size());
        }
        else
            return "";
    }

}
