package it.polimi.ingsw.ParserServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.model.Color;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BuilderEvent {

    public String builder(Event event) {
        ObjectMapper mapper = new ObjectMapper();
        String attributes = null;
        String type = selectType(event);
        String json;
        try {
            if (type.equals("ChooseTarget")) {
                attributes = typeTarget(event);
            } else if (type.equals("ChooseWorker")) {
                attributes = typeWorker(event);
            } else if (type.equals("UpdateEvent")) {
                attributes = typeUpdate(event);
            } else if (type.equals("StartMatchEvent")) {
                attributes = typeStartMatch(event);
            } else if (type.equals("SetWorkerEvent")) {
                attributes = typeSetWorker(event);
            } else if (!type.equals("askUser") && !type.equals("ConnectionSuccessful") && !type.equals("LogoutSuccessful") && !type.equals("SettingsEvent") && !type.equals("Pong"))
                attributes = mapper.writeValueAsString(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
        json = buildjsonString(attributes, type);
        return json;
    }

    public String selectType(Event event) {
        String s = null;
        if (event instanceof askUser) {
            s = "askUser";
        } else if (event instanceof ChooseTarget) {
            s = "ChooseTarget";
        } else if (event instanceof ChooseWorker) {
            s = "ChooseWorker";
        } else if (event instanceof ChooseYourGodEvent) {
            s = "ChooseYourGodEvent";
        } else if (event instanceof ConnectionSuccessful) {
            s = "ConnectionSuccessful";
        } else if (event instanceof DeathPlayer) {
            s = "DeathPlayer";
        } else if (event instanceof EndGame) {
            s = "EndGame";
        } else if (event instanceof ExceptionEvent) {
            s = "ExceptionEvent";
        } else if (event instanceof LoginSuccessful) {
            s = "LoginSuccessful";
        } else if (event instanceof LogoutSuccessful) {
            s = "LogoutSuccessful";
        } else if (event instanceof SettingsEvent) {
            s = "SettingsEvent";
        } else if (event instanceof SetWorkerEvent) {
            s = "SetWorkerEvent";
        } else if (event instanceof StartGameEvent) {
            s = "StartGameEvent";
        } else if (event instanceof UpdateEvent) {
            s = "UpdateEvent";
        } else if (event instanceof StartMatchEvent) {
            s = "StartMatchEvent";
        }else if (event instanceof WaitYourGodEvent) {
            s = "WaitYourGodEvent";
        }else if (event instanceof Pong) {
            s = "Pong";
        }
        return s;
    }

    private String typeWorker(Event event) {
        ChooseWorker w1 = (ChooseWorker) event;
        String attributes;
        attributes = "{\"S\":[" + buildSquareToJson(w1.getPosWorker(), w1.getPosWorker().size()) + "],";
        attributes = attributes + buildMap(((ChooseWorker) event).getSquares()) + "}";
        return attributes;
    }

    private String typeTarget(Event event) {
        ChooseTarget t1 = (ChooseTarget) event;
        String attributes;
        attributes = "{\"message\":\"" + t1.getMessage() + "\"," + "\"S\":[";
        attributes = attributes + buildSquareToJson(t1.getAvailableSquare(), t1.getAvailableSquare().size());
        attributes = attributes + "]," + buildMap(t1.getSquares()) + "}";
        return attributes;
    }

    private String typeStartMatch(Event event) {
        StartMatchEvent s1 = (StartMatchEvent) event;
        LinkedHashMap<String, String> godPlayer = s1.getGodPlayer();
        String attributes;
        attributes = "{\"linking\":[";
        int k = 0;
        for (Map.Entry<String, String> entry : godPlayer.entrySet()) {
            if (k == (godPlayer.size() - 1)) {
                attributes = attributes + "{\"nickname\":\"" + entry.getKey() + "\",\"godName\":\"" + entry.getValue() + "\"}";
            } else {
                attributes = attributes + "{\"nickname\":\"" + entry.getKey() + "\",\"godName\":\"" + entry.getValue() + "\"},";
            }
            k++;
        }
        attributes = attributes + "],"+buildMap(s1.getMappa())+"}";
        return attributes;
    }


    private String typeUpdate(Event event) {
        UpdateEvent u1 = (UpdateEvent) event;
        SquareToJson[][] map = u1.getSquares();
        String attributes;
        attributes = "{" + buildMap(map) + "}";
        return attributes;
    }

    private String typeSetWorker(Event event) {
        SetWorkerEvent s1 = (SetWorkerEvent) event;
        String attributes;
        attributes = "{\"S\":[" + buildSquareToJson(s1.getAvailableSquares(), s1.getAvailableSquares().size()) + "],";
        attributes = attributes + buildMap(s1.getMap()) + "}";
        return attributes;
    }


    private String buildjsonString(String attributes, String type) {
        String json;
        if (attributes == null)
            json = "{" + "\"eventName\":\"" + type + "\"}";
        else {
            json = "{" + "\"eventName\":\"" + type + "\"," + "\"attributes\":" + attributes + "}";
        }
        return json;

    }


    private String buildSquareToJson(List<SquareToJson> list, int size) {
        String attributes = "";
        for (int i = 0; i < list.size(); i++) {
            int level = list.get(i).getLevels();
            Color c = list.get(i).getColor();
            String col = "null";
            if (c != null) {
                col = c.toString();
            }
            int x = list.get(i).getCordinataX();
            int y = list.get(i).getCordinataY();
            if (i == size - 1) {
                attributes = attributes + "[" + level + ",\"" + col + "\"," + x + "," + y + "]";
            } else {
                attributes = attributes + "[" + level + ",\"" + col + "\"," + x + "," + y + "],";
            }
        }
        return attributes;
    }

    private String buildMap(SquareToJson[][] map) {
        String attributes = null;
        attributes = "\"squares\":[";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int levels = map[i][j].getLevels();
                Color c = map[i][j].getColor();
                if (i == 4 && j == 4) {
                    if (c != null) {
                        attributes = attributes + "{\"cordinataX\":" + i + ",\"cordinataY\":" + j + ",\"levels\":" + levels + ",\"color\":\"" + c + "\"}";
                    } else {
                        attributes = attributes + "{\"cordinataX\":" + i + ",\"cordinataY\":" + j + ",\"levels\":" + levels + ",\"color\":\"null\"}";
                    }
                } else if (c != null) {
                    attributes = attributes + "{\"cordinataX\":" + i + ",\"cordinataY\":" + j + ",\"levels\":" + levels + ",\"color\":\"" + c + "\"},";
                } else {
                    attributes = attributes + "{\"cordinataX\":" + i + ",\"cordinataY\":" + j + ",\"levels\":" + levels + ",\"color\":\"null\"},";
                }
            }
        }
        attributes = attributes + "]";
        return attributes;
    }
}
