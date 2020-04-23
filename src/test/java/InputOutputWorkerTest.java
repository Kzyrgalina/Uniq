import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputOutputWorkerTest {


    private StringBuilder readFile(String path) {
        File file = new File(path);
        StringBuilder stringBuilderTest = new StringBuilder();
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(file);
            while (fileInput.hasNextLine()) {
                stringBuilderTest.append(fileInput.nextLine()).append("\r\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stringBuilderTest;
    }

    private void writeFile(String path, ArrayList<String> strings){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path));
            for (String s :
                    strings) {
                writer.append(s + "\r\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private InputOutputWorker inputOutputWorker = new InputOutputWorker();
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ArrayList<String> strings;

    private ByteArrayInputStream InputStream = new ByteArrayInputStream((
            "str1" + "\n" +
                    "str1" + "\n" +
                    "str2" + "\n" +
                    "str2" + "\n" +
                    "str3" + "\n" +
                    "" + "\n").getBytes());

    private String expStrings = "str1" + "\r\n" +
            "str1" + "\r\n" +
            "str2" + "\r\n" +
            "str2" + "\r\n" +
            "str3" + "\r\n";


    @BeforeEach
    void setUp() {
        strings = new ArrayList<>();
        strings.add("str1");
        strings.add("str1");
        strings.add("str2");
        strings.add("str2");
        strings.add("str3");
    }

    @Test
    void getStringFromConsole() {
        System.setIn(InputStream);
        assertEquals(strings, inputOutputWorker.getStringFromConsole());
    }

    @Test
    void writeListToConsole() {
        PrintStream printStream = new PrintStream(outContent);
        System.setOut(printStream);
        inputOutputWorker.writeListToConsole(strings);
        assertArrayEquals(expStrings.getBytes(), outContent.toByteArray());
    }

    @Test
    void getStringFromFile() {
        String path = "src\\test\\getStringFromFileTest.txt";
        writeFile(path, strings);
        ArrayList<String> actual = null;
        try {
            actual = inputOutputWorker.getStringFromFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(strings, actual);
    }

    @Test
    void writeListToFile() {
        String path = "src\\test\\writeListToFileTest.txt";
        try {
            inputOutputWorker.writeListToFile(path, strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = readFile(path);
        assertEquals(expStrings, stringBuilder.toString());
    }


}