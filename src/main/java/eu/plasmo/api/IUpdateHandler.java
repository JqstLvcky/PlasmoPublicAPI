package eu.plasmo.api;

import java.util.List;

public interface IUpdateHandler {

    String name();

    boolean update(Update update);

    boolean update(List<Update> updates);

}
