
package mysqlconnect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DB {
    /** Adatbázis kapcsolathoz szükséges paraméterek */
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String URL= "jdbc:mysql://localhost:3306/nwind13ke";
    final String USER="root";
    final String PASSWORD="";
    
    private Connection conn = null;
    private Statement createStatement = null;
    private DatabaseMetaData dbmd = null;
    
    
    public DB() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //System.out.println("Kapcsolat létrejött");
        } catch (SQLException ex) {
            System.out.println("Gáz van");
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (conn != null) {
            try {
                createStatement = conn.createStatement();
                //System.out.println("Statement létrejött");
            } catch (SQLException ex) {
                System.out.println("Gáz van ");
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            dbmd = conn.getMetaData();
            //System.out.println("Metadata létrejött");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Metadata gáz");
        }
        
        
    }
    public void showTable() {
        String sql = "select * from alkalmazottak";
        try {
            
            ResultSet rs = createStatement.executeQuery(sql);
            while (rs.next()){
                String title = rs.getString("Megszólítás");
                String firstName = rs.getString("Vezetéknév");
                String lastName = rs.getString("Keresztnév");
            System.out.println(title + " " + firstName + " " + lastName);
            }
            } catch (SQLException ex) {
                System.out.println("Kiírás gáz");
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}

