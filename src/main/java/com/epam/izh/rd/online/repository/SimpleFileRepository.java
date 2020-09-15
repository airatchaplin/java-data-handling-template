package com.epam.izh.rd.online.repository;

import java.io.*;

public class SimpleFileRepository implements FileRepository {
    final String resourcesPath = "src/main/resources/";

    private long countFiles = 0L;
    private long countDirs = 0L;


    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {

        File pathFile = new File(path);
        if (!pathFile.exists()) {
            pathFile = new File(resourcesPath + path);
        }
        File[] files = pathFile.listFiles();

        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                countFilesInDirectory(file.getPath());
            } else countFiles++;
        }
        return countFiles;

    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {

        File pathFile = new File(path);
        if (!pathFile.exists()) {
            pathFile = new File(resourcesPath + path);
            countDirs++;

        }
        File[] files = pathFile.listFiles();
        assert files != null;
        for (File s : files) {
            if (s.isDirectory()) {
                countDirs++;
                countDirsInDirectory(s.getPath());
            }
        }
        return countDirs;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {

    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {
        File filePath = new File(path);
        if (!filePath.exists()){
            filePath.mkdir();
        }
        File file = new File(path + "/" + name);
        if (!file.exists()){
            try {
                file.createNewFile();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {

        String text = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/" + fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            text = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return text;
    }
}