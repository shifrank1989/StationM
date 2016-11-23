package com.sm.test;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*String xmlString="<?xml version=\"1.0\" encoding=\"gb2312\"?>"+
	        "<Result xmlns=\"http://www.fiorano.com/fesb/activity/DBQueryOnInput2/Out\">"+
	           "<row resultcount=\"1\">"+
	              "<车站名称>1082197    </车站名称>"+
	              "<车站电报码>田东北     </车站电报码>"+
	              "<电报所名>TBZ    </电报所名>"+
	              "<所名略号>南宁站    </所名略号>"+
	           "</row>"+
	           "<row resultcount=\"1\">"+
	              "<车站名称>1082196    </车站名称>"+
	              "<车站电报码>那何北    </车站电报码>"+
	              "<电报所名>HUZ   </电报所名>"+
	              "<所名略号>南宁站    </所名略号>"+
	           "</row>"+
	        "</Result>";*/
		String xmlString="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
		        "<results>"+
				"<message>车站主数据</message>"+
                    "<verno>1.1.037b</verno>"+
                     "<records>7797</records>"+
                    "<totalPages>39</totalPages>"+
                     "<pageNum>1</pageNum>"+
                     "<pageSize>200</pageSize>"+
		           "<Row>"+
                     "<唯一标识码>fdbdc3c1755f48a98cb5d4790838cefb</唯一标识码>"+
		              "<车站名称>1082197    </车站名称>"+
		              "<车站电报码>田东北     </车站电报码>"+
		              "<电报所名>TBZ    </电报所名>"+
		              "<所名略号>南宁站    </所名略号>"+
		           "</Row>"+
		           "<Row>"+
		           "<唯一标识码>e9f3cfa814754c2c9500346a17e66088</唯一标识码>"+
		           "<车站名称>那何北</车站名称>"+
		           "<车站电报码>HUZ</车站电报码>"+
		           "<电报所名>南宁站</电报所名>"+
		           "<所名略号>NNZ</所名略号>"+
		           "</Row>"+
		        "</results>";
     DBdataModle DB=new DBdataModle(xmlString);
	}

}
