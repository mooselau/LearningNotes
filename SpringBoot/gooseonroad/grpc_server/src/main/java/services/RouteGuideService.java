package services;

import io.grpc.examples.routeguide.RouteGuideGrpc;
import io.grpc.examples.routeguide.RouteGuideOuterClass;
import io.grpc.examples.routeguide.RouteGuideOuterClass.Feature;
import io.grpc.examples.routeguide.RouteGuideOuterClass.Point;
import io.grpc.stub.StreamObserver;

import java.util.Collection;

public class RouteGuideService extends RouteGuideGrpc.RouteGuideImplBase {

    private final Collection<Feature> features;

    public RouteGuideService(Collection<Feature> features) {
        this.features = features;
    }

    @Override
    public void getFeature(RouteGuideOuterClass.Point request, StreamObserver<RouteGuideOuterClass.Feature> responseObserver) {
        responseObserver.onNext(checkFeature(request));
        responseObserver.onCompleted();
    }

    private Feature checkFeature(Point location) {
        for (Feature feature : features) {
            if (feature.getLocation().getLatitude() == location.getLatitude()
                    && feature.getLocation().getLongitude() == location.getLongitude()) {
                return feature;
            }
        }
        // No feature was found, return an unnamed feature.
        return Feature.newBuilder().setName("").setLocation(location).build();
    }
}
