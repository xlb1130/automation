package groovy

import groovy.sql.Sql

 class GrovvySystemConfigRead {  
  
     static String getInputString(){  
         def sql = Sql.newInstance(
                 "jdbc:oracle:thin:@127.0.0.1:1521:mydb",
                 "dbuser",
                 "oracle_1234",
                 "oracle.jdbc.driver.OracleDriver")
         println("执行查询语句")
	    sql.connection.autoCommit = false
	    
	    String getOldTokenSql = "select * from ( select t.telnum,t1.para from eshop.smnotify t,eshop.smnotify_para"+
	  		 " t1 where t1.formnum = t.formnum and t.telnum = '18795097364'  order by t.senddate desc)where rownum=1"
	    String para = "1"
	    sql.eachRow(getOldTokenSql) { 
	        para = it.para
	    }  
        return para
     }  
}  