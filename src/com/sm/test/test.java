package com.sm.test;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*String xmlString="<?xml version=\"1.0\" encoding=\"gb2312\"?>"+
	        "<Result xmlns=\"http://www.fiorano.com/fesb/activity/DBQueryOnInput2/Out\">"+
	           "<row resultcount=\"1\">"+
	              "<��վ����>1082197    </��վ����>"+
	              "<��վ�籨��>�ﶫ��     </��վ�籨��>"+
	              "<�籨����>TBZ    </�籨����>"+
	              "<�����Ժ�>����վ    </�����Ժ�>"+
	           "</row>"+
	           "<row resultcount=\"1\">"+
	              "<��վ����>1082196    </��վ����>"+
	              "<��վ�籨��>�Ǻα�    </��վ�籨��>"+
	              "<�籨����>HUZ   </�籨����>"+
	              "<�����Ժ�>����վ    </�����Ժ�>"+
	           "</row>"+
	        "</Result>";*/
		String xmlString="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
		        "<results>"+
				"<message>��վ������</message>"+
                    "<verno>1.1.037b</verno>"+
                     "<records>7797</records>"+
                    "<totalPages>39</totalPages>"+
                     "<pageNum>1</pageNum>"+
                     "<pageSize>200</pageSize>"+
		           "<Row>"+
                     "<Ψһ��ʶ��>fdbdc3c1755f48a98cb5d4790838cefb</Ψһ��ʶ��>"+
		              "<��վ����>1082197    </��վ����>"+
		              "<��վ�籨��>�ﶫ��     </��վ�籨��>"+
		              "<�籨����>TBZ    </�籨����>"+
		              "<�����Ժ�>����վ    </�����Ժ�>"+
		           "</Row>"+
		           "<Row>"+
		           "<Ψһ��ʶ��>e9f3cfa814754c2c9500346a17e66088</Ψһ��ʶ��>"+
		           "<��վ����>�Ǻα�</��վ����>"+
		           "<��վ�籨��>HUZ</��վ�籨��>"+
		           "<�籨����>����վ</�籨����>"+
		           "<�����Ժ�>NNZ</�����Ժ�>"+
		           "</Row>"+
		        "</results>";
     DBdataModle DB=new DBdataModle(xmlString);
	}

}
