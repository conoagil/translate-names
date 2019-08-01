/******************************************************************************
 **
 ** Copyright (c) 2019 Conoa, Inc.
 **
 ** Licensed under MIT License
 **
 ******************************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;

import com.basistech.rni.index.INameIndex;
import com.basistech.rni.index.INameIndexSession;
import com.basistech.rni.index.IndexStoreDataModelFlags;
import com.basistech.rni.index.NameIndexException;
import com.basistech.rni.index.NameIndexStoreException;
import com.basistech.rni.index.StandardNameIndex;
import com.basistech.rnt.ITranslator;
import com.basistech.rnt.ITranslatable;
import com.basistech.rnt.TranslationResult;
import com.basistech.rnt.BasicTranslatorFactory;
import com.basistech.rni.match.Name;
import com.basistech.rni.match.NameBuilder;
import com.basistech.rnt.RNTException;
import com.basistech.rnt.RNTEnvironment;
import com.basistech.util.LanguageCode;
import com.basistech.util.Pathnames;
import com.basistech.util.ISO15924;
import com.basistech.util.LanguageCode;
import com.basistech.util.TextDomain;
import com.basistech.util.TransliterationScheme;

/*
 * TranslateNames
 * 
 * This code takes names.txt as input and produces translations or 
 * transliterations for each name in the file using Rosette's RNI/RNT product.
 * Currently, it transliterates from Arabic, Chinese, Korean and Russian names to
 * their Latin-script equivalents.  The code could be extended to support the
 * other languages supported by RNT.
 *
 * This class may be placed anywhere, but is typically placed in the RNI/RNT
 * samples folder.  For examples, if you place RNI/RNT in
 * /Users/xxx/Documents/rnirnt, then the samples
 * folder is /Users/xxx/Documents/rnirnt/rlpnc/samples/java.
 *
 * Use this command to build:
 * 
 * javac -classpath /Users/xxx/Documents/rnirnt/:/Users/xxx/Documents/rnirnt/rlpnc/lib/jvm/*:. TranslateNameFile.java 
 *
 * Use this command to run:
 *
 * java -Dbt.root=/Users/xxx/Documents/rnirnt -cp .:/Users/xxx/Documents/rnirnt/:/Users/xxx/Documents/rnirnt/rlpnc/lib/jvm/* TranslateNameFile
 *
 */
public class TranslateNames {

    /*
     * Get a translator object given a language code,
     * currently specifying the IC transliteration scheme.
     *
     * Configured to take foreign-script input and produce Latin-script output 
     */
    public static ITranslator getTranslator(LanguageCode langCode) throws Exception {
        // Source domain: language code is passed in 
        TextDomain srcDomain = new TextDomain(langCode);

        // Target domain: Latin script, English language, and IC transliteration.
        TextDomain targetDomain = new TextDomain(ISO15924.Latn,
            LanguageCode.ENGLISH,
            TransliterationScheme.IC);
        ITranslator translator = BasicTranslatorFactory.create(srcDomain, targetDomain);
        return translator;
    }

    /*
     * Translate the input string
     */
    public static void produceTranslation(ITranslator araTranslator, ITranslator zhoTranslator,
        ITranslator rusTranslator, ITranslator korTranslator, String sourceString) throws Exception {
        
        System.out.println("----");
        System.out.println ("Original text: " + sourceString);

        // Don't set the language and script on the NameBuilder
        // so it can detect them for us
        ITranslatable toTranslate = NameBuilder.data(sourceString)
            .build();
        System.out.println ("Original language: " + toTranslate.getLanguage());

        // Perform the translation and get the results.
        List<TranslationResult> results = new ArrayList<TranslationResult>();
        switch (toTranslate.getLanguage()) {
            case ARABIC:
                results = araTranslator.translate(toTranslate);
            break;
            case CHINESE:
                results = zhoTranslator.translate(toTranslate);
            break;
            case RUSSIAN:
                results = rusTranslator.translate(toTranslate);
            break;
            case KOREAN:
                results = korTranslator.translate(toTranslate);
            break;
            default:
        }

        // RNT produces a list of translations, so iterate through the list
        // and print to console
        for (TranslationResult result : results) {
            String trans = result.getTranslation();
            Double transConfidence = result.getConfidence();
            System.out.println("Translation: " + trans + "\tConfidence: " + transConfidence);
        }
    }

    /*
     * Application entry point.
     */
    public static void main(String[] args) throws Exception {

        // Initialize RNT and get some language translators
        RNTEnvironment rntEnv = new RNTEnvironment();
        ITranslator araTranslator = getTranslator(LanguageCode.ARABIC);
        ITranslator zhoTranslator = getTranslator(LanguageCode.CHINESE);
        ITranslator rusTranslator = getTranslator(LanguageCode.RUSSIAN);
        ITranslator korTranslator = getTranslator(LanguageCode.KOREAN);

        try {
            File f = new File("names.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String inputName = "";

            // iterate through the lines in the names file
            while ((inputName = b.readLine()) != null) {
                produceTranslation(araTranslator, zhoTranslator, rusTranslator, korTranslator, inputName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // clean up the translators and RNT
        araTranslator.close();
        zhoTranslator.close();
        rusTranslator.close();
        korTranslator.close();
        rntEnv.close();
    }
}
