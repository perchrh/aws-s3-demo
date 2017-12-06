# S3 Hackathon
S3 capabilities and usage

## Necessary preparations: ##

### 0 Create and configure AWS account ###
The Hackathon admin does this in advance.
After signing up for AWS, create an IAM user with programmatic access and full permissions to S3, using the AWS Console.
Create an encryption key, and give the mentioned user access to use the key.

Download the user's keyfile, containing the access key and the secret access key. Share this file with the participants. Also share the Key ID of the encryption key you created.
There is a guide from Amazon if you need more information, http://docs.aws.amazon.com/java-sdk/latest/developer-guide/setup-credentials.html

### 1 Install AWS command line tools ###

Reference: https://aws.amazon.com/cli/

All systems: Install python3, then use python3's pip to install the 'awscli' package.
Instructions for MacOS:

    brew install python3
    sudo -H pip3 install awscli

### 2 Configure AWS credentials etc ###
Get the access key and the secret access key from the hackathon admin.

Set credentials in the AWS credentials profile file on your local system, located at:
~/.aws/credentials on Linux, macOS, or Unix
C:\Users\USERNAME \.aws\credentials on Windows.

Use the command line tools mentioned in step (1) to write the credentials and configuration. 
Select 'eu-central-1' as the region:  
    
    aws configure

The resulting 'credentials' file should contain lines in the following format:

    [default]
    aws_access_key_id = your_access_key_id
    aws_secret_access_key = your_secret_access_key

Correspondingly, in the same folder, a 'config' file should be created with content like below: 
    
    [default]
    region = eu-central-1 #frankfurt
    
### 3 Test access to AWS through the Java-SDK ###
Run this project's code to test that the access configured in the previous step works. 
Use the 'aws.example.s3.ListBuckets' main method class for testing access. 

## Documentation ##
Official AWS S3 instructions, https://aws.amazon.com/documentation/sdk-for-java/

Youtube playlist, https://www.youtube.com/playlist?list=PL77cy_t1wkZtYlWt26cFH5wWMQPHSGefa

Signing up for AWS and creating an account and a IAM-user, http://docs.aws.amazon.com/sdk-for-java/v2/developer-guide/signup-create-iam-user.html

### V2 API not recommended (yet) ###
While there is a preview-version of the v2 aws Java-SDK, it is not suited for normal use - it's lacking in documentation and in examples. The documentation is short and for preview-1, and preview-5 has API-changes.


### Code examples (v1 api) ###
In addition to the example in this repo, check out 
https://github.com/awsdocs/aws-doc-sdk-examples/tree/master/java/example_code/s3

*There are no code examples for v2 api, only (outdated) snippets*

### Exercise 0 ###
List your S3-buckets. You may receive an empty list. 

### Exercise 1 ###
Create a bucket. Check that it appears when you list buckets.
 
### Exercise 2 ###
Add a (small) file to your bucket. Check that it appears when you list objects for your bucket. 

### Exercise 3 ###
Download an object from your bucket, write it to a local file. 

### Exercise 4 ###
Add one or more tags (key=value) to one or more objects in your bucket. 
List objects that contain a tag with a certain name, and output the value of the tag.

### Exercise 5 ###
Encrypt a file during transfer, for upload and for download.
Hint: See EncryptionEnabledS3Client.java