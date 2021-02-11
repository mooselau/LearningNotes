package client;

import com.google.common.annotations.VisibleForTesting;
import com.google.protobuf.Empty;
import com.google.protobuf.Message;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.example.merchantservice.MerchantServiceGrpc;
import io.grpc.example.merchantservice.MerchantServiceGrpc.MerchantServiceBlockingStub;
import io.grpc.example.merchantservice.MerchantServiceOuterClass.IndustryType;
import io.grpc.example.merchantservice.MerchantServiceOuterClass.IndustryTypes;
import io.grpc.examples.routeguide.RouteGuideGrpc;
import io.grpc.examples.routeguide.RouteGuideGrpc.RouteGuideBlockingStub;
import io.grpc.examples.routeguide.RouteGuideGrpc.RouteGuideStub;
import io.grpc.examples.routeguide.RouteGuideOuterClass;
import io.grpc.examples.routeguide.RouteGuideOuterClass.Point;
import utils.RouteGuideUtil;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RouteGuideClient {

    private final ManagedChannel channel;
    private final RouteGuideBlockingStub blockingStub;
    private final RouteGuideStub asyncStub;

    private final MerchantServiceBlockingStub mblockingStub;

    private TestHelper testHelper;

    /**
     * Construct client for accessing RouteGuide server at {@code host:port}.
     */
    public RouteGuideClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    /**
     * Construct client for accessing RouteGuide server using the existing channel.
     */
    public RouteGuideClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = RouteGuideGrpc.newBlockingStub(channel);
        asyncStub = RouteGuideGrpc.newStub(channel);
        mblockingStub = MerchantServiceGrpc.newBlockingStub(channel);
    }

    public void getAllIndustryType() {
        long avg = 0L;
        for (int i = 0; i < 1; i++) {
        long duration = way1();
//            long duration = way2();
            avg += duration;
        }
        p(String.format("AVG Time Consuming: %d ms", avg / 10));
    }

    private Empty empty = Empty.newBuilder().build();

    private long way1() {
        p("*****************\n\t use IndustryTypes:");
        long startTime = System.currentTimeMillis();
        IndustryTypes types = mblockingStub.getMerchantIndustryTypes(empty);
        List<IndustryType> list = types.getIndustryTypeList();
        p("Result size: " + list.size());
        for (IndustryType industryType : list) {
            p(String.format("IndustryType - code: %s, name: %s ", industryType.getMccCode(), industryType.getMccName()));
        }
        long endTime = System.currentTimeMillis();
//        p(String.format("Time consuming: %d ms", duration));
        long duration = endTime - startTime;
        return duration;
    }

    private long way2() {
        p("*****************\n\t use Streaming IndustryType:");
        long startTime = System.currentTimeMillis();
        Iterator<IndustryType> industryTypes = mblockingStub.getMerchantIndustryTypeList(empty);
        int count = 0;
        while (industryTypes.hasNext()) {
            IndustryType industryType = industryTypes.next();
            p(String.format("IndustryType - code: %s, name: %s ", industryType.getMccCode(), industryType.getMccName()));
            count ++;
        }
        p("Result size: " + count);
        long endTime = System.currentTimeMillis();
//        p(String.format("Time consuming: %d ms", (endTime - startTime)));
        long duration = endTime - startTime;
        return duration;
    }

    /**
     * Blocking unary call example.  Calls getFeature and prints the response.
     */
    public void getFeature(int lat, int lon) {
        p(String.format("*** GetFeature: lat={0} lon={1}", lat, lon));

        Point request = Point.newBuilder().setLatitude(lat).setLongitude(lon).build();

        RouteGuideOuterClass.Feature feature;
        try {
            feature = blockingStub.getFeature(request);
            if (testHelper != null) {
                testHelper.onMessage(feature);
            }
        } catch (StatusRuntimeException e) {
            p(String.format("RPC failed: {0}", e.getStatus()));
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
            return;
        }
        if (RouteGuideUtil.exists(feature)) {
            p(String.format("Found feature called \"{0}\" at {1}, {2}",
                    feature.getName(),
                    RouteGuideUtil.getLatitude(feature.getLocation()),
                    RouteGuideUtil.getLongitude(feature.getLocation())));
        } else {
            p(String.format("Found no feature at {0}, {1}",
                    RouteGuideUtil.getLatitude(feature.getLocation()),
                    RouteGuideUtil.getLongitude(feature.getLocation())));
        }
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Issues several different requests and then exits.
     */
    public static void main(String[] args) throws InterruptedException {
        RouteGuideClient client = new RouteGuideClient("localhost", 8989);
        try {
            // Looking for a valid feature
//            client.getFeature(409146138, -746188906);
            // Feature missing.
//            client.getFeature(0, 0);
            client.getAllIndustryType();
        } finally {
            client.shutdown();
        }
    }

    private void p(String msg) {
        System.out.println(msg);
    }

    /**
     * Only used for helping unit test.
     */
    @VisibleForTesting
    interface TestHelper {
        /**
         * Used for verify/inspect message received from server.
         */
        void onMessage(Message message);

        /**
         * Used for verify/inspect error received from server.
         */
        void onRpcError(Throwable exception);
    }

}
