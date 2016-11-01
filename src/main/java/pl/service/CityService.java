package pl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.model.City;
import pl.repository.CityRepository;

import java.util.List;

/**
 * Created by davit on 30.10.2016.
 */

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public void addCity(City city) {
        cityRepository.save(city);
    }

    public void deleteCity(City city) {
        cityRepository.delete(city);
    }

    public void updateCity(City city) {
        if(cityRepository.exists(city.getId()))
            cityRepository.save(city);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
