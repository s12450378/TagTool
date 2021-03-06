import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
//備忘：changeTag之減少tag未完成
//用searchTag==0去做
//changeTag需要判斷新舊字串有幾個tag

public class Data {
	private String tagDirectory;	//包含路徑跟檔名
	private String dataDirectory;	//包含路徑跟檔名
	private String dataName;	//檔名
	public String tags;			//一個字串,以","之類的字元分割出各個tag
	public String tagList[] = new String[50];	//存分割後的tag
	private int tagCount=0;		//此data有幾個tag
	
	public boolean dataAvailable(){	//檔案是否存在檔案系統
		
		return true;
	}
	public int getCount(){
		return tagCount;
	}
	void defaultData(String name,String tagsInput,String dir,String dir2){
		dataName = name ;
		tagDirectory=new String(dir);
		dataDirectory=new String(dir2);
		changeTag(tagsInput);
	}
	public void changeTag(String inputString){	//供UI調用,change tag按鈕按下去時候
		
		tags=inputString;
		tagToken(tags);
		try{
	//		System.out.println(tagDirectory);
	        FileWriter fw = new FileWriter(tagDirectory);
	        fw.write(tags);
	        fw.flush();
	        fw.close();
			}catch(IOException e2){}

	}
	public String getDataDir(){
		return dataDirectory;
	} 
	
	public boolean tagExist(){	//該data是否有tag(已完成)
		if(tags=="")
			return false;
		else		
			return true;
	}
	public void removeData(){	// 就把此檔案的tag都拿掉,主程式端data的數量-1就好
		tags="";
		tagCount=0;
		
	}
	
	public String getDataName(){
		return dataName;
	}

	private void tagToken(String tagString){
		tagCount=0;
		if(tagString.equals("")){
			
		}else{
			StringTokenizer Tok=new StringTokenizer(tagString,",");	//以","做區隔
			String buffer="";
		
			while (Tok.hasMoreElements()){
				buffer=(String) Tok.nextElement();
				if(searchTag(buffer)==0){
				tagList[++tagCount] = new String("");
				tagList[tagCount]=buffer;	//注意陣列索引值是從1~tagCount,0暫不使用
				}
			}
		}
	}
	
	public int searchTag(String btag){
		int index=0;					//若return 0 代表該tag不存在
		for(int i=1;i<=tagCount;i++){
			if(btag.equals(tagList[i])){
				index=i;
				return index;		//找到tag就回傳所在index
			}
		}
		
		return index;	//否則回傳0
	}
	
	public void sort(){		//把分割後的tag做排序
		for(int i=1;i<=tagCount;i++){
			String temp=tagList[i];
			int j;
			for(j=i-1;j>=0 && tagList[j].compareTo(temp)>0;j--)
				tagList[j+1]=tagList[j];
			tagList[j+1]=temp;
		}
	}
	
	public void bind(){		//把排序後的tag丟回字串,用來顯示在UI上
		tags="";
		for(int i=1;i<tagCount;i++){
			tags+=tagList[i]+",";
		}
		tags+=tagList[tagCount];
	}
	public void deleteTag(String deTag){
		tagCount=0;
		if(tags.equals("")){
			
		}else{
			StringTokenizer Tok=new StringTokenizer(tags,",");	//以","做區隔
			String buffer="";
		
			while (Tok.hasMoreElements()){

				buffer=(String) Tok.nextElement();
					if(!buffer.equals(deTag)){					
						if(searchTag(buffer)==0){
							tagList[++tagCount] = new String("");
							tagList[tagCount]=buffer;	//注意陣列索引值是從1~tagCount,0暫不使用
						}
					}
			}
		if(tagCount==0)
			tags="";
		for(int i=1;i<=tagCount;i++)
			if(i==1)
				tags=tagList[i];
			else
				tags=tags+"," + tagList[i];
		}
		changeTag(tags);
	}

}
