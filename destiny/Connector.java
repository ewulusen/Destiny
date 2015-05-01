/**
 *connect to the jdbc
 * @author ewulusen
 */
package destiny;
import java.sql.*;
public class Connector  {
   public Connection conn;
   public static String waiter="no"; 
    public static Connection conn2;
    public  Connector() {

try{
    Class.forName("com.mysql.jdbc.Driver");
    //"jdbc:mysql://sql2.ultraweb.hu:3306/ewulusen","ewulusen","2595573"
    //"jdbc:mysql://sql3.freemysqlhosting.net:3306/sql369942","sql369942","rR3%sJ2!"
    conn = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql369942","sql369942","rR3%sJ2!");
   System.out.println("Database Ready");
   waiter="yes";
}

   catch (Exception ex) {
   System.out.println("Error on connection: "+ex);
   }
}
    
        public  static Connection Connector2() {

try{
    Class.forName("com.mysql.jdbc.Driver");
    //"jdbc:mysql://sql3.freemysqlhosting.net:3306/sql369942","sql369942","rR3%sJ2!"
    conn2 = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql369942","sql369942","rR3%sJ2!");
   System.out.println("Database Ready");
}

   catch (Exception ex) {
   System.out.println("Error on connection: "+ex);
   }
return conn2;
}


}
