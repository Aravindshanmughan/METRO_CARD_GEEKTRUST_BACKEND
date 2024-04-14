package com.geektrust.backend.main;

import java.io.IOException;
import com.geektrust.backend.services.MetroStationService;
import org.junit.jupiter.api.Test;

public class MainTest {
    MetroStationService metroStationService = new MetroStationService();

 

    @Test
    public void callStartMethod () throws IOException {
        metroStationService.start(new String[]{"src/input.txt"});
    }
}

