package com.gassion.weather.dto.yandex_api.forecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gassion.weather.dto.ForecastApiResponseDTO;
import com.gassion.weather.dto.yandex_api.forecast.section.CurrentWeather;
import com.gassion.weather.dto.yandex_api.forecast.section.Forecast;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class YandexApiForecastApiResponseDTO extends ForecastApiResponseDTO {

    @JsonProperty("fact")
    private CurrentWeather currentWeather;

    @JsonProperty("forecasts")
    private List<Forecast> forecast;

}
