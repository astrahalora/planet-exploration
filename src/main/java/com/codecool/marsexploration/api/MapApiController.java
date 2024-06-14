package com.codecool.marsexploration.api;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.provider.MapConfigurationProvider;
import com.codecool.marsexploration.configuration.service.MapConfigurationValidator;
import com.codecool.marsexploration.configuration.service.MapConfigurationValidatorImpl;
import com.codecool.marsexploration.mapelements.model.MapModel;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilderImpl;
import com.codecool.marsexploration.mapelements.service.generator.MapElementsGenerator;
import com.codecool.marsexploration.mapelements.service.generator.MapElementsGeneratorImpl;
import com.codecool.marsexploration.mapelements.service.generator.MapGenerator;
import com.codecool.marsexploration.mapelements.service.generator.MapGeneratorImpl;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacer;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacerImpl;
import com.codecool.marsexploration.output.service.MapFileWriter;
import com.codecool.marsexploration.output.service.MapFileWriterImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MapApiController {
    MapConfiguration mapConfig = MapConfigurationProvider.getConfiguration();
    MapConfigurationValidator validator = new MapConfigurationValidatorImpl();
    DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();
    CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(mapConfig);
    MapElementBuilder mapElementFactory = new MapElementBuilderImpl(dimensionCalculator);
    MapElementsGenerator mapElementsGenerator = new MapElementsGeneratorImpl(mapElementFactory);
    MapElementPlacer mapElementPlacer = new MapElementPlacerImpl(coordinateCalculator);
    MapGenerator mapGenerator = new MapGeneratorImpl(mapElementsGenerator, mapElementPlacer, coordinateCalculator);
    MapFileWriter mapFileWriter = new MapFileWriterImpl();

    private String generateStringMapAndWriteMapToFile() {
        if(validator.validate(mapConfig)) {
            MapModel newMap = mapGenerator.generate(mapConfig);
            mapFileWriter.writeMapFile(newMap, "src/main/resources/exploration-map-latest.map");
            return newMap.toString();
        }
        return Arrays.deepToString(mapGenerator.createEmptyMapRepresentation(32, 32));
    }

    @GetMapping("/api/map")
    @ResponseBody
    public Map<String, String> getMap() {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("stringMap", generateStringMapAndWriteMapToFile());
        return response;
    }
}

