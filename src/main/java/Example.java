import software.amazon.awssdk.core.async.AsyncRequestProvider;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.utils.FunctionalUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

public class Example {

    private static final String BUCKET = "name";
    private static final String KEY = "name";

    public static void main(String[] args) {
        putObjectInBucket(Paths.get("myfile.in"));

    }

    private static void putObjectInBucket(Path filePath) {
        S3AsyncClient client = S3AsyncClient.create();
        CompletableFuture<PutObjectResponse> future = client.putObject(
                PutObjectRequest.builder()
                        .bucket(BUCKET)
                        .key(KEY)
                        .build(),
                AsyncRequestProvider.fromFile(filePath)
        );
        future.whenComplete((resp, err) -> {
            try {
                if (resp != null) {
                    System.out.println(resp);
                } else {
                    // Handle error
                    err.printStackTrace();
                }
            } finally {
                // Lets the application shut down. Only close the client when you are completely done with it.
                FunctionalUtils.invokeSafely(client::close);
            }
        });
    }

}
