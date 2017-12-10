package packages;

import java.sql.Connection;
///import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.sql.*;
//import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.List;

//import com.mysql.fabric.jdbc.FabricMySQLDriver;

public class DataBase {

	
    public static Connection getConnection() throws SQLException {
       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root" );
       return connection;
    }
   
    public static void  insertAuthor (Author a) throws SQLException {
        Connection conn= getConnection();
        PreparedStatement statement = conn.prepareStatement("insert into author (id_author,name,surname, dateOfBirthday,country,city) values('"+a.getId()+"','"+a.getName()+"','"+a.getSurname()+"','"+a.getDateOfBirthday()+"','"+a.getCountry()+"','"+a.getCity()+"')");
        statement.execute();
        conn.close();
    }
   
    public static void insertBook(Book b) throws SQLException {
        Connection conn= getConnection();
        PreparedStatement statement = conn.prepareStatement("insert into book (id_book,name,countPages, publisher) values('"+b.getId()+"','"+b.getName()+"','"+b.getCountPages()+"','"+b.getPublisher()+"')",Statement.RETURN_GENERATED_KEYS);
        statement.execute();
        ResultSet rs = statement.getGeneratedKeys();
        rs.next();
      
       Statement st =conn.createStatement();
        conn.setAutoCommit(false);
        for(Author a: b.getListOfAuthor()){
            st.addBatch("insert into author (name,surname,dateOfBirthday,country,city,fromBook) values('" + a.getName() + "','" + a.getSurname() + "','" + a.getDateOfBirthday() + "','" + a.getCountry() + "','" + a.getCity() + "','" + b.getId() + "');");
        }
        int [] count=st.executeBatch();
        conn.commit();
    }


    public static  ArrayList<Author> getAllAuthors() throws SQLException {
        ArrayList<Author> res = new ArrayList<Author>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM author;");
        while(rs.next()){
        	Author a = new Author(rs.getInt("Id"),rs.getString("name"),rs.getString("surname"),rs.getTimestamp("dateOfBirthday").toLocalDateTime().toLocalDate(),rs.getString("country"),rs.getString("city"));
            res.add(a);
        }
        return res;
    }
    public static ArrayList<Author> getAuthorsByBookId(int id_origin) throws SQLException {
        ArrayList<Author> res = new ArrayList<Author>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM author WHERE frombook=" + id_origin + ";");
        while(rs.next()){
        	Author a = new Author(rs.getInt("Id"),rs.getString("name"),rs.getString("surname"),rs.getTimestamp("dateOfBirthday").toLocalDateTime().toLocalDate(),rs.getString("country"),rs.getString("city"));
            res.add(a);
        }
        
        return res;
    }
    public static ArrayList<Book> getAllBooks() throws SQLException {
        ArrayList<Book> res = new ArrayList<Book>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM book;");
        while(rs.next()){
            Book b= new Book(rs.getInt("id"),rs.getString("name"),rs.getInt("countPages"),rs.getString("publisher"),getAuthorsByBookId(rs.getInt("id")));
            res.add(b);
        }
        return res;
    }
    public static Book getBookById(int id) throws SQLException {
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM book WHERE id_book=" + id + ";");
        rs.next();
        Book b = new Book(rs.getInt("id_book"),rs.getString("name"),rs.getInt("countPages"),rs.getString("publisher"),getAuthorsByBookId(rs.getInt("id_author")));
        return b;
    }
    public static void clearAllAuthors() throws SQLException {
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        statement.execute("delete from mydb where id>0;");
        conn.close();
    }
    public static void clearAllBooks() throws SQLException {
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        statement.execute("delete from mydb where id>0;");
        conn.close();
    }

}