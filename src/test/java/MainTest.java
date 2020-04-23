import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.*;

class MainTest {

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

}