package ivytech.fp.com;
import entites.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //test out querying our database and learning about jpa methods and jql
        Patient patient = new Patient();
        //to connect to a database we have to create an EntityManagerFactory
        //this factory makes multiple instances of an entity manager that allow for multiple connections
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        //Entitymanger has a bunch of cool built-in methods we can use in order to not have to write sql
        //more complicated queries will have to be used with either sql or JPA version of sql called JPQL
        PatientQueries pqueries = new PatientQueries(em);

        List<Patient> patients = pqueries.findAll();
        patients.forEach(p -> System.out.println(p.toString()));




        Patient savePatient = new Patient();
        savePatient.setFName("Jimmy");
        savePatient.setLName("Jones");
        savePatient.setAge(25);

        pqueries.save(savePatient);




        Doctor doctor = new Doctor();
        doctor = em.find(Doctor.class, 1);

        Patient newDrPatient = new Patient();
        newDrPatient.setFName("Jordan");
        newDrPatient.setLName("Jones");
        newDrPatient.setAge(80);
        newDrPatient.setDr(doctor);
        pqueries.save(newDrPatient);

        Patient courtney = pqueries.findById(4);
        System.out.println(courtney.getDr().getFName());














    }
}
