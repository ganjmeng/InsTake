package adapters;

/**
 * Created by mac on 27/01/16.
 */
public class arrayAdapter {

    private String filepath, filename;

    public arrayAdapter() {
    }

    public arrayAdapter(String filepath, String filename) {
        this.filepath = filepath;
        this.filename = filename;
    }

    public String getFilePath() {
        return filepath;
    }

    public void setFilePath(String filepath) {
        this.filepath = filepath;
    }

    public String getFileName() {
        return filename;
    }

    public void setFileName(String filename) {
        this.filename = filename;
    }

}
