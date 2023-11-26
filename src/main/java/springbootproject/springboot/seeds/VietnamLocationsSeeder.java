package springbootproject.springboot.seeds;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springbootproject.springboot.contracts.repositories.CityRepositoryInterface;
import springbootproject.springboot.contracts.repositories.DistrictRepositoryInterface;
import springbootproject.springboot.models.City;
import springbootproject.springboot.models.District;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Component
public class VietnamLocationsSeeder {
    private static final Logger LOGGER = LogManager.getLogger(VietnamLocationsSeeder.class);
    private static final String API_URL = "https://provinces.open-api.vn/api/?depth=2";
    private static final String CHECK_CITY_DATA_QUERY = "SELECT * FROM cities LIMIT 1";
    private static final String CHECK_DISTRICT_DATA_QUERY = "SELECT * FROM districts LIMIT 1";

    private final JdbcTemplate jdbcTemplate;
    private final CityRepositoryInterface cityRepositoryInterface;
    private final DistrictRepositoryInterface districtRepositoryInterface;

    @Autowired
    public VietnamLocationsSeeder(JdbcTemplate jdbcTemplate, CityRepositoryInterface cityRepositoryInterface, DistrictRepositoryInterface districtRepositoryInterface) {
        this.jdbcTemplate = jdbcTemplate;
        this.cityRepositoryInterface = cityRepositoryInterface;
        this.districtRepositoryInterface = districtRepositoryInterface;
    }

    @EventListener
    public void seedVietnamLocationTables(ContextRefreshedEvent event) {
        if (isCitiesAndDistrictsTablesEmpty()) {
            try {
                List<Map<String, Object>> data = fetchVietnamProvincesAPI();
                data.forEach(this::processProvincesData);
                LOGGER.info("Vietnam locations are seeded");
            } catch (IOException e) {
                LOGGER.trace("Vietnam locations are NOT seeded");
                LOGGER.trace(e);
            }
        } else {
            LOGGER.info("Tables cities and districts already have data. Cancelling this seed");
        }
    }


    @Transactional
    public void processProvincesData(Map<String, Object> provinceObj) {
        City city = createCityData(provinceObj);
        this.cityRepositoryInterface.save(city);

        List<Map<String, Object>> districts = (List<Map<String, Object>>) provinceObj.get("districts");
        districts
                .stream()
                .map(districtObj -> createDistrictData(districtObj, city))
                .forEach(districtRepositoryInterface::save);
    }


    private City createCityData(Map<String, Object> data) {
        City city = new City();

        city.setName((String) data.get("name"));
        city.setCodename((String) data.get("codename"));

        return city;
    }

    private District createDistrictData(Map<String, Object> data, City city) {
        District district = new District();

        district.setName((String) data.get("name"));
        district.setCodename((String) data.get("codename"));
        district.setCity(city);

        return district;
    }

    private boolean isCitiesAndDistrictsTablesEmpty() {
        return this.jdbcTemplate.query(CHECK_CITY_DATA_QUERY, (resultSet, rowNum) -> null).isEmpty()
                && this.jdbcTemplate.query(CHECK_DISTRICT_DATA_QUERY, (resultSet, rowNum) -> null).isEmpty();
    }

    private List<Map<String, Object>> fetchVietnamProvincesAPI() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(API_URL).build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String jsonData = response.body().string();
            Gson gson = new Gson();
            Type type = new TypeToken<List<Map<String, Object>>>() {
            }.getType();

            return gson.fromJson(jsonData, type);
        }
    }
}