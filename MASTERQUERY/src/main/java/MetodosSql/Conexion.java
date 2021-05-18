package MetodosSql;
 


import java.sql.*;

 
 
 
public class Conexion {
     
 
        private  Connection c;
        protected  Statement statemente;
        protected  ResultSet resulsete;
        private static String user="admin";
        private static String pass="admin";
        private static String host="localhost";
        private static String base="reportero";
        private static String cadena="jdbc:mysql://"+host+"/"+base;
        
       
        private static String driver="com.mysql.cj.jdbc.Driver";
        //Para sql server -> com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
           
    
        
        public static String getUser() {
			return user;
		}



		public void setUser(String user) {
			Conexion.user = user;
		}



		public static String getPass() {
			return pass;
		}



		public void setPass(String pass) {
			Conexion.pass = pass;
		}



		public Conexion(){
             
        }
         
         
        
        public boolean conectar(){
        	//String server,String database,String usuario,String password
        	boolean conecto;
        	try{
        		//Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/prueba","root", "la_clave");
            	
            //	cadena="jdbc:mysql://localhost/prueba","root", "la_clave";
            	//"jdbc:sqlserver://"+server+";user="+usuario+";password="+password+";database="+database+"";
            	
                Class.forName(driver);
                c=DriverManager.getConnection(cadena,getUser(),getPass());
                statemente=c.createStatement();       
                 
                conecto=true;
                
            }catch(ClassNotFoundException e1){
            	
             System.out.println("Error en los drivers");
             conecto=false;
            }
            catch(SQLException e2){
            	 System.out.println(e2.getMessage());
             
                
               
                conecto=false;
 
            }catch(Exception e3){
            	System.out.println(e3.getMessage());
            	
            	
                 conecto=false;
            	
            }
 return conecto;
    }
        
        public  void desconectar(){
            //estado=new JTextField();
             
                try {
                    if (c != null){
                        c.close();
                       
                         
                         
                         
                    //  System.out.println("Conexi�n liberada OK");
                    }
                    else{
                        System.out.println("Ya estaba desconectado");
                         
                    }
                     
                    //statemente.close();
                     
                     
                     
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                	
                    System.out.println("Desconectado incorrecto");
                    e.printStackTrace();
                }
                 
             
        }



		public Connection getC() {
			return c;
		}



		public static String getServer() {
			// TODO Auto-generated method stub
			return null;
		}
}