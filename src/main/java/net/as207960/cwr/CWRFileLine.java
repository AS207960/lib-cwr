package net.as207960.cwr;

public class CWRFileLine {
    private final String record_type;
    private final String record_contents;

    protected CWRFileLine(String line) throws CWRParsingException {
        if (line.length() < 3) {
            throw new CWRParsingException("Invalid line: " + line);
        }
        this.record_type = line.substring(0, 3);
        this.record_contents = line.substring(3);
    }

    public String recordType() {
        return this.record_type;
    }

    public String recordContents() {
        return this.record_contents;
    }
}
