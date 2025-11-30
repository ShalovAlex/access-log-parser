package tests1;

import java.io.BufferedReader;
import java.io.FileReader;

class TooLongLineException extends RuntimeException {
    public TooLongLineException(String message) {
        super(message);
    }
}

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

                if (length > 1024) {
                    throw new TooLongLineException("Обнаружена строка длинной " + length + " " + (lineCount + 1) +
                            ". Максимально допустимая длина: 1024 символа.");
                }

                lineCount ++;
                if (length > maxLength) {
                    maxLength = length;
                }
                if (length < minLength) {
                    minLength = length;
                }
            }

            System.out.println("Общее количество строк в файле: " + lineCount);
            System.out.println("Длина самой длинной строки в файле: " + maxLength);
            System.out.println("Длина самой короткой строки в файле: " + minLength);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public static void main(String[] args) {
        File fileProcessor = new File();
        fileProcessor.readFile("/Users/alex/Desktop/_git/AccessLogParser/src/tests1/test");
    }
}
