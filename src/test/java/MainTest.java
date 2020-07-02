import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kohsuke.args4j.CmdLineException;
import java.io.*;
import java.util.Scanner;

class MainTest {
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
    private void writeFile(String path, String strings){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path));
            for (String s :
                    strings.split("\r\n")) {
                writer.append(s + "\r\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private ByteArrayInputStream InputStream = new ByteArrayInputStream((
                    "str1" + "\n" +
                    "str1" + "\n" +
                    "str2" + "\n" +
                    "str2" + "\n" +
                    "str3" + "\n" +
                    "" + "\n").getBytes());

    private String strings1 =
            "str1" + "\r\n" +
                    "str1" + "\r\n" +
                    "str2" + "\r\n" +
                    "str2" + "\r\n" +
                    "str3" + "\r\n";
    private String expStrings1 =
            "str1" + "\r\n" +
                    "str2" + "\r\n" +
                    "str3" + "\r\n";
    private String strings2 =
            "str1" + "\n" +
                    "STR1" + "\n" +
                    "STR2" + "\n" +
                    "str2" + "\n" +
                    "str3" + "\n";
    private String expStrings2 =
            "str1" + "\n" +
                    "STR2" + "\n" +
                    "str3" + "\n";
    private ByteArrayInputStream InputStream3 = new ByteArrayInputStream((
            "qwertystr1" + "\n" +
                    "QWERTYSTR1" + "\n" +
                    "123456STR2" + "\n" +
                    "456789str2" + "\n" +
                    "++++++str3" + "\n").getBytes());
    private String expStrings3 =
            "qwertystr1" + "\r\n" +
                    "123456STR2" + "\r\n" +
                    "++++++str3" + "\r\n";
    private ByteArrayInputStream InputStream4 = new ByteArrayInputStream((
            "qwertystr1" + "\n" +
                    "QWERTYSTR1" + "\n" +
                    "QWErtySTR1" + "\n" +
                    "123456STR2" + "\n" +
                    "456789str2" + "\n" +
                    "++++++str3" + "\n").getBytes());
    private String expStrings4 =
            "3 qwertystr1" + "\n" +
                    "2 123456STR2" + "\n" +
                    "1 ++++++str3" + "\n";
    private String strings5 =
            "str1" + "\n" +
                    "str1" + "\n" +
                    "str2" + "\n" +
                    "str2" + "\n" +
                    "str3" + "\n";
    private String expStrings5 = "str3" + "\n";
    private ByteArrayInputStream InputStream6 = new ByteArrayInputStream((
            "qwertystr1" + "\n" +
                    "QWERTYSTR1" + "\n" +
                    "123456STR2" + "\n" +
                    "456789str2" + "\n" +
                    "++++++str3" + "\n").getBytes());
    private String expStrings6 =
            "123456STR2" + "\r\n" +
                    "456789str2" + "\r\n" +
                    "++++++str3" + "\r\n";
    private String strings7 =
            "qwertystr1" + "\r\n" +
                    "QWERTYstr1" + "\r\n" +
                    "1234567str2" + "\r\n" +
                    "2345678str2" + "\r\n" +
                    "++++++str3" + "\r\n";
    private String expStrings7 =
            "1234567str2" + "\r\n" +
                    "2345678str2" + "\r\n" +
                    "++++++str3" + "\r\n";


    //выбрасывание исключений при неправильных аргументах командной строки
    @Test
    void CmdLineException() {
        Assertions.assertThrows(org.kohsuke.args4j.CmdLineException.class, () -> Main.main(new String[]{"-f"}));
        Assertions.assertThrows(org.kohsuke.args4j.CmdLineException.class, () -> Main.main(new String[]{"-s -o"}));
    }

    //выбрасывание исключений методами считывания и записывания
    @Test
    void FileNotFoundException() {
        Assertions.assertThrows(FileNotFoundException.class, () -> Main.main(new String[]{"qwerty.txt"}));
    }
    @Test
    void FileToFile() throws IOException, CmdLineException {
        writeFile("src\\test\\getStringFromFileTest.txt", strings1);
        Main.main(new String[]{"src\\test\\getStringFromFileTest.txt", "-o", "src\\test\\writeListToFileTest.txt"});
        Assertions.assertEquals(expStrings1, readFile(Main.main1.getOutFileName()).toString());
    }

    @Test
    void FileToCmdWithI() throws IOException, CmdLineException {
        writeFile("src\\test\\getStringFromFileTest.txt", expStrings2);
        PrintStream printStream = new PrintStream(outContent);
        System.setOut(printStream);
        Main.main(new String[]{"-i", "src\\test\\getStringFromFileTest.txt"});
        Assertions.assertEquals(expStrings2, outContent.toString());
    }

    @Test
    void CmdToFileWithIS() throws IOException, CmdLineException{
        System.setIn(InputStream3);
        Main.main(new String[]{"-i", "-s", "6" ,"-o","src\\test\\writeListToFileTest.txt"});
        Assertions.assertEquals(expStrings3, readFile(Main.main1.getOutFileName()).toString());
    }

    @Test
    void CmdToCmdWithISC() throws IOException, CmdLineException {
        PrintStream printStream = new PrintStream(outContent);
        System.setOut(printStream);
        System.setIn(InputStream4);
        Main.main(new String[]{"-i", "-s", "6", "-c"});
        Assertions.assertEquals(expStrings4, outContent.toString());
    }

    @Test
    void FileToCmdWithU() throws IOException, CmdLineException {
        writeFile("src\\test\\getStringFromFileTest.txt", expStrings5);
        PrintStream printStream = new PrintStream(outContent);
        System.setOut(printStream);
        Main.main(new String[]{"-u", "src\\test\\getStringFromFileTest.txt"});
        Assertions.assertEquals(expStrings5, outContent.toString());
    }

    @Test
    void CmdToFileWithUI() throws IOException, CmdLineException {
        System.setIn(InputStream6);
        Main.main(new String[]{"-i","-u","-o","src\\test\\writeListToFileTest.txt"});
        Assertions.assertEquals(expStrings6, readFile(Main.main1.getOutFileName()).toString());
    }

    @Test
    void FileToFileWithUIS() throws IOException, CmdLineException {
        writeFile("src\\test\\getStringFromFileTest.txt", strings7);
        Main.main(new String[]{"src\\test\\getStringFromFileTest.txt", "-i","-u","-s","6","-o",
                "src\\test\\writeListToFileTest.txt"});
        Assertions.assertEquals(expStrings7, readFile(Main.main1.getOutFileName()).toString());
    }
}