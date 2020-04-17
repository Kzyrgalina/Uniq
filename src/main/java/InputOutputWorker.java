import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InputOutputWorker {

    public ArrayList<String> getStringFromConsole(){
        ArrayList<String> outList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String in;
        while(!(in = scanner.nextLine()).equals("")){
            outList.add(in);
        }
        return outList;
    }

    public static ArrayList<String> getStringFromFile(String path) {
        ArrayList<String> outList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String out;
            while((out = bufferedReader.readLine()) != null){
                outList.add(out);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("ошибка при считывании данных из файла " + path);
        }
        return outList;
    }


    public void writeListToFile(String path, ArrayList<String> list) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));

            for (String value : list) {
                bufferedWriter.write(value + "\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("ошибка при записи результата в файл " + path);
        }
    }

    public void writeListToConsole(ArrayList<String> list)  {
        for (String value : list) {
            System.out.println(value);
        }
    }





}
