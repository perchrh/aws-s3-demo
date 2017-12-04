# aws-s3-demo
Eika Hackathon

## Necessary preparations: ##


### 1 Installing AWS command line tools ###

Ref https://aws.amazon.com/cli/
$ brew install python3 pip3
$ sudo -H pip3 install awscli
$ aws configure # med Security Credentials (=access keys)

### 2 Configure AWS credentials etc ###
Get the access key and the secret access key. 

Set credentials in the AWS credentials profile file on your local system, located at:
~/.aws/credentials on Linux, macOS, or Unix
C:\Users\USERNAME \.aws\credentials on Windows
This file should contain lines in the following format:

    [default]
    aws_access_key_id = your_access_key_id
    aws_secret_access_key = your_secret_access_key

Correspondingly, in the same folder, create a file called "config" with the following contents:
    
    [default]
    region = eu-central-1 #frankfurt


## Documentation ##
Official AWS S3 instructions, https://aws.amazon.com/documentation/sdk-for-java/

Youtube playlist, https://www.youtube.com/playlist?list=PL77cy_t1wkZtYlWt26cFH5wWMQPHSGefa

Signing up for AWS and creating an account and a IAM-user, http://docs.aws.amazon.com/sdk-for-java/v2/developer-guide/signup-create-iam-user.html

### V2 API not recommended (yet) ###
While there is a preview-version of the v2 aws Java-SDK, it is not suited for normal use - it's lacking in documentation and in examples. The documentation is short and for preview-1, and preview-5 has API-changes.



