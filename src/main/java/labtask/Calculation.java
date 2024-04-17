package labtask;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Calculation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int number1;
  private int number2;
  private String operation;

public void setNumber1(int number1) {
    this.number1 = number1;
}

public void setNumber2(int number2) {
    this.number2 = number2;
}

public void setOperation(String operation) {
    this.operation = operation;
}

  public Calculation() {
  }

  public Calculation(int number1, int number2, String operation) {
    this.number1 = number1;
    this.number2 = number2;
    this.operation = operation;
  }

public int getNumber1() {
    return number1;
}

public String getOperation() {

    return operation;
}

public int getNumber2() {
    
    return number2;
}



 
}