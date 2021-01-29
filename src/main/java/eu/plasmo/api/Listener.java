package eu.plasmo.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import ro.pippo.core.Application;

import java.util.ArrayList;
import java.util.List;

public class Listener extends Application {

    public Listener() {

        POST("/info", (routeContext) -> {

            try {

                JSONTokener tokener = new JSONTokener(routeContext.text().getRequest().getBody());
                JSONArray array = new JSONArray(tokener);

                if (array.length() == 1) {

                    JSONObject object = array.optJSONObject(0);
                    Update update = new Update(object.optString("name"), Rank.get(object.optString("old")), Rank.get(object.optString("rank")));

                    boolean drop = false;

                    for (IUpdateHandler handler : PlasmoAPI.HANDLERS) {

                        if (drop)
                            break;

                        drop = handler.update(update);

                    }


                } else if (array.length() > 1) {

                    List<Update> updates = new ArrayList<>();

                    for (int index = 0, length = array.length(); index < length; index++) {

                        JSONObject object = array.optJSONObject(index);

                        if (object.optString("name") != null)
                            updates.add(new Update(object.optString("name"), Rank.get(object.optString("old")), Rank.get(object.optString("rank"))));

                    }

                    boolean drop = false;

                    for (IUpdateHandler handler : PlasmoAPI.HANDLERS) {

                        if (drop)
                            break;

                        drop = handler.update(updates);

                    }

                } else throw new IllegalArgumentException("Array is empty.");

            } catch (IllegalArgumentException exception) {

                routeContext.send("Array is empty.");
                exception.printStackTrace();

            } catch (JSONException exception) {

                routeContext.send("Error occurred with try create JSONArray.");
                exception.printStackTrace();

            } catch (Throwable throwable) {

                routeContext.send("Error (" + throwable.getClass() + ").");
                throwable.printStackTrace();

            }

        });

        GET(".*", routeContext -> routeContext.send("404 not found. This server only for post requests."));

    }

}
