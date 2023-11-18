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
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VietnamLocationsSeeder {
    private static final Logger LOGGER = LogManager.getLogger(VietnamLocationsSeeder.class);
    private final JdbcTemplate jdbcTemplate;
    private final CityRepositoryInterface cityRepositoryInterface;
    private final DistrictRepositoryInterface districtRepositoryInterface;
    private static final String API_URL = "http://provinces.open-api.vn/api/?depth=2";

    @Autowired
    public VietnamLocationsSeeder(
            JdbcTemplate jdbcTemplate,
            CityRepositoryInterface cityRepositoryInterface,
            DistrictRepositoryInterface districtRepositoryInterface
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.cityRepositoryInterface = cityRepositoryInterface;
        this.districtRepositoryInterface = districtRepositoryInterface;
    }

    @EventListener
    public void seeds(ContextRefreshedEvent event) throws SQLException {
        seedVietnamLocationTables();
    }

    public void seedVietnamLocationTables() throws SQLException {
        if (!isCitiesAndDistrictsTablesEmpty()) {
            LOGGER.info("Tables cities and districts are having data. Canceling this seed");

        } else {
            try {
                List<Map<String, Object>> data = fetchVietnamProvincesAPI();
                data.forEach(this::processProvincesData);
                LOGGER.info("Vietnam locations are seeded");

            } catch (IOException e) {
                LOGGER.trace("Vietnam locations are NOT seeded");
                LOGGER.trace(e);
            }
        }
    }

    @Transactional
    private void processProvincesData(Map<String, Object> provinceObj) {
        String divisionType = (String) provinceObj.get("division_type");

        if ("thành phố trung ương".equals(divisionType)) {
            City city = createCityData(provinceObj);
            List<District> districts = createListDistrictsData(provinceObj);

            city.setDistricts(districts);
            this.cityRepositoryInterface.save(city);

        } else {
            Optional.ofNullable(provinceObj.get("districts"))
                    .map(obj -> (List<Map<String, Object>>) obj)
                    .ifPresent(this::processDistrictsData);
        }
    }

    private void processDistrictsData(List<Map<String, Object>> districts) {
        districts.forEach(districtObj -> {
            String districtDivisionType = (String) districtObj.get("division_type");

            if ("thành phố".equals(districtDivisionType)) {
                City city = createCityData(districtObj);

                this.cityRepositoryInterface.save(city);

            } else {
                District district = createDistrictData(districtObj);

                this.districtRepositoryInterface.save(district);
            }
        });
    }

    private City createCityData(Map<String, Object> data) {
        City city = new City();

        city.setName((String) data.get("name"));
        city.setCodename((String) data.get("codename"));

        return city;
    }

    private District createDistrictData(Map<String, Object> data) {
        District district = new District();

        district.setName((String) data.get("name"));
        district.setCodename((String) data.get("codename"));

        return district;
    }

    private List<District> createListDistrictsData(Map<String, Object> data) {
        List<Map<String, Object>> districts = (List<Map<String, Object>>) data.get("districts");

        return districts.stream()
                .map(this::createDistrictData)
                .collect(Collectors.toList());
    }

    private boolean isCitiesAndDistrictsTablesEmpty() {
        String checkCityData = "SELECT * FROM cities LIMIT 1";
        String checkDistrictData = "SELECT * FROM districts LIMIT 1";

        return this.jdbcTemplate.query(checkCityData, (resultSet, rowNum) -> null).isEmpty()
                && this.jdbcTemplate.query(checkDistrictData, (resultSet, rowNum) -> null).isEmpty();
    }

    private List<Map<String, Object>> fetchVietnamProvincesAPI() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

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