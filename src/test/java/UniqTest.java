import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UniqTest {

    ArrayList<String> inputList;
    ArrayList<String> expectedList;
    ArrayList<String> inputListFlagI;
    ArrayList<String> inputListFlagS;
    ArrayList<String> expectedListFlagS;
    ArrayList<String> inputListFlagC;
    ArrayList<String> expectedListFlagС;
    ArrayList<String> inputListFlagU;
    ArrayList<String> expectedListFlagU;
    ArrayList<String> inputListFlagUI;
    ArrayList<String> expectedListFlagUI;
    ArrayList<String> inputListFlagUIS;
    ArrayList<String> expectedListFlagUIS;

    Uniq uniq = new Uniq();

    @BeforeEach
    void setUp() {

        inputList = new ArrayList<>();
        inputList.add("Two roads diverged in a yellow wood");
        inputList.add("And sorry I could not travel both");
        inputList.add("And be one traveler, long I stood");
        inputList.add("And looked down one as far as I could");
        inputList.add("To where it bent in the undergrowth.");

        expectedList = new ArrayList<>();
        expectedList.add("Two roads diverged in a yellow wood");
        expectedList.add("And sorry I could not travel both");
        expectedList.add("And be one traveler, long I stood");
        expectedList.add("And looked down one as far as I could");
        expectedList.add("To where it bent in the undergrowth.");


        inputListFlagI = new ArrayList<>();
        inputListFlagI.add("Two roads diverged in a yellow wood");
        inputListFlagI.add("And sorry I could not travel both");
        inputListFlagI.add("And be one traveler, long I stood");
        inputListFlagI.add("And be one traveler, long I stood");
        inputListFlagI.add("And be ONE traveler, long I stood");
        inputListFlagI.add("And be ONE traveler, long I stood");
        inputListFlagI.add("And be ONE traveler, long I stood");
        inputListFlagI.add("And looked down one as far as I could");
        inputListFlagI.add("To where it bent in the undergrowth.");
        inputListFlagI.add("To where it bent in the undergrowth.");

        inputListFlagS = new ArrayList<>(); //также включены тесы для [-i]
        inputListFlagS.add("Two roads diverged in a yellow wood");
        inputListFlagS.add("And sorry I could not travel both");
        inputListFlagS.add("And be one traveler, long I stood");
        inputListFlagS.add("qwe be one traveler, long I stood");
        inputListFlagS.add("123 be ONE traveler, long I stood");
        inputListFlagS.add("456 be ONE traveler, long I stood");
        inputListFlagS.add("4567be ONE traveler, long I stood");
        inputListFlagS.add("1234be ONE traveler, long I stood");
        inputListFlagS.add("And be ONE traveler, long I stood");
        inputListFlagS.add("And looked down one as far as I could");
        inputListFlagS.add("To where it bent in the undergrowth.");
        inputListFlagS.add("To where it bent in the undergrowth.");

        expectedListFlagS = new ArrayList<>();
        expectedListFlagS.add("Two roads diverged in a yellow wood");
        expectedListFlagS.add("And sorry I could not travel both");
        expectedListFlagS.add("And be one traveler, long I stood");
        expectedListFlagS.add("123 be ONE traveler, long I stood");
        expectedListFlagS.add("4567be ONE traveler, long I stood");
        expectedListFlagS.add("1234be ONE traveler, long I stood");
        expectedListFlagS.add("And be ONE traveler, long I stood");
        expectedListFlagS.add("And looked down one as far as I could");
        expectedListFlagS.add("To where it bent in the undergrowth.");

        inputListFlagU = new ArrayList<>();
        inputListFlagU.add("Two roads diverged in a yellow wood");
        inputListFlagU.add("And sorry I could not travel both");
        inputListFlagU.add("And be one traveler, long I stood");
        inputListFlagU.add("And looked down one as far as I could");
        inputListFlagU.add("To where it bent in the undergrowth.");

        inputListFlagC = new ArrayList<>();
        inputListFlagC.add("Two roads diverged in a yellow wood");
        inputListFlagC.add("And sorry I could not travel both");
        inputListFlagC.add("And sorry I could not travel both");
        inputListFlagC.add("And sorry I could not travel both");
        inputListFlagC.add("And be one traveler, long I stood");
        inputListFlagC.add("123 be ONE traveler, long I stood");
        inputListFlagC.add("823 be ONE traveler, long I stood");
        inputListFlagC.add("023 be ONE traveler, long I stood");
        inputListFlagC.add("4567be ONE traveler, long I stood");
        inputListFlagC.add("1234be ONE traveler, long I stood");
        inputListFlagC.add("And be ONE traveler, long I stood");
        inputListFlagC.add("And be one traveler, long I stood");
        inputListFlagC.add("And looked down one as far as I could");
        inputListFlagC.add("To where it bent in the undergrowth.");
        inputListFlagC.add("To where it bent in the undergrowth.");
        inputListFlagC.add("To where it bent in the undergrowth.");

        expectedListFlagС = new ArrayList<>();
        expectedListFlagС.add("1 Two roads diverged in a yellow wood");
        expectedListFlagС.add("3 And sorry I could not travel both");
        expectedListFlagС.add("4 And be one traveler, long I stood");
        expectedListFlagС.add("1 4567be ONE traveler, long I stood");
        expectedListFlagС.add("1 1234be ONE traveler, long I stood");
        expectedListFlagС.add("2 And be ONE traveler, long I stood");
        expectedListFlagС.add("1 And looked down one as far as I could");
        expectedListFlagС.add("3 To where it bent in the undergrowth.");

        inputListFlagU = new ArrayList<>();
        inputListFlagU.add("Two roads diverged in a yellow wood");
        inputListFlagU.add("And sorry I could not travel both");
        inputListFlagU.add("And sorry I could not travel both");
        inputListFlagU.add("And looked down one as far as I could");
        inputListFlagU.add("And be one traveler, long I stood");
        inputListFlagU.add("And sorry I could not travel both");
        inputListFlagU.add("And looked down one as far as I could");
        inputListFlagU.add("To where it bent in the undergrowth.");

        expectedListFlagU = new ArrayList<>();
        expectedListFlagU.add("Two roads diverged in a yellow wood");
        expectedListFlagU.add("And be one traveler, long I stood");
        expectedListFlagU.add("To where it bent in the undergrowth.");

        inputListFlagUI = new ArrayList<>();
        inputListFlagUI.add("Two roads diverged in a yellow wood");
        inputListFlagUI.add("And sorry I could not travel both");
        inputListFlagUI.add("And sorry I could not travel both");
        inputListFlagUI.add("And looked down one as far as I could");
        inputListFlagUI.add("And be one traveler, long I stood");
        inputListFlagUI.add("And sorry I could not travel both");
        inputListFlagUI.add("And looked down one as far as I could");
        inputListFlagUI.add("TWO ROADS DIVERGED IN A YELLOW WOOD");
        inputListFlagUI.add("To where it bent in the undergrowth.");

        expectedListFlagUI = new ArrayList<>();
        expectedListFlagUI.add("And be one traveler, long I stood");
        expectedListFlagUI.add("To where it bent in the undergrowth.");

        inputListFlagUIS = new ArrayList<>();
        inputListFlagUIS.add("Two roads diverged in a yellow wood");
        inputListFlagUIS.add("And sorry I could not travel both");
        inputListFlagUIS.add("123 Sorry I Could Not Travel Both");
        inputListFlagUIS.add("And looked down one as far as I could");
        inputListFlagUIS.add("And be one traveler, long I stood");
        inputListFlagUIS.add("456 sorry I could not travel both");
        inputListFlagUIS.add("000 looked down one as far as I could");
        inputListFlagUIS.add("TWO ROADS DIVERGED IN A YELLOW WOOD");
        inputListFlagUIS.add("To where it bent in the undergrowth.");

        expectedListFlagUIS = new ArrayList<>();
        expectedListFlagUIS.add("And be one traveler, long I stood");
        expectedListFlagUIS.add("To where it bent in the undergrowth.");

    }

    @Test
    void unionStrings() {
        //лист, который не нужно исправлять
        assertEquals(expectedList, uniq.unionStrings(inputList, 0, false, false));
        //лист для [-i]
        assertEquals(expectedList, uniq.unionStrings(inputListFlagI, 0, true, false));
        //лист для [-i] + [-s]
        assertEquals(expectedListFlagS, uniq.unionStrings(inputListFlagS, 3, false, false));
        //лист для [-i] + [-s] + [-c]
        assertEquals(expectedListFlagС, uniq.unionStrings(inputListFlagC, 3, true, true));
    }

    @Test
    void intersectsStrings(){
        //лист для [-u]
        assertEquals(expectedListFlagU, uniq.intersectsStrings(inputListFlagU, 0, false));
        //лист для [-u] + [-i]
        assertEquals(expectedListFlagUI, uniq.intersectsStrings(inputListFlagUI, 0, true));
        //лист для [-u] + [-i] + [-s]
        assertEquals(expectedListFlagUIS, uniq.intersectsStrings(inputListFlagUIS, 3, true));
    }
}