package packages;
  
import java.time.LocalDate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Author implements Comparable<Author> {

	private int id;
	private String name;
	private String surname;
	private LocalDate dateOfBirthday;
	private String country;
	private String city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public LocalDate getDateOfBirthday() {
		return dateOfBirthday;
	}

	public void setDateOfBirthday(LocalDate dateOfBirthday) {
		this.dateOfBirthday = dateOfBirthday;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Author(int id, String name, String surname, LocalDate dateOfBirthday, String country, String city) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dateOfBirthday = dateOfBirthday;
		this.country = country;
		this.city = city;
	}

	public Author() {
	}
	

	@Override
	public String toString() {
		return id + " " + name + " " + surname + " " + dateOfBirthday + " " + country + " " + city;
	}
	
	

	@Override
	public boolean equals(Object object) {
		if ((object instanceof Author) && ((((Author) object).getId()) == this.id)
				&& (((Author) object).getName() == this.name) && ((((Author) object).getSurname() == this.surname))
				&& ((((Author) object).getDateOfBirthday() == this.dateOfBirthday))
				&& ((((Author) object).getCountry() == this.country)) && ((((Author) object).getCity() == this.city))) {
			return true;
		} else {
			return false;
		}
	
	
	}
	
	public static class AuthorBuilder{
		private int id;
		private String name;
		private String surname;
		private LocalDate dateOfBirthday;
		private String country;
		private String city;
		
		public AuthorBuilder(String name) {
			this.name = name;
		}
		
		public AuthorBuilder setId(int id) {
			this.id = id;
			return this;
		}
		
		public AuthorBuilder setSurname(String surname) {
			this.surname = surname;
			return this;
		}
		
		public AuthorBuilder setDateOfBirthday(LocalDate dateOfBirthday) {
			this.dateOfBirthday = dateOfBirthday;
			return this;
		}
		
		public AuthorBuilder setCountry(String country) {
			this.country = country;
			return this;
		}
		
		public AuthorBuilder setCity(String city) {
			this.city = city;
			return this;
		}
	
		
		Author build() {
			Author author = new Author();
			
			Pattern namePattern = Pattern.compile("^[a-zA-Z]{1,15}$");
			
			
			Matcher nameMatch = namePattern.matcher(this.name);
			Matcher surnameMatch = namePattern.matcher(this.surname);
			Matcher countryMatch = namePattern.matcher(this.country);
			Matcher cityMatch = namePattern.matcher(this.city);
			
			if(!(nameMatch.matches()))
				throw new IllegalArgumentException("Enter correct name.");
			if(!(surnameMatch.matches()))
				throw new IllegalArgumentException("Enter correct surname.");
			if(!(countryMatch.matches()))
				throw new IllegalArgumentException("Enter correct country");
			if(!(cityMatch.matches()))
				throw new IllegalArgumentException("Enter correct city.");
			if (this.id < 1)
				throw new IllegalArgumentException("id must be a positive integer number");
			
			
			author.setName(name);
			author.setId(id);
			author.setSurname(surname);
			author.setDateOfBirthday(dateOfBirthday);
			author.setCountry(country);
			author.setCity(city);
			
			return author;
		}
		
		
	}

	@Override
	public int compareTo(Author o) {
	
		return 0;
	}


}

