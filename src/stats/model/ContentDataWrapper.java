package stats.model;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ContentDataWrapper {

	private List<ContentData> years;
	
	public ContentDataWrapper() {
		years = new ArrayList<ContentData>();
	}

	public List<ContentData> getYears() {
		return years;
	}

	public void setYears(List<ContentData> years) {
		this.years = years;
	}
	
}
