package net.as207960.cwr;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Date;

public class App {
    public static void main(String[] args) throws IOException, CWRParsingException {
        CWRFile file = new CWRFile(
                2022,1, "119", "119", "TEST", "EMI",
                CWRFile.SenderType.Publisher, new Date(), new Date()
        );

        Work work = new Work();
        work.setWorkId("TESTID");
        work.setWorkTitle("Test song title");
        work.setCopyrightDate(new Date());
        work.setCategory(Work.Category.Popular);
        work.setDuration(Duration.ofSeconds(273));
        work.setRecorded(true);
        work.setVersionType(Work.VersionType.Original);
        work.setPriority(false);

        Publisher publisher = new Publisher();
        publisher.setPublisherId("119");
        publisher.setName("Honest Q's Used Music");
        publisher.setPublisherType(Publisher.PublisherType.OriginalPublisher);
        publisher.setIPIName("86414760");
        publisher.setPRAffiliatedSociety(52);
        publisher.setPROwnershipShare(50);
        publisher.setMRAffiliatedSociety(52);
        publisher.setMROwnershipShare(100);
        publisher.setSRAffiliatedSociety(52);
        publisher.setSROwnershipShare(100);
        publisher.setFirstRecordingRefusal(false);

        TerritoryCollection publisherTerritory = new TerritoryCollection();
        publisherTerritory.setTerritory(new Territory(2136, false));
        publisherTerritory.setPRCollectionShare(50);
        publisherTerritory.setMRCollectionShare(100);
        publisherTerritory.setSRCollectionShare(100);
        publisherTerritory.setShareChange(false);
        publisher.addControlledTerritory(publisherTerritory);

        work.addPublisher(publisher);

        Writer writer = new Writer();
        writer.setWriterId("TESTID");
        writer.setLastName("Name");
        writer.setFirstName("A");
        writer.setIPIName("64277861");
        writer.setDesignation(Writer.Designation.ComposerAuthor);
        writer.setPRAffiliatedSociety(52);
        writer.setPROwnershipShare(50);
        writer.setMRAffiliatedSociety(52);
        writer.setMROwnershipShare(100);
        writer.setSRAffiliatedSociety(52);
        writer.setSROwnershipShare(100);
        writer.setFirstRecordingRefusal(false);
        writer.setWorkForHire(false);

        TerritoryCollection writerTerritory = new TerritoryCollection();
        writerTerritory.setTerritory(new Territory(2136, false));
        writerTerritory.setPRCollectionShare(50);
        writerTerritory.setMRCollectionShare(100);
        writerTerritory.setSRCollectionShare(100);
        writerTerritory.setShareChange(false);
        writer.addTerritory(writerTerritory);

        WriterPublisher writerPublisher = new WriterPublisher();
        writerPublisher.setPublisher(publisher);
        writer.addPublisher(writerPublisher);
        work.addWriter(writer);

        file.addNewWork(work);

        CWROutput output = file.toFile();

        System.out.printf("Total records: %d\n", output.getTotalRecordCount());
        System.out.printf("Group count: %d\n", output.getGroupCount());

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file.getFileName()));
        fileWriter.write(output.getContents());
        fileWriter.close();

        String inFile = "CW220001000_DMP.V21";
        String inFileContents = new String(Files.readAllBytes(Paths.get(inFile)));

        CWRFile in = CWRFile.parseCWR(inFile, inFileContents);

        System.out.println(in);
    }
}
