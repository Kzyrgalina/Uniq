/**
 * Объединение последовательностей одинаковых идущих подряд строк в файле в одну:
 * file задаёт имя входного файла. Если параметр отсутствует, следует считывать текст с консоли.
 * Флаг -o ofile  задаёт имя выходного файла. Если параметр отсутствует, следует выводить результаты на консоль.
 * Флаг -i означает, что при сравнении строк следует не учитывать регистр символов.
 * Флаг -s N означает, что при сравнении строк следует игнорировать первые N символов каждой строки. Выводить нужно первую строку.
 * Флаг -u означает, что следует выводить в качестве результата только уникальные строки.
 * Флаг -с означает, что перед каждой строкой вывода следует вывести количество строк, которые были заменены данной (т.е. если во входных данных было 2 одинаковые строки, в выходных данных должна быть одна с префиксом “2”).
 * Command line: uniq [-i] [-u] [-c] [-s num] [-o ofile] [file]
 * В случае, когда какое-нибудь из имён файлов указано неверно, следует выдать ошибку.
 * Кроме самой программы, следует написать автоматические тесты к ней.
 **/

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    @Option(name = "-i", metaVar = "IgnoreCase", usage = "Equals with IgnoreCase")
    private boolean i;

    @Option(name = "-o", metaVar = "OutputFile", usage = "name of output file")
    private String outFileName = "";

    @Option(name = "-s", metaVar = "IgnoreNumString", usage = "Ignore begin 'num' string")
    private int sN = 0;

    @Option(name = "-c", metaVar = "CountString", usage = "Write counted string")
    private boolean c;

    @Option(name = "-u", metaVar = "OnlyUnique", usage = "Write only uniques string")
    private boolean u;

    @Argument(metaVar = "InputName", usage = "Input file name")
    private String fileName = "";


    public static Main main1;

    public static void main(String[] args) throws CmdLineException, IOException {
        main1 = new Main();
        main1.launch(args);
    }

    public boolean isI() {
        return main1.i;
    }

    public String getOutFileName() {
        return main1.outFileName;
    }

    public int getsN() {
        return main1.sN;
    }

    public boolean isC() {
        return main1.c;
    }

    public String getFileName() {
        return main1.fileName;
    }

    public boolean isU() {
        return main1.u;
    }

    private void launch(String[] args) throws CmdLineException, IOException {
        CmdLineParser parser = new CmdLineParser(this);
        {
            parser.parseArgument(args);

            //инициализация
            //InputOutputWorker inputOutputWorker = new InputOutputWorker();
            Uniq uniq = new Uniq();

            //ввод данных
            ArrayList<String> inputList;
            if (!fileName.equals("")) {
                FileInputStream fileInputStream = new FileInputStream(new File(fileName));
//                inputList = inputOutputWorker.getStringFromFile(fileName);
                uniq.setInputStream(fileInputStream);
            }

            //вывод данных
            if (!outFileName.equals("")) {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(outFileName));
                uniq.setOutputStream(fileOutputStream);
            }

            //работа программы, соблюдая все флаги
            ArrayList<String> outputList;
            if(u){
                uniq.intersectsStrings(sN, i);
            }else{
                uniq.unionStrings(sN, i, c);
            }
        }
    }
}
