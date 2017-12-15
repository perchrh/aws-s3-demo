# S3 Hackathon
S3 capabilities and usage

## Necessary preparations: ##

### 0 Create and configure AWS account ###
The Hackathon admin does this in advance.
After signing up for AWS, create an IAM user with programmatic access and the security policies of full access to S3 and Cloudtrail, using the AWS Console.
See 'Signing up for AWS and creating an account and a IAM-user', at http://docs.aws.amazon.com/sdk-for-java/v2/developer-guide/signup-create-iam-user.html
Create an encryption key, using default settings, and give the mentioned user usage access to the key.

Download the user's keyfile, containing the access key and the secret access key. Share this file with the participants. Also share the Key ID of the encryption key you created.
There is a guide from Amazon if you need more information, http://docs.aws.amazon.com/java-sdk/latest/developer-guide/setup-credentials.html

### 1 Install AWS command line tools ###

Reference: https://aws.amazon.com/cli/

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
Try this on the command line to see if the previous step worked:

     aws s3 ls

Run this project's code to test that the access configured in the previous step works. 
Use the 'aws.example.s3.ListBuckets' main method class for testing access. 

## Documentation ##
Official AWS S3 documentation, https://aws.amazon.com/documentation/s3/

Youtube playlist, https://www.youtube.com/playlist?list=PL77cy_t1wkZtYlWt26cFH5wWMQPHSGefa

### V2 API not recommended (yet) ###
While there is a preview-version of the v2 aws Java-SDK, it is not suited for normal use - it's lacking in documentation and in examples. The documentation is short and for preview-1, and preview-5 has API-changes.


### Code examples (v1 api) ###
In addition to the example in this repo, check out 
https://github.com/awsdocs/aws-doc-sdk-examples/tree/master/java/example_code/s3

*There are no code examples for v2 api, only (outdated) snippets*

### Amazon cloud basic concepts ### 
S3 is one of Amazon Cloud's storage solutions. 
Amazon Cloud is called AWS. 

A **bucket** is a container for objects stored in Amazon S3. Every object is contained in a bucket. 

An **object** in this context can be thought of as a file. 

Inside a bucket, objects are named using **keys**. A key can be photos/2017/july/DCP_123123.jpg. 
Every key must be unique, and it refers to a single object. 
Note that you can put separators like '/' in a key to group keys, similar to a folder structure on a PC.
For more information about objects, including metadata (S3-provided and user-provided), see http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingObjects.html

A bucket is located in a geographical **region**. There are mechanisms to mirror buckets to other regions and so (Cross region replication).
A region is for example EU (Frankfurt). When you put data in a bucket, you are guaranteed that Amazon will not store the data elsewhere than the bucket's region, 
if you do not explicitly enable mirroring or similar features. 

**Bucket policies** provide centralized access control to buckets and objects based on a variety of conditions, including Amazon S3 operatons, 
requesters, resources, and aspects of the request (e.g. IP address). 
For example, a user can be given access to a particular bucket, from the corporate network only, during business hours only, and through HTTPS only. 

Data can be encrypted at rest (server-side) and/or in-flight (upload or download time). The AWS user can provide the encryption key to use. 
See http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingEncryption.html

**CloudTrail** is an AWS service that can be used to log access and usage of S3, for example for audit log purposes. 

A bucket can be configured with a **deletion policy**, a custom set of rules that say when to delete objects, for example based on age. 

Objects in S3 can be given **tags**. A tag is a key-value pair, for example project=Indigo. 
See http://docs.aws.amazon.com/AmazonS3/latest/dev/object-tagging.html 

**Versioning** enables you to keep multiple versions of an object in one bucket, for example my-image.jpg (version 1) and 
my-image.jpg (version 2). You might want to enable versioning to protect yourself from unintended overwriten and deletions or to archive objects 
so that you can retrieve previous versions of them. Versioning is a feature enabled per bucket. 

**Cloudfront** is Amazon's content delivery network, that can be used for caching frequently used objects near the users' location. 
There is a Cloudfront cache location in Stockholm, for example. 

**Cloudwatch** can collect statistics etc on S3 usage. 

The AWS **Storage gateway** can be used to copy files to S3 using normal means, for example through an NFS interface, VTL og iSCSI. 

A bucket can be configured for **S3 infrequent access**, reducing costs, suitable for example for certain archive solutions.  

AWS Identity and Access Management (**IAM**) enables you to securely control access to AWS services and resources for your users. Using IAM, you can create and manage AWS users and groups, and use permissions to allow and deny their access to AWS resources. 

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
Requires "Java Cryptography Extension (JCE)" installed if Using Oracle JDK (included in OpenJDK). 
Get it from http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html

Put the new jar files in the JRE's security folder, for example /Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/security

Hint: you can list your AWS hosted keys using 'aws kms list-keys'