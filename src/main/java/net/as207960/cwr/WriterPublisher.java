package net.as207960.cwr;

public class WriterPublisher {
    protected Publisher publisher;
    protected String agreement_id;
    protected String society_agreement_id;

    public WriterPublisher() {}

    protected WriterPublisher(
            Publisher publisher, String agreement_id, String society_agreement_id
    ) {
        this.publisher = publisher;
        this.agreement_id = agreement_id;
        this.society_agreement_id = society_agreement_id;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    /**
     * @return The unique number assigned to this agreement by the
     * submitter. Nullable.
     */
    public String getAgreementId() {
        return agreement_id;
    }

    public void setAgreementId(String agreement_id) {
        this.agreement_id = agreement_id;
    }

    /**
     * @return The unique number assigned to this agreement by the society. Nullable.
     */
    public String getSociety_agreementId() {
        return society_agreement_id;
    }

    public void setSocietyAgreementId(String society_agreement_id) {
        this.society_agreement_id = society_agreement_id;
    }

    @Override
    public String toString() {
        return "WriterPublisher{" +
                "publisher=" + publisher +
                ", agreement_id='" + agreement_id + '\'' +
                ", society_agreement_id='" + society_agreement_id + '\'' +
                '}';
    }
}
