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

    public ArrayList<String> getStringFromFile(String path) throws IOException {
        ArrayList<String> outList = new ArrayList<>();
         {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String out;
            while((out = bufferedReader.readLine()) != null){
                outList.add(out);
            }
            bufferedReader.close();
        }
        return outList;
    }


    public void writeListToFile(String path, ArrayList<String> list) throws IOException {
        {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));

            for (String value : list) {
                bufferedWriter.write(value + "\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        }
    }

    public void writeListToConsole(ArrayList<String> list) {
        for (String value : list) {
            System.out.println(value);
        }
    }





}
