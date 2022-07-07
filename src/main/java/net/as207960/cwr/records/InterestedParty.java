package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

public class InterestedParty implements Record {
    public enum AgreementRole {
        Assignor,
        Acquirer
    }

    private final int transaction_sequence;
    private final int record_sequence;
    private final AgreementRole agreement_role;
    private final String ipi_name;
    private final String ipi_base_number;
    private final String party_id;
    private final String name;
    private final String first_name;
    private final int pr_affiliated_society;
    private final float pr_rights;
    private final int mr_affiliated_society;
    private final float mr_rights;
    private final int sr_affiliated_society;
    private final float sr_rights;

    public InterestedParty(
            int transaction_sequence, int record_sequence,
            AgreementRole agreement_role, String ipi_name, String ipi_base_number, String party_id, String name,
            String first_name, int pr_affiliated_society, float pr_rights,
            int mr_affiliated_society, float mr_rights,
            int sr_affiliated_society, float sr_rights
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.agreement_role = agreement_role;
        this.ipi_name = ipi_name != null ? ipi_name : "";
        this.ipi_base_number = ipi_base_number != null ? ipi_base_number : "";
        this.party_id = party_id != null ? party_id : "";
        this.name = name != null ? name : "";
        this.first_name = first_name != null ? first_name : "";
        this.pr_affiliated_society = pr_affiliated_society;
        this.pr_rights = pr_rights;
        this.mr_affiliated_society = mr_affiliated_society;
        this.mr_rights = mr_rights;
        this.sr_affiliated_society = sr_affiliated_society;
        this.sr_rights = sr_rights;
    }

    private static String mapAgreementRole(AgreementRole role) {
        if (role == null) {
            return "  ";
        }

        switch (role) {
            case Assignor:
                return "AS";
            case Acquirer:
                return "AC";
            default:
                return "  ";
        }
    }

    private static AgreementRole parseAgreementRole(String role) throws CWRParsingException {
        switch (role) {
            case "  ":
                return null;
            case "AS":
                return AgreementRole.Assignor;
            case "AC":
                return AgreementRole.Acquirer;
            default:
                throw new CWRParsingException("Invalid agreement role: " + role);
        }
    }

    public static InterestedParty parse(String line) throws CWRParsingException {
        line = String.format("%-150s", line);

        int transactionSequence;
        int recordSequence;

        try {
            transactionSequence = Integer.parseInt(line.substring(0, 8), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid transaction sequence: " + e);
        }

        try {
            recordSequence = Integer.parseInt(line.substring(8, 16), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid record sequence: " + e);
        }

        AgreementRole agreementRole = parseAgreementRole(line.substring(16, 18));
        String ipiName = line.substring(18, 29).trim();
        String ipiBaseName = line.substring(29, 42).trim();
        String partyId = line.substring(42, 51).trim();
        String name = line.substring(51, 96).trim();
        String firstName = line.substring(96, 126).trim();

        int pr_affiliated_society;
        float pr_share;
        int mr_affiliated_society;
        float mr_share;
        int sr_affiliated_society;
        float sr_share;

        try {
            String pr_affiliated_society_str = line.substring(126, 129).trim();
            if (!pr_affiliated_society_str.isEmpty()) {
                pr_affiliated_society = Integer.parseInt(pr_affiliated_society_str, 10);
            } else {
                pr_affiliated_society = 0;
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid PR affiliated society: " + e);
        }
        try {
            int pr_share_int = Integer.parseInt(line.substring(129, 134), 10);
            pr_share = (float)(pr_share_int) / 100.0f;
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid PR share: " + e);
        }

        try {
            String mr_affiliated_society_str = line.substring(134, 137).trim();
            if (!mr_affiliated_society_str.isEmpty()) {
                mr_affiliated_society = Integer.parseInt(mr_affiliated_society_str, 10);
            } else {
                mr_affiliated_society = 0;
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid MR affiliated society: " + e);
        }
        try {
            int mr_share_int = Integer.parseInt(line.substring(137, 142), 10);
            mr_share = (float)(mr_share_int) / 100.0f;
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid MR share: " + e);
        }

        try {
            String sr_affiliated_society_str = line.substring(142, 145).trim();
            if (!sr_affiliated_society_str.isEmpty()) {
                sr_affiliated_society = Integer.parseInt(sr_affiliated_society_str, 10);
            } else {
                sr_affiliated_society = 0;
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid SR affiliated society: " + e);
        }
        try {
            int sr_share_int = Integer.parseInt(line.substring(145, 150), 10);
            sr_share = (float)(sr_share_int) / 100.0f;
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid SR hare: " + e);
        }

        return new InterestedParty(
                transactionSequence, recordSequence, agreementRole, ipiName, ipiBaseName, partyId,
                name, firstName, pr_affiliated_society, pr_share, mr_affiliated_society, mr_share,
                sr_affiliated_society, sr_share
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-2.2s%-11.11s%-13.13s%-9.9s%-45.45s%-30.30s%03d%05d%03d%05d%03d%05d",
                Utils.recordPrefix("IPA", this.transaction_sequence, this.record_sequence),
                mapAgreementRole(this.agreement_role), this.ipi_name, this.ipi_base_number, this.party_id,
                this.name, (this.agreement_role == AgreementRole.Assignor) ? "" : this.first_name,
                this.pr_affiliated_society, (int)(this.pr_rights*100),
                this.mr_affiliated_society, (int)(this.mr_rights*100),
                this.sr_affiliated_society, (int)(this.sr_rights*100)
        );
    }

    public int transactionSequence() {
        return transaction_sequence;
    }

    public int recordSequence() {
        return record_sequence;
    }

    @Override
    public String toString() {
        return "InterestedParty{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", agreement_role=" + agreement_role +
                ", ipi_name='" + ipi_name + '\'' +
                ", ipi_base_number='" + ipi_base_number + '\'' +
                ", party_id='" + party_id + '\'' +
                ", name='" + name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", pr_affiliated_society=" + pr_affiliated_society +
                ", pr_rights=" + pr_rights +
                ", mr_affiliated_society=" + mr_affiliated_society +
                ", mr_rights=" + mr_rights +
                ", sr_affiliated_society=" + sr_affiliated_society +
                ", sr_rights=" + sr_rights +
                '}';
    }

    public AgreementRole getAgreementRole() {
        return agreement_role;
    }

    public String getIpiName() {
        return ipi_name;
    }

    public String getIpiBaseNumber() {
        return ipi_base_number;
    }

    public String getPartyId() {
        return party_id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return first_name;
    }

    public int getPRAffiliatedSociety() {
        return pr_affiliated_society;
    }

    public float getPRRights() {
        return pr_rights;
    }

    public int getMRAffiliatedSociety() {
        return mr_affiliated_society;
    }

    public float getMRRights() {
        return mr_rights;
    }

    public int getSRAffiliatedSociety() {
        return sr_affiliated_society;
    }

    public float getSRRights() {
        return sr_rights;
    }
}
