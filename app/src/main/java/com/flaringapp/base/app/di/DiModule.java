package com.flaringapp.base.app.di;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public abstract class DiModule {
    private final List<Object> singles;
    private final HashMap<Class, Callable<Object>> factories;

    protected DiModule()
    {
        singles = provideSingles();
        factories = provideFactories();
    }

    protected abstract List<Object> provideSingles();

    protected abstract HashMap<Class, Callable<Object>> provideFactories();

    final Object ProvideDependency(Class targetClass) throws Exception {
        for (Object single : singles) {
            if (single.getClass() == targetClass) {
                return single;
            }
        }

        for (Map.Entry<Class, Callable<Object>> factory : factories.entrySet()) {
            if (factory.getKey() == targetClass || Arrays.asList(targetClass.getInterfaces()).contains(factory.getKey())) {
                return (factory.getValue().call());
            }
        }

        return null;
    }
}
