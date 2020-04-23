import java.util.ArrayList;

public class Uniq {
    /**
     * @param inputList  список считанных строк
     * @param ignore     количество игнорируемых символов [-s Num]
     * @param ignoreCase [-i]
     * @param countFlag  [-c]
     * @return список уникальных строк с учетом всех установленных флагов, кроме [-u]
     */
    public ArrayList<String> unionStrings(ArrayList<String> inputList, int ignore, boolean ignoreCase, boolean countFlag) { // ignore = num
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

        return outList;
    }

    /**
     * @param list список считанных строк из которого удаляем повторяющиеся
     * @param ignore количество игнорируемых символов [-s Num]
     * @param ignoreCase [-i]
     * @return список уникальных строк
     */
    // метод исключительно для флага [-u] (принцип работы аналогичен unionStrings())
    public ArrayList<String> intersectsStrings(ArrayList<String> list, int ignore, boolean ignoreCase) {
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
        for (String str: delete) {
            list.remove(str);
        }
        return list;
        }
    }



