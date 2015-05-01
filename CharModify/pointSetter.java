/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CharModify;
import destiny.Kezdo;
import destiny.Kezdo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ewulusen
 */
public class pointSetter {
    
    public static int pointSetter1(String ability,int ID) {
       int point=0;
         try {
             
            Statement stmt=Kezdo.conn.conn.createStatement();
            String sql="SELECT * from KARAKTERL WHERE KAR_ID='"+ID+"'";
            //System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            rs.next();
            //String sql2="SELECT * from KARAKTERL WHERE KAR_ID='"+ID+"'";
            //System.out.println("abi: "+rs.getString(ability));
            rs.getString(ability);
            if(rs.getString(ability).isEmpty()){}
            else {
                
           point=Integer.parseInt(rs.getString(ability));
            }
        } catch (SQLException ex) {
            System.out.println("Error on SQL in pointsetter on"+ability+" :"+ex);
        }
         
      
        //System.out.println(ability+" "+point);
        return point;
    }
     public static String pointSetter2(String ability,int ID) {
       String point = null;
         try {
           
            Statement stmt=Kezdo.conn.conn.createStatement();
            String sql="SELECT * from KARAKTERL WHERE KAR_ID='"+ID+"'";
            //System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            rs.next();
           // System.out.println("abi: "+rs.getString(ability));
           point=rs.getString(ability);
            
        } catch (SQLException ex) {
            System.out.println("Error on SQL in pointsetter on"+ability+" :"+ex);
        }
         
      
        //System.out.println(ability+" "+point);
        return point;
    }
    public static void AbilityAdder() {
             CharPointSetter.ini=CharPointSetter.ref+CharPointSetter.agi;
             CharPointSetter.perce=CharPointSetter.ref+CharPointSetter.inte;
             CharPointSetter.AC=CharPointSetter.con+CharPointSetter.def;
             CharPointSetter.MAC=CharPointSetter.inte+CharPointSetter.def;
             CharPointSetter.MANA=CharPointSetter.inte*10;
             CharPointSetter.HP=CharPointSetter.con+CharPointSetter.def+CharPointSetter.str;
             CharPointSetter.MOVE=CharPointSetter.agi;
             CharPointSetter.HP_reg=CharPointSetter.HP/10;
             CharPointSetter.Mana_reg=CharPointSetter.MANA/10;
    }
    public static void AbilityAdder2() {
             CharViewer.ini=CharViewer.aref+CharViewer.aagi;
             CharViewer.perce=CharViewer.aref+CharViewer.ainte;
             CharViewer.AC=CharViewer.acon+CharViewer.adef;
             CharViewer.MAC=CharViewer.ainte+CharViewer.adef;
             CharViewer.MANA=CharViewer.ainte*10;
             CharViewer.HP=CharViewer.acon+CharViewer.adef+CharViewer.astr;
             CharViewer.MOVE=CharViewer.aagi;
             CharViewer.HP_reg=CharViewer.HP/10;
             CharViewer.Mana_reg=CharViewer.MANA/10;
    }
            
            
            
    public static int incCharPoint(int point) {
        if(CharPointSetter.kredit>0) {
            point++;
                    }
        return point;
    }
     public static int decCharPoint(int point) {
        if(CharPointSetter.kredit>0) {
            point--;
                    }
        return point;
    }
     public static void uploadChar() {
        
         try {
             AbilityAdder();
             Statement stmt=Kezdo.conn.conn.createStatement();
             String sql="UPDATE KARATERL set "
                     + "(K_STR='"+CharPointSetter.str+"'"
                     + "K_AGI='"+CharPointSetter.agi+"'"
                     + "K_REF='"+CharPointSetter.ref+"'"
                     + "K_DEF='"+CharPointSetter.def+"'"
                     + "K_DEX='"+CharPointSetter.dex+"'"
                     + "K_INT='"+CharPointSetter.inte+"'"
                     + "K_LUC='"+CharPointSetter.luck+"'"
                     + "K_CON='"+CharPointSetter.con+"'"
                     + "K_INICIATIVE='"+CharPointSetter.ini+"'"
                     + "K_PERCEPTION='"+CharPointSetter.perce+"'"
                     + "K_AC='"+CharPointSetter.AC+"'"
                     + "K_MOVE='"+CharPointSetter.MOVE+"'"
                     + "K_HP='"+CharPointSetter.HP+"'"
                     + "K_MAC='"+CharPointSetter.MAC+"'"
                     + "K_MANA='"+CharPointSetter.MANA+"'"
                     + "K_ASTR='"+CharPointSetter.str+"'"
                     + "K_AAGI='"+CharPointSetter.agi+"'"
                     + "K_AREF='"+CharPointSetter.ref+"'"
                     + "K_ADEF='"+CharPointSetter.def+"'"
                     + "K_ADEX='"+CharPointSetter.dex+"'"
                     + "K_AINT='"+CharPointSetter.inte+"'"
                     + "K_ALUC='"+CharPointSetter.luck+"'"
                     + "K_ACON='"+CharPointSetter.con+"'"
                     + "K_AINI='"+CharPointSetter.ini+"'"
                     + "K_APERC='"+CharPointSetter.perce+"'"
                     + "K_AAC='"+CharPointSetter.AC+"'"
                     + "K_AMOVE='"+CharPointSetter.MOVE+"'"
                     + "K_AHP='"+CharPointSetter.HP+"'"
                     + "K_AMAC='"+CharPointSetter.MAC+"'"
                     + "K_AMANA='"+CharPointSetter.MANA+"'"
                     + "K_HP_REG='"+CharPointSetter.HP_reg+"'"
                     + "K_MANA_REG='"+CharPointSetter.Mana_reg+"'"
                     + "K_AHP_REG='"+CharPointSetter.HP_reg+"'"
                     + "K_AMANA_REG='"+CharPointSetter.Mana_reg+"') where"
                     + "KAR_ID='"+Charakters.CharakterId+"'";

             stmt.executeUpdate(sql);
             //System.out.println(sql);
             
         } catch (SQLException ex) {
             System.out.println("Hiba a karakterfeltöltésnél: "+ex);
         }
        
     }
    public static void putDown (String mit,String jel,int ID) {
        CharSuit.Capacitivy=0;
       String splithowmany;
       String splitwhat;
       String hol=null;
       String [] exploded;
        String [] exploded1;
        String mit1;
        int mire=0;
        int segedszam;
        int AC=0;
        String seged=null;
        ResultSet rs1;
        ResultSet rs0;
         try {
            Statement stmt=Kezdo.conn.conn.createStatement();
            Statement stmt1=Kezdo.conn.conn.createStatement();
            Statement stmt0=Kezdo.conn.conn.createStatement();
            String sql="SELECT * from KARAKTERL WHERE KAR_ID='"+ID+"'";
            ResultSet rs=stmt.executeQuery(sql);
            rs.next();
       
            if (jel.equals("F")) {
                sql="SELECT * from FEGYVEREK where F_ID='"+mit+"'";
                rs1=stmt1.executeQuery(sql);
                hol="K_WEAPONS";
                rs1.next();
                splithowmany=rs1.getString("F_SZAM");
                splitwhat=rs1.getString("F_MIT");
                exploded=splithowmany.split(";");
                exploded1=exploded[0].split(",");
                segedszam=Integer.parseInt(rs.getString("K_"+splitwhat));
                seged="K_"+splitwhat;
                if(exploded[0].equals("+")) {  
                    mire=segedszam-Integer.parseInt(exploded1[1]);
                }
                else {
                    mire=segedszam+Integer.parseInt(exploded1[1]);
                    
                }
                AC=Integer.parseInt(rs.getString("K_ADEF"));
            }
            if (jel.equals("P")) {
                sql="SELECT * from PANCEL where P_ID='"+mit+"'";
                rs1=stmt1.executeQuery(sql);
                hol="K_ARMOR";
                rs1.next();
                splithowmany=rs1.getString("P_SZAM");
                splitwhat=rs1.getString("P_MIT");
                exploded=splithowmany.split(";");
                exploded1=exploded[0].split(",");
                seged="K_"+splitwhat;
                segedszam=Integer.parseInt(rs.getString("K_"+splitwhat));
                if(exploded[0].equals("+")) {  
                    mire=segedszam-Integer.parseInt(exploded1[1]);
                }
                else {
                    mire=segedszam+Integer.parseInt(exploded1[1]);
                    
                }
                AC=Integer.parseInt(rs.getString("K_ADEF"))-Integer.parseInt(rs1.getString("P_AC"));
            }
            if (jel.equals("NY")) {
                      sql="SELECT * from NYAKLANC where NY_ID='"+mit+"'";
                rs1=stmt1.executeQuery(sql);
                hol="K_NYAKLANC";
                rs1.next();
                splithowmany=rs1.getString("NY_SZAM");
                splitwhat=rs1.getString("NY_MIT");
                exploded=splithowmany.split(";");
                exploded1=exploded[0].split(",");
                seged="K_"+splitwhat;
                segedszam=Integer.parseInt(rs.getString("K_"+splitwhat));
                if(exploded[0].equals("+")) {  
                    mire=segedszam-Integer.parseInt(exploded1[1]);
                }
                else {
                    mire=segedszam+Integer.parseInt(exploded1[1]);
                    
                }
                AC=Integer.parseInt(rs.getString("K_ADEF"));
            }
            if (jel.equals("GY")) {
                      sql="SELECT * from GYURUK where GY_ID='"+mit+"'";
                rs1=stmt1.executeQuery(sql);
                rs1.next();
                hol="K_GYURU";
                splithowmany=rs1.getString("GY_SZAM");
                splitwhat=rs1.getString("GY_MIT");
                exploded=splithowmany.split(";");
                exploded1=exploded[0].split(",");
                seged="K_"+splitwhat;
                segedszam=Integer.parseInt(rs.getString("K_"+splitwhat));
                if(exploded[0].equals("+")) {  
                    mire=segedszam-Integer.parseInt(exploded1[1]);
                }
                else {
                    mire=segedszam+Integer.parseInt(exploded1[1]);
                    
                }
                AC=Integer.parseInt(rs.getString("K_ADEF"));
                
            }
              if(seged.equals("K_0")) {
             sql="UPDATE KARAKTERL set K_DEF='"+AC+"', '"+hol+"'='' where KAR_ID='"+ID+"'";
           stmt1.executeUpdate(sql);   
            }
            else {
           sql="UPDATE KARAKTERL set "+seged+"='"+mire+"', K_DEF='"+AC+"',"+hol+"='' where KAR_ID='"+ID+"'";
           stmt1.executeUpdate(sql);
           System.out.println(sql+"down");
           sql="UPDATE ITEMEK set I_ON='0' where I_FID='"+mit+"' and I_PID='"+ID+"'";
           stmt0.executeUpdate(sql);
            System.out.println(sql);
              }
            
        } catch (SQLException ex) {
            Logger.getLogger(CharSuit.class.getName()).log(Level.SEVERE, null, ex);
        }
         
      
        //System.out.println(ability+" "+point);
        
    }
    public static void putOn(String mit,String jel,int ID) {
      String splithowmany;
       String splitwhat;
       String [] exploded;
        String [] exploded1;
        String hol=null;
        int mire=0;
        int AC=0;
        int segedszam;
        String seged = null;
        ResultSet rs1;
        ResultSet rs0;
         try {
            Statement stmt=Kezdo.conn.conn.createStatement();
            Statement stmt0=Kezdo.conn.conn.createStatement();
            Statement stmt1=Kezdo.conn.conn.createStatement();
            String sql="SELECT * from KARAKTERL WHERE KAR_ID='"+ID+"'";
            ResultSet rs=stmt.executeQuery(sql);
            rs.next();
            sql="SELECT * from ITEMEK WHERE I_ID='"+mit+"'";
            rs0=stmt0.executeQuery(sql);
            rs0.next();
            mit=rs0.getString("I_FID");
            if (jel.equals("F")) {
                putDown(rs.getString("K_WEAPONS"), jel, ID);
                hol="K_WEAPONS";
                sql="SELECT * from FEGYVEREK where F_ID='"+mit+"'";
                rs1=stmt1.executeQuery(sql);
                rs1.next();
                splithowmany=rs1.getString("F_SZAM");
                splitwhat=rs1.getString("F_MIT");
                exploded=splithowmany.split(";");
                exploded1=exploded[0].split(",");
                seged="K_"+splitwhat;
                segedszam=Integer.parseInt(rs.getString("K_"+splitwhat));
                if(exploded[0].equals("-")) {  
                    mire=segedszam-Integer.parseInt(exploded1[1]);
                }
                else {
                    mire=segedszam+Integer.parseInt(exploded1[1]);
                    
                }
                AC=Integer.parseInt(rs.getString("K_ADEF"));
            }
            if (jel.equals("P")) {
                putDown(rs.getString("K_ARMOR"), jel, ID);
                sql="SELECT * from PANCEL where P_ID='"+mit+"'";
                rs1=stmt1.executeQuery(sql);
                hol="K_ARMOR";
                rs1.next();
                splithowmany=rs1.getString("P_SZAM");
                splitwhat=rs1.getString("P_MIT");
                exploded=splithowmany.split(";");
                exploded1=exploded[0].split(",");
                seged="K_"+splitwhat;
                segedszam=Integer.parseInt(rs.getString("K_"+splitwhat));
                if(exploded[0].equals("-")) {  
                    mire=segedszam-Integer.parseInt(exploded1[1]);
                }
                else {
                    mire=segedszam+Integer.parseInt(exploded1[1]);
                    
                }
                AC=Integer.parseInt(rs.getString("K_ADEF"))-Integer.parseInt(rs1.getString("P_AC"));
            }
            if (jel.equals("NY")) {
                putDown(rs.getString("K_NYAKLANC"), jel, ID);
                hol="K_NYAKLANC";
                sql="SELECT * from NYAKLANC where NY_ID='"+mit+"'";
                rs1=stmt1.executeQuery(sql);
                rs1.next();
                splithowmany=rs1.getString("NY_SZAM");
                splitwhat=rs1.getString("NY_MIT");
                exploded=splithowmany.split(";");
                exploded1=exploded[0].split(",");
                seged="K_"+splitwhat;
                segedszam=Integer.parseInt(rs.getString("K_"+splitwhat));
                if(exploded[0].equals("-")) {  
                    mire=segedszam-Integer.parseInt(exploded1[1]);
                }
                else {
                    mire=segedszam+Integer.parseInt(exploded1[1]);
                    
                }
                AC=Integer.parseInt(rs.getString("K_ADEF"));
            }
            if (jel.equals("GY")) {
                putDown(rs.getString("K_GYURU"), jel, ID);
                hol="K_GYURU";
                sql="SELECT * from GYURUK where GY_ID='"+mit+"'";
                rs1=stmt1.executeQuery(sql);
                rs1.next();
                splithowmany=rs1.getString("GY_SZAM");
                splitwhat=rs1.getString("GY_MIT");
                exploded=splithowmany.split(";");
                exploded1=exploded[0].split(",");
                seged="K_"+splitwhat;
                segedszam=Integer.parseInt(rs.getString("K_"+splitwhat));
                if(exploded[0].equals("-")) {  
                    mire=segedszam-Integer.parseInt(exploded1[1]);
                }
                else {
                    mire=segedszam+Integer.parseInt(exploded1[1]);
                    
                }
                AC=Integer.parseInt(rs.getString("K_ADEF"));
                
            }
            if(seged.equals("K_0")) {
             sql="UPDATE KARAKTERL set K_DEF='"+AC+"' where KAR_ID='"+ID+"'";
           stmt1.executeUpdate(sql);   
            }
            else {
           sql="UPDATE KARAKTERL set "+seged+"='"+mire+"', K_DEF='"+AC+"' ,"+hol+"='"+mit+"' where KAR_ID='"+ID+"'";
           
           stmt1.executeUpdate(sql);
           sql="UPDATE ITEMEK set I_ON='1' where I_FID='"+mit+"' and I_PID='"+ID+"'";
           stmt1.executeUpdate(sql);
            System.out.println(sql);
            }
        } catch (Exception ex) {
            System.out.println("Error on SQL puton "+ex);
        }
       
    }
}

    

