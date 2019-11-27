package com.flaringapp.base.app.di;

import com.flaringapp.base.app.di.exceptions.MissingDependencyException;
import com.flaringapp.base.app.di.modules.DataModule;
import com.flaringapp.base.app.di.modules.DiModule;
import com.flaringapp.base.app.di.modules.PresentationModule;

import java.util.ArrayList;
import java.util.List;

public final class Di {

    private static List<DiModule> modules = new ArrayList<>();

    public static void initModules() {
        modules.add(new DataModule());
        modules.add(new PresentationModule());

        for (DiModule module : modules) {
            module.init();
        }
    }

    public static <T> T inject(Class targetClass) {
        try {
            for (DiModule module : modules) {
                Object dependency = module.ProvideDependency(targetClass);
                if (dependency != null) return (T) dependency;
            }
            throw new MissingDependencyException(targetClass.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
