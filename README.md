
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

## Sample Output ##

The output of this code looks similar to the following:

----
Original text: برهم أحمد صالح
Original language: ARABIC
Translation: Brhm Ahmad Salih	Confidence: 0.0
Translation: Brhm Ahmad Salih	Confidence: 0.0
----
Original text: عبد الفتاح سعيد حسين خليل السيسى
Original language: ARABIC
Translation: 'Abd-al-Fattah Sa'id Husayn Khalil al-SysA	Confidence: 0.0
Translation: 'Abd-al-Fattah Sa'id Husayn Khalil al-SysA	Confidence: 0.0
----
Original text: 胡锦涛
Original language: CHINESE
Translation: Hu Jintao	Confidence: 1.0
----
Original text: 习近平
Original language: CHINESE
Translation: Xi Jinping	Confidence: 1.0
----
Original text: Влади́мир Влади́мирович Пу́тин
Original language: RUSSIAN
Translation: Vladímir Vladímirovich Pútin	Confidence: 1.0
----
Original text: Дми́трий Анато́льевич Медве́дев
Original language: RUSSIAN
Translation: Dmítriy Anatólyevich Medvédev	Confidence: 1.0
----
Original text: 문재인
Original language: KOREAN
Translation: Mun Chae-in	Confidence: 1.0
----
Original text: 이낙연
Original language: KOREAN
Translation: Yi Nak-yo'n	Confidence: 1.0
----
Original text: 김정은
Original language: KOREAN
Translation: Kim Cho'ng-u'n	Confidence: 1.0
