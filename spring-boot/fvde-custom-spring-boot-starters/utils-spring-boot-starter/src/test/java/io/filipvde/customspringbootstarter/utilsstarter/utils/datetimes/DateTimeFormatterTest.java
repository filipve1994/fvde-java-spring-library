package io.filipvde.customspringbootstarter.utilsstarter.utils.datetimes;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

class DateTimeFormatterTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeFormatterTest.class);

	@Test
	public void convertLocalTimeToGMT_Object() {
		// Local time to GMT (Date object)
		Date localTime = new Date();
		LOGGER.debug("Local:\t" + localTime);
		Date gmtTime = DateTimeFormatter.convertLocalTimeToGMT(localTime);
		LOGGER.debug("GMT:\t" + gmtTime);
	}

	@Test
	public void convertGMTTimeToLocal_Object() {
		// GMT time to Local (Date object)
		Date gmtTime = new Date();
		LOGGER.debug("GMT:\t" + gmtTime);
		Date localTime = DateTimeFormatter.convertGMTTimeToLocal(gmtTime);
		LOGGER.debug("Local:\t" + localTime);
	}

	@Test
	public void convertLocalTimeToGMT_String() {
		// Local time to GMT (String object)
		String localTime = "2016-05-22 17:16:15";
		LOGGER.debug("Local:\t" + localTime);
		String gmtTime = DateTimeFormatter.convertLocalTimeToGMT(localTime, DateTimeFormatter.DATE_TIME_POPULAR_FORMAT);
		LOGGER.debug("GMT:\t" + gmtTime);
	}

	@Test
	public void convertGMTTimeToLocal_String() {
		// GMT time to Local (String object)
		String gmtTime = "2016-05-22 17:16:15";
		LOGGER.debug("GMT:\t" + gmtTime);
		String localTime = DateTimeFormatter.convertGMTTimeToLocal(gmtTime, DateTimeFormatter.DATE_TIME_POPULAR_FORMAT);
		LOGGER.debug("Local:\t" + localTime);
	}

	@Test
	public void calculateDifference() {
		// Calculate difference between 2 date
		String date1 = "2016-05-22 07:16:15";
		String date2 = "2016-06-22 19:19:15";
		DateTimeCalculation.DateTimeResult result = DateTimeCalculation.calculateDifference(date1, date2,
			DateTimeFormatter.DATE_TIME_POPULAR_FORMAT);
		LOGGER.debug("Date time difference days:\t" + result.getDiffDays());
		LOGGER.debug("Date time difference hours:\t" + result.getDiffHours());
		LOGGER.debug("Date time difference minutes:\t" + result.getDiffMinutes());
		LOGGER.debug("Date time difference seconds:\t" + result.getDiffSeconds());
	}

	@Test
	public void getCurrenDateTimeForNaming() {
		LOGGER.debug("Get current date time for naming:\t" +
			DateTimeFormatter.formatDateTimeToString(new Date(), DateTimeFormatter.DATE_TIME_NAMING));
	}
}