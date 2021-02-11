package services;

import com.google.protobuf.Empty;
import io.grpc.example.merchantservice.MerchantServiceGrpc.MerchantServiceImplBase;
import io.grpc.example.merchantservice.MerchantServiceOuterClass;
import io.grpc.example.merchantservice.MerchantServiceOuterClass.IndustryType;
import io.grpc.example.merchantservice.MerchantServiceOuterClass.IndustryTypes;
import io.grpc.stub.StreamObserver;

import java.util.Collection;

public class MerchantService extends MerchantServiceImplBase {

    private Collection<IndustryType> industryTypeList;
    private IndustryTypes industryTypes;

    public MerchantService(Collection<IndustryType> industryTypeList, IndustryTypes industryTypes) {
        this.industryTypeList = industryTypeList;
        this.industryTypes = industryTypes;
    }

    @Override
    public void getMerchantIndustryTypeList(Empty request, StreamObserver<IndustryType> responseObserver) {
        for (IndustryType t : industryTypeList) {
            responseObserver.onNext(t);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getMerchantIndustryTypes(Empty request, StreamObserver<MerchantServiceOuterClass.IndustryTypes> responseObserver) {
        responseObserver.onNext(industryTypes);
        responseObserver.onCompleted();
    }

}
