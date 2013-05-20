package ch.hsr.ba.tourlive.web.utils.importer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSVReader {
	private InputStream in = null;
	private final String CSV_SEPARATOR = ";";
	private final String ENCODING = "UTF8";
	private final static Logger LOG = LoggerFactory.getLogger(CSVReader.class);

	public CSVReader(InputStream in) {
		this.in = in;
	}

	public void setInputStream(InputStream in) {
		this.in = in;
	}

	public ArrayList<String[]> readFile() {
		final ArrayList<String[]> returnList = new ArrayList<String[]>();
		Scanner scan = null;
		scan = new Scanner(in, ENCODING);
		try {
			if (scan.hasNext()) {
				// skip header line
				scan.nextLine();
			}
			while (scan.hasNext()) {
				returnList.add(scan.nextLine().split(CSV_SEPARATOR));
			}
		} catch (Exception e) {
			LOG.error("Error while importing csv file");
		} finally {
			scan.close();
		}
		return returnList;
	}
}
