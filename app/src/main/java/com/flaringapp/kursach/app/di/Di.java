package com.flaringapp.kursach.app.di;

import androidx.annotation.NonNull;

import com.flaringapp.kursach.app.di.exceptions.MissingDependencyException;
import com.flaringapp.kursach.app.di.modules.DataModule;
import com.flaringapp.kursach.app.di.modules.DiModule;
import com.flaringapp.kursach.app.di.modules.PresentationModule;

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

    @NonNull
    public static <T> T inject(Class targetClass, Object... args) {
        for (DiModule module : modules) {
            Object dependency = module.ProvideDependency(targetClass, args);
            if (dependency != null) return (T) dependency;
        }
        throw new MissingDependencyException(targetClass.getName());
    }
}
