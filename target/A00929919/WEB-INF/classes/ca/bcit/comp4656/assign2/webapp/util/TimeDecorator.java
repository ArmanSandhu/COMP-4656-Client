package ca.bcit.comp4656.assign2.webapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.displaytag.decorator.TableDecorator;

import ca.bcit.comp4656.assign2.webapp.domain.Employee;

public class TimeDecorator extends TableDecorator {

	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
	public String getDob() {
		Date dob = ((Employee)(this.getCurrentRowObject())).getDob();
		if(dob == null || "".equals(dob)) {
			return dateFormatter.format(new Date());
		}
		return dateFormatter.format(dob);
	}
}
