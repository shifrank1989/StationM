package com.sm.tools;

public class SQL {
   public static String All_SQL_select="select "
			+ "z.code,z.name,j.daizi,j.name,z.company,b.name,z.daima,b.province,z.keyun,z.huoyun,z.xianyun,z.yewu,z.status,z.page "
			+ "from T_ZhanMingLueHao z,T_BaoSuo b,T_JuWei j "
			+ "where z.baosuoId=b.id and z.juweiId=j.id ";
   public static String Find_SQL_name="select "
			+ "z.code,z.name,j.daizi,j.name,z.company,b.name,z.daima,z.province,z.keyun,z.huoyun,z.xianyun,z.yewu,z.status,z.page "
			+ "from T_ZhanMingLueHao z,T_BaoSuo b,T_JuWei j "
			+ "where z.baosuoId=b.id and z.juweiId=j.id and z.name like '%";
   public static String Find_SQL_bsname="select "
			+ "z.code,z.name,j.daizi,j.name,z.company,b.name,z.daima,z.province,z.keyun,z.huoyun,z.xianyun,z.yewu,z.status,z.page "
			+ "from T_ZhanMingLueHao z,T_BaoSuo b,T_JuWei j "
			+ "where z.baosuoId=b.id and z.juweiId=j.id and b.name like '%";
   public static String Find_SQL_company="select "
			+ "z.code,z.name,j.daizi,j.name,z.company,b.name,z.daima,z.province,z.keyun,z.huoyun,z.xianyun,z.yewu,z.status,z.page "
			+ "from T_ZhanMingLueHao z,T_BaoSuo b,T_JuWei j "
			+ "where z.baosuoId=b.id and z.juweiId=j.id and z.company like '%";
   public static String Find_SQL_juwei="select "
			+ "z.code,z.name,j.daizi,j.name,z.company,b.name,z.daima,z.province,z.keyun,z.huoyun,z.xianyun,z.yewu,z.status,z.page "
			+ "from T_ZhanMingLueHao z,T_BaoSuo b,T_JuWei j "
			+ "where z.baosuoId=b.id and z.juweiId=j.id and j.daizi like '%";
   public static String Find_log_SQL="select UserName,CreateTime,MSG from T_ResLog";
}
