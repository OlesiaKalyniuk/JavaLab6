package packages;

import java.sql.SQLException;
import java.time.LocalDate;


public class Main {
	public static void main(String[] args) throws SQLException {
        Author a= new Author(8,"William", "Shekspier", LocalDate.of(2017, 2, 7), "Ukraine", "Chernivtsi");
        Author a2= new Author(9,"William", "Shekspier", LocalDate.of(2017, 2, 7), "USA", "New York");
        //Author a3= new Author(3,"Lesia", "Ukrainka", LocalDate.of(2017, 2, 7), "Ukraine", "Morentci");
        Book b = new Book(22, "Hamlet",260,"World book");
        b.addAuthor(a);
        b.addAuthor(a2);
        //b.addAuthor(a3);
        
	
	
    DataBase.insertBook(b);
    //Book bk = DataBase.getBookById(10);
    // System.out.println(bk.toString());

	}
}
