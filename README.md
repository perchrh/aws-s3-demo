# S3 Hackathon
S3 capabilities and usage

## Necessary preparations: ##


### 1 Install AWS command line tools ###

Reference: https://aws.amazon.com/cli/

Instructions for MacOS:

    brew install python3 pip3
    sudo -H pip3 install awscli
    aws configure # med Security Credentials (=access keys)

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
    
### 3 Test access to AWS through the Java-SDK ###
Run this project's code to test that the access configured in the previous step works.

## Documentation ##
Official AWS S3 instructions, https://aws.amazon.com/documentation/sdk-for-java/

Youtube playlist, https://www.youtube.com/playlist?list=PL77cy_t1wkZtYlWt26cFH5wWMQPHSGefa

Signing up for AWS and creating an account and a IAM-user, http://docs.aws.amazon.com/sdk-for-java/v2/developer-guide/signup-create-iam-user.html

### V2 API not recommended (yet) ###
While there is a preview-version of the v2 aws Java-SDK, it is not suited for normal use - it's lacking in documentation and in examples. The documentation is short and for preview-1, and preview-5 has API-changes.


### Code examples (v1 api) ###
Check out 
https://github.com/awsdocs/aws-doc-sdk-examples/tree/master/java/example_code/s3

*There are no code examples for v2 api, only (outdated) snippets*
