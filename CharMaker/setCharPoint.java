/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CharMaker;

import destiny.Kezdo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ewulusen
 */
public class setCharPoint {
    
    public static int pointSetter(String ability) {
        int point=0;
        String splitableAby;
        String [] exploded;
        String [] exploded1;
  
         try {
           
            Statement stmt=Kezdo.conn.conn.createStatement();
            String sql="SELECT * from KASZTOK WHERE F_FAJ='"+CharMake.selectedCharKaszt+"'";
            ResultSet rs=stmt.executeQuery(sql);
            rs.next();
            splitableAby=rs.getString("F_"+ability);
            exploded=splitableAby.split(",");
           
                if (exploded[0].equals("+")) {
                     
                    point=point+Integer.parseInt(exploded[1]);
                }
                else {
                    
                    point=point-Integer.parseInt(exploded[1]);
                }
            
            Statement stmt1=Kezdo.conn.conn.createStatement();
            String sql1="SELECT * from FAJOK WHERE F_FAJ='"+CharMake.selectedCharFaj+"'";
            ResultSet rs1=stmt1.executeQuery(sql1);
            rs1.next();
                splitableAby=rs1.getString("F_"+ability);
            exploded1=splitableAby.split(",");
             
                if (exploded1[0].equals("+")) {
                    
                   
                    point=point+Integer.parseInt(exploded1[1]);
                }
                else {
                    
                    point=point-Integer.parseInt(exploded1[1]);
                }
            
        } catch (SQLException ex) {
            System.out.println("Error on SQL :"+ex);
        }
         
      
        //System.out.println(ability+" "+point);
        return point;
    }
    
    public static int incCharPoint(int point) {
        if(CharMaker.CharPointSet.kredit>0) {
            point++;
                    }
        return point;
    }
     public static int decCharPoint(int point) {
        if(CharMaker.CharPointSet.kredit>0) {
            point--;
                    }
        return point;
    }
     public static void uploadChar() {
        int adef=0;
         try {
             CharPointSet.ini=CharPointSet.ref+CharPointSet.agi;
             CharPointSet.perce=CharPointSet.ref+CharPointSet.inte;
             CharPointSet.AC=CharPointSet.con+CharPointSet.def;
             CharPointSet.MAC=CharPointSet.inte+CharPointSet.def;
             CharPointSet.MANA=CharPointSet.inte*10;
             CharPointSet.HP=CharPointSet.con+CharPointSet.def+CharPointSet.str;
             CharPointSet.MOVE=CharPointSet.agi;
             CharPointSet.HP_reg=CharPointSet.HP/10;
             CharPointSet.Mana_reg=CharPointSet.MANA/10;
             adef=CharPointSet.def+4;
             Statement stmt=Kezdo.conn.conn.createStatement();
             String sql="insert into KARAKTERL (KAR_KASZT,KAR_FAJ,K_STR,K_AGI,K_REF,K_DEF,K_DEX,K_INT,K_LUC,K_CON,K_INICIATIVE,K_PERCEPTION,K_AC,K_MOVE,K_HP,K_MAC,K_MANA,K_NEV,K_KIE,K_SZINT,K_APOINT,K_ASTR,K_AAGI,K_AREF,K_ADEF,K_ADEX,K_AINT,K_ALUC,K_ACON,K_AINI,K_APERC,K_AAC,K_AMOVE,K_AHP,K_AMAC,K_AMANA,K_HP_REG,K_MANA_REG,K_AHP_REG,K_AMANA_REG,K_WEAPONS,K_ARMOR,K_PENZ,K_ITEMS,K_APOZ) values ('"+CharMake.selectedCharKaszt+"','"+CharMake.selectedCharFaj+"','"+CharPointSet.str+"','"+CharPointSet.agi+"','"+CharPointSet.ref+"','"+adef+"','"+CharPointSet.dex+"','"+CharPointSet.inte+"','"+CharPointSet.luck+"',"+
             "'"+CharPointSet.con+"','"+CharPointSet.ini+"','"+CharPointSet.perce+"','"+CharPointSet.AC+"','"+CharPointSet.MOVE+"','"+CharPointSet.HP+"','"+CharPointSet.MAC+"','"+CharPointSet.MANA+"','"+CharPointSet.nev+"','"+Kezdo.playerID+"','1','10','"+CharPointSet.str+"','"+CharPointSet.agi+"','"+CharPointSet.ref+"','"+adef+"','"+CharPointSet.dex+"','"+CharPointSet.inte+"','"+CharPointSet.luck+"','"+CharPointSet.con+"','"+CharPointSet.ini+"','"+CharPointSet.perce+"','"+CharPointSet.AC+"','"+CharPointSet.MOVE+"','"+CharPointSet.HP+"','"+CharPointSet.MAC+"','"+CharPointSet.MANA+"',"+
             "'"+CharPointSet.HP_reg+"','"+CharPointSet.Mana_reg+"','"+CharPointSet.HP_reg+"','"+CharPointSet.Mana_reg+"','181','25','100','181,25','0,0' )";
             stmt.executeUpdate(sql);
            String sqlselect="SELECT * from KARAKTERL ORDER BY KAR_ID DESC LIMIT 1";
            ResultSet rs=stmt.executeQuery(sqlselect);
            rs.next();
             sql=("insert into ITEMEK (I_PID,I_FID,I_FAJ)"
                     + "VALUES('"+rs.getString("KAR_ID")+"','181','F')");
            // System.out.println(sql);
             stmt.executeUpdate(sql);
                 sql=("insert into ITEMEK (I_PID,I_FID,I_FAJ)"
                     + "VALUES('"+rs.getString("KAR_ID")+"','25','P')");
            // System.out.println(sql);
             stmt.executeUpdate(sql);
             
             //System.out.println(sql);
             
         } catch (SQLException ex) {
             System.out.println("Hiba a karakterfeltöltésnél: "+ex);
         }
        
     }
    
}
