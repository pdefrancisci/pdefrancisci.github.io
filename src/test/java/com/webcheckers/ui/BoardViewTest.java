package com.webcheckers.ui;

import com.webcheckers.model.Board;
import com.webcheckers.util.Color;
import com.webcheckers.model.Row;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("UI-tier")
public class BoardViewTest {

    @Test
    public void testIterator(){
        Board b = new Board();
        BoardView bv = new BoardView(b, Color.RED);
        Iterator<Row> r = bv.iterator();
        while(r.hasNext()){
            assertNotNull(r.next());
        }
        BoardView bvWhite = new BoardView(b, Color.WHITE);
        Iterator<Row> rWhite = bvWhite.iterator();
        while(rWhite.hasNext()){
            assertNotNull(rWhite.next());
        }

    }


}
