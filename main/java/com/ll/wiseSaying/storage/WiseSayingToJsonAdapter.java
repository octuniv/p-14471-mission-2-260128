package com.ll.wiseSaying.storage;

import com.ll.wiseSaying.entity.WiseSaying;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WiseSayingToJsonAdapter {
    private static WiseSayingToJsonAdapter instance;
    private static final String folderAddress = "/Users/apple/data";
    private static final String filePath = folderAddress + "/data.json";
    private static final Pattern idPattern = Pattern.compile("^id: (\\d+),?$");
    private static final Pattern wisePattern = Pattern.compile("^content: \"(.+)\",?$");
    private static final Pattern authorPattern = Pattern.compile("^author: \"(.+)\",?$");
    private static final Pattern isDeletePattern = Pattern.compile("^deleted: (true|false),?$");

    private WiseSayingToJsonAdapter() {
        try {
            Path directoryPath = Paths.get(folderAddress);
            Files.createDirectories(directoryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static WiseSayingToJsonAdapter getInstance() {
        if (instance == null) {
            instance = new WiseSayingToJsonAdapter();
        }
        return instance;
    }

    public void writeToFile(List<WiseSaying> wiseList) {
        String saved = wiseListToStringJSON(wiseList);

        try {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }

            if (wiseList.isEmpty()) {
                return;
            }

            BufferedWriter fw = new BufferedWriter(new FileWriter((filePath)));
            fw.write(saved);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String wiseListToStringJSON(List<WiseSaying> wiseList) {
        StringBuilder contents = new StringBuilder();
        String space = " ";
        contents.append("[").append("\n");
        for (WiseSaying wise: wiseList) {
            contents.append(space).append("{").append("\n");

            contents.append(space).append(space).append("id: ").append(wise.getId()).append(",").append("\n");
            contents.append(space).append(space).append("content: ")
                    .append("\"").append(wise.getWise()).append("\"").append(",").append("\n");
            contents.append(space).append(space).append("author: ")
                    .append("\"").append(wise.getAuthor()).append("\"").append(",").append("\n");
            contents.append(space).append(space).append("deleted: ").append(wise.getDeleted()).append("\n");

            if (wise.getId() == WiseSaying.getNextId() - 1) {
                contents.append(space).append("}").append("\n");
            }
            else {
                contents.append(space).append("}").append(",").append("\n");
            }
        }
        contents.append("]").append("\n");
        return contents.toString();
    }

    public ArrayList<WiseSaying> readFromFile() {
        List<String> list = null;

        try{
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }

            Charset cs = StandardCharsets.UTF_8;
            list = Files.readAllLines(Paths.get(filePath), cs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(list == null) return new ArrayList<>();

        return this.stringJSONToWiseList(list.stream().reduce("", (total, str) -> total + str + "\n" ));
    }

    private ArrayList<WiseSaying> stringJSONToWiseList (String contents) {
        return Arrays.stream(contents.split("}"))
                .filter(s-> !s.contains("]"))
                .map(this::stringToWise)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private WiseSaying stringToWise (String content) {
        WiseSaying wise = new WiseSaying("", "");

        Arrays.stream(content.split("\n"))
                .map(String::strip)
                .forEach(s -> {
                    Matcher idMatcher = idPattern.matcher(s);
                    Matcher wiseMatcher = wisePattern.matcher(s);
                    Matcher authorMatcher = authorPattern.matcher(s);
                    Matcher deletedMatcher = isDeletePattern.matcher(s);

                    if (idMatcher.find()) {
                        wise.setId(Integer.parseInt(idMatcher.group(1)));
                    } else if (wiseMatcher.find()) {
                        wise.setWise(wiseMatcher.group(1));
                    } else if (authorMatcher.find()) {
                        wise.setAuthor(authorMatcher.group(1));
                    } else if (deletedMatcher.find()) {
                        wise.setDeleted(Boolean.parseBoolean(deletedMatcher.group(1)));
                    }
                });

        if (wise.getWise().isEmpty() || wise.getAuthor().isEmpty()) {
            WiseSaying.undoNextId();
            return null;
        }

        return wise;
    }


}
