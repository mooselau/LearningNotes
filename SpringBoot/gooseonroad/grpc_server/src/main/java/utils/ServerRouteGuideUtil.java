package utils;

import com.google.protobuf.util.JsonFormat;
import io.grpc.example.merchantservice.MerchantServiceOuterClass.IndustryType;
import io.grpc.example.merchantservice.MerchantServiceOuterClass.IndustryTypes;
import io.grpc.examples.routeguide.RouteGuideOuterClass;
import io.grpc.examples.routeguide.RouteGuideOuterClass.FeatureDatabase;
import services.MerchantService;
import services.RouteGuideServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public class ServerRouteGuideUtil {

    public static URL getDefaultIndustryTypeFile() {
        return MerchantService.class.getResource("../industry_type_db.json");
    }

    /**
     * Gets the default features file from classpath.
     */
    public static URL getDefaultFeaturesFile() {
        return RouteGuideServer.class.getResource("../route_guide_db.json");
    }

    public static IndustryTypes parseIndustryTypes(URL file) throws IOException {
        try (InputStream input = file.openStream()) {
            try (Reader reader = new InputStreamReader(input, Charset.forName("UTF-8"))) {
                IndustryTypes.Builder industryTypes =  IndustryTypes.newBuilder();
                JsonFormat.parser().merge(reader, industryTypes);
                return industryTypes.build();
            }
        }
    }

    public static List<IndustryType> parseIndustryTypeList(URL file) throws IOException {
        try (InputStream input = file.openStream()) {
            try (Reader reader = new InputStreamReader(input, Charset.forName("UTF-8"))) {
                IndustryTypes.Builder industryTypes =  IndustryTypes.newBuilder();
                JsonFormat.parser().merge(reader, industryTypes);
                return industryTypes.getIndustryTypeList();
            }
        }
    }

    /**
     * Parses the JSON input file containing the list of features.
     */
    public static List<RouteGuideOuterClass.Feature> parseFeatures(URL file) throws IOException {
        InputStream input = file.openStream();
        try {
            Reader reader = new InputStreamReader(input, Charset.forName("UTF-8"));
            try {
                FeatureDatabase.Builder database = FeatureDatabase.newBuilder();
                JsonFormat.parser().merge(reader, database);
                return database.getFeatureList();
            } finally {
                reader.close();
            }
        } finally {
            input.close();
        }
    }
}
