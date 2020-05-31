import Grep.Grep;
import GrepLauncher.*;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class GrepTests {

    ArrayList<String> trueLine = new ArrayList();

    @Test
    public void grepTest() throws IOException {
        ArrayList<String> grepResult =
                new Grep(false, false, false, "раз", "testResources/test1.txt").grep();
        ArrayList<String> trueLine = new ArrayList();
        trueLine.add("Всем привет, ракраззззззз");
        assertEquals(trueLine, grepResult);

        trueLine.clear();
        trueLine.add("Всем привет, раз*");
        grepResult =
                new Grep(false, true, false, "РаЗ*", "testResources/test2.txt").grep();
        assertEquals(trueLine, grepResult);

        trueLine.clear();
        trueLine.add("сколько лет уже прошло!");
        trueLine.add("3542 аааааа,");
        trueLine.add("ПРивЕТ!!!!");
        grepResult =
                new Grep(true, false, false, "привет", "testResources/test3.txt").grep();
        assertEquals(trueLine, grepResult);

        trueLine.clear();
        trueLine.add("разззззззапап");
        grepResult =
                new Grep(false, false, true, "раз*", "testResources/test4.txt").grep();
        assertEquals(trueLine, grepResult);

        trueLine.clear();
        trueLine.add("один");
        grepResult =
                new Grep(true, true, false, "Раз*", "testResources/test5.txt").grep();
        assertEquals(trueLine, grepResult);

        trueLine.clear();
        trueLine.add("one two");
        grepResult =
                new Grep(true, false, true, ".{1,}[f]", "testResources/test6.txt").grep();
        assertEquals(trueLine, grepResult);

        trueLine.clear();
        trueLine.add("хотелость бы ажио купить, но нет");
        trueLine.add("привет");
        grepResult =
                new Grep(true, true, true, "ажиотаж*", "testResources/test7.txt").grep();
        assertEquals(trueLine, grepResult);

        trueLine.clear();
        trueLine.add("FoRtiFication is very bad");
        grepResult =
                new Grep(false, true, true, ".{1,}[f]", "testResources/test8.txt").grep();
        assertEquals(trueLine, grepResult);

    }
}
