package aws.example.s3;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Encryption;
import com.amazonaws.services.s3.AmazonS3EncryptionClientBuilder;
import com.amazonaws.services.s3.model.CryptoConfiguration;
import com.amazonaws.services.s3.model.CryptoMode;
import com.amazonaws.services.s3.model.KMSEncryptionMaterialsProvider;

import java.security.NoSuchAlgorithmException;

public class EncryptionEnabledS3Client {
    private static final String BUCKET_NAME = ""; //add your bucket name
    private static final String ENCRYPTED_KEY = "enc-key"; // dummy key name
    private static final Regions CURRENT_REGION = Regions.EU_CENTRAL_1;
    private static final String HOSTED_KEY_ID = ""; // add the ID of a customer managed key you have access to

    public static void main(String[] args) {
        EncryptionEnabledS3Client encrypt = new EncryptionEnabledS3Client();

        try {
            encrypt.encryptionOnly_KmsManagedKey();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This uses the V2 metadata schema with a key wrap algorithm of 'kms' and a CEK algorithm of AES/CBC/PKCS5Padding.
     */
    private void encryptionOnly_KmsManagedKey() throws NoSuchAlgorithmException {
        AmazonS3Encryption encryptionEnabledClient = AmazonS3EncryptionClientBuilder
                .standard()
                .withRegion(CURRENT_REGION)
                .withCryptoConfiguration(new CryptoConfiguration(CryptoMode.EncryptionOnly))
                // Can either be Key ID or alias (prefixed with 'alias/')
                .withEncryptionMaterials(new KMSEncryptionMaterialsProvider(HOSTED_KEY_ID))
                .build();

        // Upload the object, encrypting it during transfer
        String objectContent = "some contents";
        encryptionEnabledClient.putObject(BUCKET_NAME, ENCRYPTED_KEY, objectContent);

        // Download the object, decrypting it after transfer
        String decryptedObject = encryptionEnabledClient.getObjectAsString(BUCKET_NAME, ENCRYPTED_KEY);
        System.out.println(decryptedObject);
    }

}
