public class Person {
  private String name;
  private String surname;
  private String email;

  public void setName(String name) {
    this.name = name;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Person(String name, String surname, String email){
      this.name = name;
      this.surname = surname;
      this.email = email;
  }

  public String getName(){
    return this.name;
  }

  public String getSurname(){

    return this.surname;
  }

  public String getEmail(){

    return this.email;
  }

  //printing person info
  public void printPersonInfo(){
    System.out.println("Name is "+this.name);
    System.out.println("surname is "+this.surname);
    System.out.println("email is "+this.email);
    System.out.println();
  }

}
