package GrepTests;

import GrepLauncher.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GrepTests {

    private PrintStream stream;
    private ArgumentCaptor<String> captor;

    public void setStream() {
        stream = mock(PrintStream.class);
        captor = ArgumentCaptor.forClass(String.class);
        System.setOut(stream);
    }

    @Test
    public void grepTest() {

        String some = "";
        boolean first = true;

        GrepLauncher grep = new GrepLauncher();
        setStream();
        GrepLauncher.main("вступай test/test.txt".split(" "));
        verify(stream).println(captor.capture());
        assertEquals(captor.getValue(), "Сколько миль ещё? Перелет короткий был не в счёт, вступай в Профсоюз!");

        setStream();
        GrepLauncher.main("-i вступай test/test.txt".split(" "));
        verify(stream, times(2)).println(captor.capture());
        some = "";
        for (String cap : captor.getAllValues()) {
            if (first) {
                first = false;
                some = cap;
            } else some += "\n" + cap;
        }
        assertEquals(some,
                "Сколько миль ещё? Перелет короткий был не в счёт, вступай в Профсоюз!\n" +
                        "Вступай в Профсоюз!");

        setStream();
        GrepLauncher.main("-v вступай test/test.txt".split(" "));
        verify(stream, times(4)).println(captor.capture());
        some = "";
        first = true;
        for (String cap : captor.getAllValues()) {
            if (first) {
                first = false;
                some = cap;
            } else some += "\n" + cap;
        }
        assertEquals(some,
                "Дон ли, Волга ли течёт; котомку – на плечо. Не ключом\n" +
                        "Боль в груди – там тайничок, открытый фомкой, не ключом\n" +
                        "Долгий пыльный чёс, фургон набит коробками с мерчём\n" +
                        "Вступай в Профсоюз!");

        setStream();
        GrepLauncher.main(new String[]{"-r", "не ключом", "test/test.txt"});
        verify(stream).println(captor.capture());
        assertEquals(captor.getValue(), "Боль в груди – там тайничок, открытый фомкой, не ключом");

        setStream();
        GrepLauncher.main("-v -i профсоюз test/test.txt".split(" "));
        verify(stream, times(3)).println(captor.capture());
        some = "";
        first = true;
        for (String cap : captor.getAllValues()) {
            if (first) {
                first = false;
                some = cap;
            } else some += "\n" + cap;
        }
        assertEquals(some,
                "Дон ли, Волга ли течёт; котомку – на плечо. Не ключом\n" +
                        "Боль в груди – там тайничок, открытый фомкой, не ключом\n" +
                        "Долгий пыльный чёс, фургон набит коробками с мерчём");

        setStream();
        GrepLauncher.main(new String[]{"-v", "-i", "-r", "Не ключом", "test/test.txt"});
        verify(stream, times(3)).println(captor.capture());
        some = "";
        first = true;
        for (String cap : captor.getAllValues()) {
            if (first) {
                first = false;
                some = cap;
            } else some += "\n" + cap;
        }
        assertEquals(some,
                "Сколько миль ещё? Перелет короткий был не в счёт, вступай в Профсоюз!\n" +
                        "Долгий пыльный чёс, фургон набит коробками с мерчём\n" +
                        "Вступай в Профсоюз!");

        setStream();
        GrepLauncher.main(new String[]{"-v", "-r", "Боль в груди", "test/test.txt"});
        verify(stream, times(4)).println(captor.capture());
        some = "";
        first = true;
        for (String cap : captor.getAllValues()) {
            if (first) {
                first = false;
                some = cap;
            } else some += "\n" + cap;
        }
        assertEquals(some,
                "Дон ли, Волга ли течёт; котомку – на плечо. Не ключом\n" +
                        "Сколько миль ещё? Перелет короткий был не в счёт, вступай в Профсоюз!\n" +
                        "Долгий пыльный чёс, фургон набит коробками с мерчём\n" +
                        "Вступай в Профсоюз!");

        setStream();
        GrepLauncher.main(new String[]{"-i", "-r", "ФУРГОН набиТ", "test/test.txt"});
        verify(stream).println(captor.capture());
        assertEquals(captor.getValue(), "Долгий пыльный чёс, фургон набит коробками с мерчём");

    }
}
