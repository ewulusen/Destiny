/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dungeon;

import static Dungeon.CharSelector.DungeonID;
import static Dungeon.CharSelector.Hexa;
import static Dungeon.CharSelector.szint;
import destiny.Kezdo;
import java.net.URL;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ewulusen
 */
public class DungeonMaker {
static Statement stmt;
static String sql;
static ResultSet rs;

public static int DungeonID(int Charid) throws SQLException 
{
int id=0;
int counter=0;
int random;
List<String> list = new ArrayList<>();
int szint=1;
stmt=Kezdo.conn.conn.createStatement();
sql="SELECT * from KARAKTERL WHERE KAR_ID='"+Charid+"'";
rs=stmt.executeQuery(sql);
rs.next();
szint=Integer.parseInt(rs.getString("K_SZINT"));
sql="SELECT * from DUNGEON WHERE LVL<='"+szint+"'";
rs=stmt.executeQuery(sql);

while(rs.next()) {
   counter++; 
   list.add(rs.getString("D_ID"));
}
Random rn = new Random();
random=rn.nextInt(counter);
id=Integer.parseInt(list.get(random));
return id;
}


////////////////////////////////////////////////////////////
public static int DungeonFloor() {
int floor=0;
Random rn = new Random();
floor = rn.nextInt(8)+1;
return floor;
}
/////////////////////////////////////////////////////////
public static String[][] DungeonHexaCalbirate(int id,int szint,int x,int y) throws SQLException {
//a dungeon ID je kell neki és egy lvl hogy hol kezdi
String[][] Hexa = new String[40][40];
int floor=0;
int meret;
String szornyid;
Random rn = new Random();
stmt=Kezdo.conn.conn.createStatement();
sql="SELECT * from DUNGEON WHERE D_ID='"+id+"'";
rs=stmt.executeQuery(sql);
rs.next();
szornyid=rs.getString("D_SZORNY"+szint);
meret=Integer.parseInt(rs.getString("D_MERET"+szint));
int a=rn.nextInt(meret);
int b=rn.nextInt(meret);
int db=rn.nextInt(5)+1;
int terep;
int terep2;

//System.out.println(rs.getString("D_SZORNY"+szint));
if(rs.getString("D_SZINT"+szint).equals("0")) {
    Hexa[1][1]="0";
    return Hexa;
}
else {
    
     for(int i=0;i<meret+1;i++) {
         for(int j=0;j<meret+1;j++) {
             if (i==0) {
                Hexa[i][j]="s,"+j;  
             }
             if(j==0) {
                 Hexa[i][j]="s,"+i;;
             }
             if(i==x && j==y) {
                Hexa[i][j]="p,"+CharSelector.CharakterId; 
             }
             terep2=rn.nextInt(10)+1;           
             if(i==a && j==b){
	Hexa[i][j]="m,"+szornyid;
 for(int k=1;k<=db-1;k++)
	{
	if (j<meret/2){
	Hexa[i][j+k]="m,"+szornyid;
		}
	 else{
	Hexa[i][j-k]="m,"+szornyid;
	 }
	}
	}//szörny helye
	if(terep2 % 6<1 && Hexa[i][j]==null){
            terep=rn.nextInt(6)+1;
			Hexa[i][j]="b,"+terep;
}//akadály
	else{
            if(Hexa[i][j]==null) {Hexa[i][j]="a,"+CharSelector.DungeonFloor; }}
		//terep
		
	}//forbelső
}//forküls
             
      
  return Hexa;
}


}

///////////////////////////////////////////////////////////////
public static String DungeonPainter(String[][] Hexa) throws SQLException {
String  html=null;
String path;
path=DungeonMaker.class.getProtectionDomain().getCodeSource().getLocation().toString();
Random rn = new Random();
StringBuilder sb = new StringBuilder();
String trimed[];
int meret=Hexa.length;
       sb.append("<html>");
       sb.append("<table>");
       
/*if(Hexa[1][1].equals("0"))
    return html="0";
else {*/
//System.out.println(Hexa[1][1]);
	for(int i=0;i<meret;i++) {
		 sb.append("<tr>");
		for(int j=0;j<meret;j++) { 
                    if(Hexa[i][j]==null){
                    }
                    else{
                   trimed=Hexa[i][j].split(",");   
			//System.out.println(trimed[0]);
                   if (trimed[0].contains("s") ) {
               sb.append("<td> "+trimed[1]);
               
             }
             
           
		if(trimed[0].contains("a")) {
			sb.append("<td><img src="+path+"DungeonImages/a"+trimed[1]+".jpg width=50 height=50>");
		}
		
                    
		if(trimed[0].equals("b")) {
			sb.append("<td><img src="+path+"DungeonImages/b"+trimed[1]+".jpg width=50 height=50>");
                                }
		if(trimed[0].equals("p")){
                    
            Statement stmt=destiny.Kezdo.conn.conn.createStatement();
            String sql="SELECT * from KARAKTERL WHERE K_KIE='"+trimed[1]+"'";
            ResultSet rs=stmt.executeQuery(sql);
                rs.next();
		sb.append( "<td><img src="+path+"images/"+rs.getString("KAR_FAJ")+rs.getString("KAR_KASZT")+"g.gif width=50 height=50>");
		
		}
                if(trimed[0].equals("m")){
		sb.append( "<td>szörny"+trimed[1]);
                }
                }
                    //System.out.println("nem jött be");
		}//forbelső
                  sb.append( "<tr>");
	}//forkülső
                
                        

sb.append( "</table>");

//}
html=sb.toString();
//System.out.println(html);
return html;
}
/////////////////////////////////////////////////////////////////////////////
public static String[][] DungeonHexaReCalbirate(String[][] Hexa,String direction) throws SQLException {
//egy Hexa kell neki és arréb léptet egy embert
//System.out.println("bejött ide az a fügvénybe be");

    int meret=Hexa.length;
    int oszlop=0;
    int sor=0;
    String trimed[];
     for(int i=1;i<meret;i++) {
         for(int j=1;j<meret;j++) {
             if(Hexa[i][j]==null){
                    }
                    else{
             trimed=Hexa[i][j].split(",");
             if (trimed[0].equals("p")){
                 oszlop=i;
                 sor=j;
                  break;
                
             }
         }
         }
     }
             switch (direction) {
                 case "u": 
                     trimed=Hexa[oszlop-1][sor].split(",");
                     if (oszlop-1>0 && trimed[0].equals("a")) {
                     Hexa=DungeonMaker.DungeonNewHexaCalbirate(Hexa,oszlop-1,sor);
                 }
                     break;
                 case "d": 
                     trimed=Hexa[oszlop+1][sor].split(",");
                     if (oszlop+1<meret && trimed[0].equals("a")) {
                         System.out.println("bejött ide a d be");
                     Hexa=DungeonMaker.DungeonNewHexaCalbirate(Hexa,oszlop+1,sor);
                     }
                     break;
                 case "l": 
                     trimed=Hexa[oszlop][sor-1].split(",");
                     if (sor-1>0 && trimed[0].equals("a")) {
                         System.out.println("bejött ide a d be");
                     Hexa=DungeonMaker.DungeonNewHexaCalbirate(Hexa,oszlop,sor-1);
                     }
                     break; 
                 case "r": 
                     trimed=Hexa[oszlop][sor+1].split(",");
                     if (sor+1<meret && trimed[0].equals("a")) {
                         System.out.println("bejött ide a d be");
                     Hexa=DungeonMaker.DungeonNewHexaCalbirate(Hexa,oszlop,sor+1);
                     }
                  break;    
                     
           
	}//switch case

             
      
  return Hexa;
}
///////////////////////////////////////////////////////////////
public static String[][] DungeonNewHexaCalbirate(String[][] Hexa,int x,int y) throws SQLException {
//a dungeon ID je kell neki és egy lvl hogy hol kezdi

int meret=Hexa.length;
String[] trimed;
//System.out.println(rs.getString("D_SZORNY"+szint));

    
     for(int i=1;i<meret;i++) {
         for(int j=1;j<meret;j++) {
             if(Hexa[i][j]==null){
                    }
                    else{
             trimed=Hexa[i][j].split(",");
             if (trimed[0].equals("p")){
                 Hexa[i][j]="a,"+CharSelector.DungeonFloor;
             }
                 
             if(i==x && j==y) {
                Hexa[i][j]="p,"+CharSelector.CharakterId; 
             }
         }
         }
         }
  return Hexa;
}
public static void ConstitutCalibrate(int id) throws SQLException {
    Statement stmt=destiny.Kezdo.conn.conn.createStatement();
    String sql="SELECT * from KARAKTERL WHERE KAR_ID='"+id+"'";
    ResultSet rs=stmt.executeQuery(sql);
    rs.next();
    Quest.HP=Integer.parseInt(rs.getString("K_AHP"));
    Quest.MANA=Integer.parseInt(rs.getString("K_AMANA"));
    Quest.MAC=Integer.parseInt(rs.getString("K_AMAC"));
    Quest.MOVE=Integer.parseInt(rs.getString("K_AMOVE"));
    Quest.AC=Integer.parseInt(rs.getString("K_AAC"));
    Quest.agi=Integer.parseInt(rs.getString("K_AAGI"));
    Quest.str=Integer.parseInt(rs.getString("K_ASTR"));
    Quest.def=Integer.parseInt(rs.getString("K_ADEF"));
    Quest.dex=Integer.parseInt(rs.getString("K_ADEX"));
    Quest.HP_reg=Integer.parseInt(rs.getString("K_AHP_REG"));
    Quest.Mana_reg=Integer.parseInt(rs.getString("K_AMANA_REG"));
    Quest.con=Integer.parseInt(rs.getString("K_ACON"));
    Quest.ini=Integer.parseInt(rs.getString("K_AINI"));
    Quest.inte=Integer.parseInt(rs.getString("K_AINT"));
    Quest.perce=Integer.parseInt(rs.getString("K_APERC"));
    Quest.ref=Integer.parseInt(rs.getString("K_AREF"));
    Quest.luck=Integer.parseInt(rs.getString("K_ALUC"));
    Quest.AHP=Integer.parseInt(rs.getString("K_AHP"));
    Quest.AMANA=Integer.parseInt(rs.getString("K_AMANA"));
    Quest.AMOVE=Integer.parseInt(rs.getString("K_AMOVE"));
    
    
    
}
}
