package com.hansumi.journalapp.utilities;

public class Utils {

    public static String makeBriefTextFromDB(String text){

        int numWords = 30;
        int i = nthOccurrence(text, ' ', numWords - 1);
        String summary = i == -1 ? text : text.substring(0, i);

        return summary + "  ...";
    }



    public static int nthOccurrence(String str, char c, int n) {
        int pos = str.indexOf(c, 0);
        while (n-- > 0 && pos != -1)
            pos = str.indexOf(c, pos+1);
        return pos;
    }


}
