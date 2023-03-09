package dev.rahulrajesh.PicPayData;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyApplicationTest {

    @Test
    public void testMyService() {
        assertEquals(2,2);
    }
}
