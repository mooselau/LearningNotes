package services;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

import static utils.ServerRouteGuideUtil.getDefaultFeaturesFile;
import static utils.ServerRouteGuideUtil.getDefaultIndustryTypeFile;
import static utils.ServerRouteGuideUtil.parseFeatures;
import static utils.ServerRouteGuideUtil.parseIndustryTypeList;
import static utils.ServerRouteGuideUtil.parseIndustryTypes;

public class RouteGuideServer {

    private int port;
    private Server server;

    public RouteGuideServer(int port) throws IOException {
        this.port = port;
        this.server = ServerBuilder.forPort(port).addService(new RouteGuideService(
                parseFeatures(getDefaultFeaturesFile())))
                .addService(new MerchantService(parseIndustryTypeList(getDefaultIndustryTypeFile()),
                        parseIndustryTypes(getDefaultIndustryTypeFile()))).build();
    }

    public void start() throws IOException {
        server.start();
        p("Server started, listening on: " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                RouteGuideServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        RouteGuideServer server = new RouteGuideServer(8989);
        server.start();
        server.blockUntilShutdown();
    }

    private void p(String msg) {
        System.out.println(msg);
    }

}
