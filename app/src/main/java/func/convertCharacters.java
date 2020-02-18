package func;

/**
 * Created by mac on 29/02/16.
 */
public class convertCharacters {

    public static String xmlchars(String xml) {
        return xml.replaceAll("&#123;","{").replaceAll("&#125;","}").replaceAll("&amp;" ,"&").replaceAll("&gt;",">").replaceAll("&lt;","<").replaceAll("&quot;","\"").replaceAll("&apos;","'");
    }

}