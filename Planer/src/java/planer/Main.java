/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSProducer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entiteti.Alarm;
import entiteti.Obaveza;
import entiteti.Destinacija;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.persistence.Query;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Dusan
 */
public class Main {

    @Resource(lookup = "Queue")
    static Queue queue;
    @Resource(lookup = "Topic")
    static Topic topic;
    @Resource(lookup = "jms/__defaultConnectionFactory")
    static ConnectionFactory connectionFactory;
    
    private static Obaveza obaveza;
    private static Destinacija trenutnaLokacija;
    private static double brzina = 8.33;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlanerPU");
        EntityManager em = emf.createEntityManager();
        System.out.println("Startovan planer ");
        
        JMSContext context = connectionFactory.createContext();
        context.setClientID("Planer");
        JMSConsumer consumer = context.createDurableConsumer(topic, "Planer", "id = 3", false);
        JMSProducer producer = context.createProducer();
        
        trenutnaLokacija = new Destinacija();
        trenutnaLokacija.setXKoordinata(6);
        trenutnaLokacija.setYKoordinata(0);
        trenutnaLokacija.setNaziv("Mirijevo");
        
        //dodajObavezu("2019-02-12 23:25:00", "Ispracaj na autobusku stanicu", 6, -4, "Mali Mokri Lug", 0);
        //izlistajObaveze(1,3);
        azurirajObavezu("Bacanje smeca", "2020-05-13 09:00:00", "Akapulko", 200, 300);
        
        while(true){
         //   int sekunde = (int)(Math.random() * 6 + 5);
            Message message = consumer.receive();
            
            if(message instanceof ObjectMessage){
                try {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    Alarm alarm = (Alarm)objectMessage.getObject();
                    obaveza.setIdA(alarm);
                    
                    em.getTransaction().begin();
                    em.persist(obaveza);
                    em.getTransaction().commit();
            //       Zadatak zadatak = (Zadatak) objectMessage.getObject();
            //        String imeZadatka = objectMessage.getStringProperty("imeZadatka");
            //        int resenje = zadatak.radi();
                    
                    Thread.sleep(100);
                    
                /*    TextMessage textMessage = context.createTextMessage();
                    textMessage.setText("Resenje: " + resenje);
                    textMessage.setIntProperty("idR", idR);
                    textMessage.setStringProperty("imeZadatka", imeZadatka);
                    producer.send(queue, textMessage); */
                    System.out.println("Primljen je alarm sa pocetkom '" + alarm.getVremeAktivacije() + "' i sa nazivom pesme: " + alarm.getIdP().getNaziv());
                } catch (JMSException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        // TODO code application logic here
    }
    private static void postaviBrzinu(double b){
        brzina = b;
    }
    private static String izracunajVreme(java.util.Date date, Destinacija pocetna, Destinacija krajnja){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        double s = Math.sqrt( Math.pow(pocetna.getYKoordinata()-krajnja.getYKoordinata(), 2) + Math.pow(pocetna.getXKoordinata()-krajnja.getXKoordinata(), 2) );
        s = s * 500;
        int t = (int)( s/brzina );
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND,-t);
        return df.format( cal.getTime() );
    }
    
    
    private static void dodajObavezu(String vreme, String naziv_obaveze, double x, double y, String naziv_destinacije, long perioda){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlanerPU");
        EntityManager em = emf.createEntityManager();
        JMSContext context = connectionFactory.createContext();
        context.setClientID("Planer1");
        JMSProducer producer = context.createProducer();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        
        try {
            java.util.Date date = format.parse(vreme);
        
        Query query = em.createQuery("SELECT d FROM Destinacija as d WHERE d.naziv = :name");
        query.setParameter("name",naziv_destinacije);
        if(query.getResultList().isEmpty()){    
            Destinacija destinacija = new Destinacija();
            destinacija.setXKoordinata(x);
            destinacija.setYKoordinata(y);
            destinacija.setNaziv(naziv_destinacije);

            em.getTransaction().begin();
            em.persist(destinacija);
            em.getTransaction().commit();
        }
        query = em.createQuery("SELECT d FROM Destinacija as d WHERE d.naziv = :name");
        query.setParameter("name",naziv_destinacije);
        Destinacija rezultat = (Destinacija)query.getResultList().get(0);
        
        obaveza = new Obaveza();
        obaveza.setNaziv(naziv_obaveze);
        obaveza.setVremePocetka(date);
        obaveza.setIdD(rezultat);
        
        String novo_vreme = izracunajVreme(date, trenutnaLokacija, rezultat);
        trenutnaLokacija = rezultat;
        
        TextMessage textMessage = context.createTextMessage();
        textMessage.setText(novo_vreme);
        textMessage.setIntProperty("id", 2);
        textMessage.setLongProperty("perioda", perioda);
        producer.send(topic, textMessage);   
        System.out.println("Poslat je zahtev za alarm sa pocetkom: "+novo_vreme+" i periodom: "+ perioda);
     
        context.close();
        em.close();
        emf.close();
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (JMSException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private static void ukloniObavezu(String naziv_obaveze){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlanerPU");
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT o FROM Obaveza as o WHERE o.naziv = :name");
        query.setParameter("name", naziv_obaveze);
        if(!query.getResultList().isEmpty()){
            em.getTransaction().begin();
            em.remove( query.getResultList().get(0) );
            em.getTransaction().commit();
        }
        
        em.close();
        emf.close();
    }
    private static void izlistajObaveze(int pageIx, int pageSize){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlanerPU");
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT o FROM Obaveza as o");
        List<Obaveza> results = query.setFirstResult(pageIx * pageSize).setMaxResults(pageSize).getResultList();
        
        for(Obaveza o: results){
            System.out.println(o.getObavezaId()+" "+o.getNaziv()+" "+o.getVremePocetka()+" "+o.getIdD()+" "+o.getIdA());
        }
        
        em.close();
        emf.close();
    }
    private static void azurirajObavezu(String naziv_obaveze, String novo_vreme, String naziv_destinacije, double x, double y){
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlanerPU");
            EntityManager em = emf.createEntityManager();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            
            Query query = em.createQuery("SELECT o FROM Obaveza as o WHERE o.naziv = :name");
            query.setParameter("name", naziv_obaveze);
            Obaveza rezultat = (Obaveza)query.getResultList().get(0);
            
            query = em.createQuery("SELECT d FROM Destinacija as d WHERE d.naziv = :name");
            query.setParameter("name", naziv_destinacije);
            
            if(query.getResultList().isEmpty()){
                Destinacija destinacija = new Destinacija();
                destinacija.setXKoordinata(x);
                destinacija.setYKoordinata(y);
                destinacija.setNaziv(naziv_destinacije);
                
                em.getTransaction().begin();
                em.persist(destinacija);
                em.getTransaction().commit();
            }
            
            query = em.createQuery("SELECT d FROM Destinacija as d WHERE d.naziv = :name");
            query.setParameter("name", naziv_destinacije);
            
            Destinacija rezultat2 = (Destinacija)query.getResultList().get(0);
            
            em.getTransaction().begin();
            rezultat.setVremePocetka( format.parse(novo_vreme) );
            rezultat.setIdD(rezultat2);
            em.getTransaction().commit();
            
            em.close();
            emf.close();
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
