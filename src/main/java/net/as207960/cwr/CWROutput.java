package net.as207960.cwr;

public class CWROutput {
    private final String contents;
    private final int totalRecordCount;

    private final int groupCount;

    protected CWROutput(String contents, int totalRecordCount, int groupCount) {
        this.contents = contents;
        this.totalRecordCount = totalRecordCount;
        this.groupCount = groupCount;
    }

    public String getContents() {
        return this.contents;
    }

    public int getTotalRecordCount() {
        return this.totalRecordCount;
    }

    public int getGroupCount() {
        return this.groupCount;
    }
}
