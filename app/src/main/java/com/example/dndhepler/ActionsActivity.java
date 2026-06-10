package com.example.dndhepler;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ActionsActivity extends BaseActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView actionsView = findViewById(R.id.actionsView);

        try {
            FileInputStream fis = openFileInput("Actions5e14.json");
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            String json = new String(bytes);
            JSONArray jsonArray = new JSONArray(json);
            JSONObject obj = jsonArray.getJSONObject(0);
            ArrayList<CategoryActions> actionsArrayList = new ArrayList<>();
            Iterator<String> actionsKeys = obj.keys();
            while (actionsKeys.hasNext()) {
                String categoryKey = actionsKeys.next();
                if (categoryKey.equals("name")) {
                    continue;
                }
                JSONObject categoryObject = obj.getJSONObject(categoryKey);
                String categoryName = categoryObject.getString("name");
                String categoryDescription = categoryObject.optString("description", "");
                actionsArrayList.add(new CategoryActions(CategoryActions.TYPE_CATEGORY, categoryName, categoryDescription, null));
                Iterator<String> categoryKeys = categoryObject.keys();
                while (categoryKeys.hasNext()) {
                    String actionsKey = categoryKeys.next();
                    if (actionsKey.equals("name") || actionsKey.equals("description")) {
                        continue;
                    }
                    JSONObject actionObject = categoryObject.getJSONObject(actionsKey);
                    String name = actionObject.getString("name");
                    String description = actionObject.getString("description");
                    Actions actions = new Actions(name,
                            description);
                    actionsArrayList.add(new CategoryActions(CategoryActions.TYPE_ACTIONS, null, null, actions));
                }
            }
            actionsView.setLayoutManager(new LinearLayoutManager(this));
            ActionsAdapter actionsAdapter = new ActionsAdapter(actionsArrayList);
            actionsView.setAdapter(actionsAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}