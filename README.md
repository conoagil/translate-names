
# Sample application using Rosette Name Translation (RNT)

## Purpose ##

 This code takes names.txt as input and produces translations or
 transliterations for each name in the file using Rosette's RNI/RNT product.
 Currently, it transliterates from Arabic, Chinese, Korean and Russian names to
 their Latin-script equivalents.  The code could be extended to support the
 other languages supported by RNT.

 This class may be placed anywhere, but is typically placed in the RNI/RNT
 samples folder.  For examples, if you place RNI/RNT in
 /Users/xxx/Documents/rnirnt, then the samples
 folder is /Users/xxx/Documents/rnirnt/rlpnc/samples/java.

## Building ##

Use this command to build:

javac -classpath /Users/xxx/Documents/rnirnt/:/Users/xxx/Documents/rnirnt/rlpnc/lib/jvm/*:. TranslateNameFile.java

## Running ##

Use this command to run:

java -Dbt.root=/Users/xxx/Documents/rnirnt -cp .:/Users/xxx/Documents/rnirnt/:/Users/xxx/Documents/rnirnt/rlpnc/lib/jvm/* TranslateNameFile
