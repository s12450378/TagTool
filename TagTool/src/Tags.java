
public class Tags {
	private String tagName;
	private String[] dataList;		//紀錄哪些data用到此tag(紀錄name)
	private int dataCount=0;		//有幾個data用到此tag
	
	
	
	
	
	
	public String getTagName(){		//取得tag的name
		return tagName;
	}
	public void changeTagName(String newName){	//修改tag的name
		tagName=newName;
	}
	
	
}
