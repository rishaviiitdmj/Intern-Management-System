package io.javabrains.monitoring;

import java.io.IOException;

import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

@Component
public class CsvGenerator {
public void writeSkillScores(List<SkillWiseScore> scores, Writer writer) {
try {

CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
printer.printRecord("InternId","Java","JPA","Junit","SpringBoot","SQL");
for (SkillWiseScore score : scores) {
printer.printRecord(score.getInternId(),score.getJava(),score.getJpa(),score.getJunit(),score.getSpringBoot(),score.getSql());
}
printer.close();
} catch (IOException e) {
e.printStackTrace();

}
}
}