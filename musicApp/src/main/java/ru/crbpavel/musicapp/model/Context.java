package ru.crbpavel.musicapp.model;

public class Context {

    private static Context instance;
    private static Object controller;

    private Context() {
    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public static Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        Context.controller = controller;
    }
}
