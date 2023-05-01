package com.duc.ptc.core.utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private Map<String, Object> scenarioContext;

    public ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public void setContext(String key, Object obj) {
        scenarioContext.put(key, obj);
    }

    public Object getContext(String key) {
        return scenarioContext.get(key);
    }

    public boolean isContainKey(String key) {
        return scenarioContext.containsKey(key);
    }
}
