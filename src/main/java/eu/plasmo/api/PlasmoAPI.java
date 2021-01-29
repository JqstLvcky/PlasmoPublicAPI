package eu.plasmo.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.pippo.core.Pippo;

public class PlasmoAPI {

    public static Logger LOGGER = Logger.getLogger("PlasmoAPI");
    public static List<IUpdateHandler> HANDLERS = new ArrayList<>();

    private static PlasmoAPI instance;

    private Pippo pippo;

    private PlasmoAPI(int port) {

        LOGGER.log(Level.FINE, "Initializing PlasmoAPI!");

        this.pippo = new Pippo(new Listener());
        this.pippo.start(port);

        LOGGER.log(Level.FINE, "Success.");

    }

    public static PlasmoAPI initialize(int port) {

        instance = new PlasmoAPI(port);
        return instance;

    }

    public static PlasmoAPI getInstance() {

        return instance;

    }

    public static boolean register(IUpdateHandler updateHandler) {

        return HANDLERS.add(updateHandler);

    }

    public static boolean unregister(IUpdateHandler updateHandler) {

        return HANDLERS.remove(updateHandler);

    }

    public static boolean unregister(String name) {

        for (IUpdateHandler handler : HANDLERS) {

            if (handler.name().equalsIgnoreCase(name))
                return HANDLERS.remove(handler);

        }

        return false;

    }

}
