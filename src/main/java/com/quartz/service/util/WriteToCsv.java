package com.quartz.service.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.quartz.model.QueryResult;

@Component
public class WriteToCsv {

	private static final Logger logger = LoggerFactory.getLogger(WriteToCsv.class);
	private Date date = Calendar.getInstance().getTime();

	private CsvBeanWriter csvOutput;
	private DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH.mm");

	public void write(List<QueryResult> queryResults) {
		logger.debug("Write to file");
		String path = System.getProperty("user.home") + "\\Desktop\\";
		File file = new File(path + "query_result_".concat(df.format(date)).concat(".csv"));
		String header[] = new String[]{"queryName", "queryResult", "queryExceptions"};
		String columns[] = new String[]{"Query Name", "Query Result", "Query Exception"};

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			csvOutput = new CsvBeanWriter(new FileWriter(file, true), CsvPreference.STANDARD_PREFERENCE);
			writeModel(csvOutput, columns, queryResults, header);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (csvOutput != null) {
				try {
					csvOutput.flush();
					csvOutput.close();
					logger.debug("The writer was closed and buffer flushed");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void writeModel(CsvBeanWriter writerBean, String[] columns, List<QueryResult> queryResults, String[] header) throws IOException {		
		writerBean.writeHeader(columns);
		for (QueryResult queryResult : queryResults) {			
			writerBean.write(queryResult, header);
		}
	}
}

