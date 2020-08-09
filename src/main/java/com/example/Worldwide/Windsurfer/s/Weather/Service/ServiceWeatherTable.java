package com.example.Worldwide.Windsurfer.s.Weather.Service;

import java.io.IOException;
import java.util.HashMap;

public interface ServiceWeatherTable {
    HashMap getDataFromApi() throws IOException;
}
