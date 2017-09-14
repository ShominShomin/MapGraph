package stats.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ContentData {

	private List<String> months;
	private String yearNum;

	public ContentData() {
		yearNum = new String("1990");
		months = new ArrayList<String>();
	}

	public List<String> getMonths() {
		return months;
	}

	public void setMonths(List<String> months) {
		this.months = months;
	}
	
	public String getYearNum() {
		return yearNum;
	}

	public void setYearNum(String yearNum) {
		this.yearNum = yearNum;
	}

}
