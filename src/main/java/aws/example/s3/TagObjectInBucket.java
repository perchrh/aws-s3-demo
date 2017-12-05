package aws.example.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Source:
 * http://docs.aws.amazon.com/AmazonS3/latest/dev/tagging-manage-javasdk.html
 * Updated to new client builder API
 */
public class TagObjectInBucket {

    static String bucketName = "***bucket***";
    static String keyName = "***object key name***";
    static String filePath = "***filepath***";

    public static void main(String[] args) {

        final AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

        // 1. Put object with tags.
        PutObjectRequest putRequest = new PutObjectRequest(bucketName, keyName, new File(filePath));
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("Key1", "Value1"));
        tags.add(new Tag("Key2", "Value2"));
        putRequest.setTagging(new ObjectTagging(tags));
        PutObjectResult putResult = s3client.putObject(putRequest);

        // 2. Retrieve object tags.
        GetObjectTaggingRequest getTaggingRequest = new GetObjectTaggingRequest(bucketName, keyName);
        GetObjectTaggingResult getTagsResult = s3client.getObjectTagging(getTaggingRequest);
        for (Tag t : getTagsResult.getTagSet()) {
            System.out.println(String.format("Retrieved tag key=%s value=%s", t.getKey(), t.getValue()));
        }

        // 3. Replace the tagset.
        List<Tag> newTags = new ArrayList<>();
        newTags.add(new Tag("Key3", "Value3"));
        newTags.add(new Tag("Key4", "Value4"));
        s3client.setObjectTagging(new SetObjectTaggingRequest(bucketName, keyName, new ObjectTagging(newTags)));

        // 4. Retrieve object tags again.
        GetObjectTaggingRequest getTaggingRequest2 = new GetObjectTaggingRequest(bucketName, keyName);
        GetObjectTaggingResult getTagsResult2 = s3client.getObjectTagging(getTaggingRequest);
    }
}
