package it.polimi.ingsw.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * This class has the goal to parse json file and import the list of Gods and their descriptions
 *
 * @author cecco
 */
public class ParserJson {

    private ObjectMapper p;
    private final String path = "/god.json";
    private ArrayList<God> usableGod = new ArrayList<>();

    public ParserJson() {
        apriFile();
    }

    public ArrayList<God> getUsableGod() {
        System.out.println(usableGod.size());
        return usableGod;
    }

    public void apriFile() {

        FileLoader fileLoader = new FileLoader();
        ObjectMapper mapper = new ObjectMapper();

        InputStream jsonFile = fileLoader.getResource(path);
        try {
            JsonNode rootNode = mapper.readTree(jsonFile);
            JsonNode god = rootNode.path("God");
            int size = god.size();
            for (int j = 0; j < size; j++) {
                if (god.get(j) != null) {
                    JsonNode singleGod = god.get(j);
                    usableGod.add(importGod(singleGod));
                    System.out.println(usableGod.size());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private God importGod(JsonNode singleGod) {
        JsonNode name = singleGod.path("name");
        JsonNode surname = singleGod.path("surname");
        JsonNode description = singleGod.path("description");
        JsonNode routine = singleGod.path("Routine");

        ArrayList<EffectRoutine> listEff = importRoutine(routine);

        String repName = name.toString().replace("\"", "");
        String repSurname = surname.toString().replace("\"", "");
        String repDecsription = description.toString().replace("\"", "");
        return new God(repName, repDecsription, repSurname, listEff);

    }

    private ArrayList<EffectRoutine> importRoutine(JsonNode routine) {
        ArrayList<EffectRoutine> listEff = new ArrayList<EffectRoutine>();
        for (int i = 0; i < routine.size(); i++) {
            JsonNode eff = routine.get(i);
            JsonNode typeEffect = eff.path("effect");
            JsonNode bool = eff.path("skippable");
            EffectRoutine newEff;

            if (typeEffect.toString().equals("\"filterLevel\"") || typeEffect.toString().equals("\"checkVictory\"")) {
                JsonNode levelsNode = eff.path("levels");
                List<Integer> levels = new ArrayList<>();
                for (int k = 0; k < levelsNode.size(); k++) {
                    levels.add(levelsNode.get(k).asInt());
                }
                newEff = new EffectRoutine(typeEffect.toString().replace("\"", ""), bool.asBoolean(), levels);
            } else {
                newEff = new EffectRoutine(typeEffect.toString().replace("\"", ""), bool.asBoolean());
            }
            listEff.add(newEff);
        }
        return listEff;
    }


    public void printGod(ArrayList<God> usableGod) {
        for (int i = 0; i < usableGod.size(); i++) {
            System.out.println("Nuovo God");
            System.out.println(usableGod.get(i).getName());
            System.out.println(usableGod.get(i).getSurname());
            System.out.println(usableGod.get(i).getTextEffect());
            for (int j = 0; j < usableGod.get(i).getRoutine().size(); j++) {
                System.out.println(usableGod.get(i).getRoutine().get(j).getEffect() + " " + usableGod.get(i).getRoutine().get(j).isSkippable());
                if (usableGod.get(i).getRoutine().get(j).getLevels() != null) {
                    System.out.print("levels :");
                    for (int k = 0; k < usableGod.get(i).getRoutine().get(j).getLevels().size(); k++) {
                        System.out.println(usableGod.get(i).getRoutine().get(j).getLevels().get(k));
                    }
                }
            }

            System.out.println("");
            System.out.println("");
        }
    }
}
