package labtask;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalculationResource {

    @PersistenceContext(unitName = "hello")
    private EntityManager entityManager;
    
    @POST
    @Path("calc")
    public Response createCalculation(Calculation calculation) {
        try {
            int result = performCalculation(calculation.getNumber1(), calculation.getNumber2(), calculation.getOperation());
            entityManager.persist(calculation);

            // Construct JSON response with the "Result" field
            String jsonResponse = "{\"Result\":" + result + "}";
            return Response.ok(jsonResponse).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().type(MediaType.APPLICATION_JSON).build();
        }
    }



    @GET
    @Path("calculations")
    public Response getAllCalculations() {
        try {
            List<Calculation> calculations = entityManager.createQuery("SELECT c FROM Calculation c", Calculation.class).getResultList();
            return Response.ok(calculations).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
        }
    }

    private int performCalculation(int number1, int number2, String operation) {
        switch (operation) {
          case "+":
            return number1 + number2;
          case "-":
            return number1 - number2;
          case "*":
            return number1 * number2;
          case "/":
            if (number2 == 0) {
              throw new IllegalArgumentException("Division by zero");
            }
            return number1 / number2;
          default:
            throw new IllegalArgumentException("Invalid operation");
        }
      }

}