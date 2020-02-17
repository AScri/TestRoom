package com.ascri.testroom;

import com.google.gson.Gson;

import java.io.IOException;

public class NameRepo {
    private final FileReader fileReader;

    public NameRepo(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public String getName() throws IOException {
        Gson gson = new Gson();
        User user = gson.fromJson(fileReader.readFile(), User.class);
        return user.name;
    }

    private static final class User {
        String name;
    }
}
