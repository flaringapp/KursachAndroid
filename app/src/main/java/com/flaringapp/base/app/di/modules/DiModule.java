package com.flaringapp.base.app.di.modules;

import com.flaringapp.base.app.di.Di;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public abstract class DiModule {

    private final List<Object> singles = new ArrayList<>();
    private final HashMap<Class, Provider<Object>> factories = new HashMap<>();

    public final void init() {
        initSingles();
        initFactories();
    }

    protected void initSingles() {}

    final void addSingle(Object single) {
        singles.add(single);
    }

    protected void initFactories() {}

    final void addFactory(Class targetClass, Provider<Object> factory) {
        factories.put(targetClass, factory);
    }

    public final Object ProvideDependency(Class targetClass, Object... args) {
        for (Object single : singles) {
            if (isClassAssignable(targetClass, single.getClass())) {
                return single;
            }
        }

        for (Map.Entry<Class, Provider<Object>> factory : factories.entrySet()) {
            if (isClassAssignable(targetClass, factory.getKey())) {
                return (factory.getValue().provide(args));
            }
        }

        return null;
    }

    private boolean isClassAssignable(Class target, Class applicant) {
        return target.isAssignableFrom(applicant);
    }

    <T> T get(Class targetClass) {
        return Di.inject(targetClass);
    }

    interface Provider<T> {
        T provide(Object... args);
    }
}
