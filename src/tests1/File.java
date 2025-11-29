package tests1;

import java.io.BufferedReader;
import java.io.FileReader;

class File {
    public void readFile(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            int lineCount = 0;
            int maxLength = 0;
            int minLength = Integer.MAX_VALUE;

            while ((line = reader.readLine()) != null) {
                int length = line.length();
                lineCount++;
                if (length > maxLength) {
                    maxLength = length;
                }
                if (length < minLength) {
                    minLength = length;
                }
            }

            System.out.println("Общее количество строк: " + lineCount);
            System.out.println("Длина самой длинной строки: " + maxLength);
            System.out.println("Длина самой короткой строки: " + minLength);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
