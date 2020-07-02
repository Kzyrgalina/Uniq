import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Uniq {

    InputStream inputStream;
    OutputStream outputStream;

    public Uniq() {
        inputStream = System.in;
        outputStream = System.out;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void unionStrings(int ignore, boolean ignoreCase, boolean countFlag) throws IOException { // ignore = num
        ArrayList<String> inputList = getStrings();
        ArrayList<String> outList = new ArrayList<>();
        String t = inputList.get(0);    // t = строка с которой мы сравниваем.
        int count = 0; //флаг подсчета количества строк
        for (int j = 1; j < inputList.size(); j++) {
            String str1 = inputList.get(j).substring(ignore);
            String str2 = t.substring(ignore);
            count++;
            //проверяем флаг игнорирования регистра букв
            if (ignoreCase) {//если флаг установлен, то проверяем с игнорированием
                if (!str1.equalsIgnoreCase(str2)) {
                    //также при сравнении мы сразу берём подстроку с индекса ignore
                    // для того, чтобы игнорировать первые ignore символов
                    outList.add(countFlag ? count + " " + t : t);
                    t = inputList.get(j);
                    count = 0;
                }
            } else {//иначе проверяем на точное совпадение
                if (!str1.equals(str2)) {
                    //также при сравнении мы сразу берём подстроку с индекса ignore
                    // для того, чтобы игнорировать первые ignore символов
                    outList.add(countFlag ? count + " " + t : t);
                    t = inputList.get(j);
                    count = 0;
                }
            }
        }
        count++;
        outList.add(countFlag ? count + " " + t : t);

        writeStrings(outList);
    }

    public void intersectsStrings(int ignore, boolean ignoreCase) throws IOException {
        ArrayList<String> list = getStrings();
        ArrayList<String> delete = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; i++) {
            for (int d = i + 1; d < list.size(); d++) {
                String sub1 = list.get(i).substring(ignore);
                String sub2 = list.get(d).substring(ignore);
                if (ignoreCase) {
                    if (sub1.equalsIgnoreCase(sub2)) {
                        delete.add(list.get(i));
                        delete.add(list.get(d));
                    }
                } else {
                    if (sub1.equals(sub2)) {
                        delete.add(list.get(i));
                        delete.add(list.get(d));
                    }
                }
            }
        }
        for (String str : delete) {
            list.remove(str);
        }
        writeStrings(list);
    }

    public ArrayList<String> getStrings() {
        ArrayList<String> outList = new ArrayList<>();
        Scanner scanner = new Scanner(inputStream);
        String s;
        while (scanner.hasNextLine() && !(s = scanner.nextLine()).isEmpty()) {
            outList.add(s);
        }
        return outList;
    }


    public void writeStrings(ArrayList<String> strings) throws IOException {
        OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
        for (String value : strings) {
            streamWriter.write(value + "\n");
        }
        streamWriter.flush();
        streamWriter.close();
    }
}



