package com.flaringapp.base.app.di;

import com.flaringapp.base.app.di.exceptions.MissingDependencyException;
import com.flaringapp.base.app.di.modules.DataModule;
import com.flaringapp.base.app.di.modules.PresentationModule;

import java.util.Arrays;
import java.util.List;

public final class Di {

    private static List<DiModule> modules = Arrays.asList(
            new DataModule(),
            new PresentationModule()
    );

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
