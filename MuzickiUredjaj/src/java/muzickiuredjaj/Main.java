/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muzickiuredjaj;
import java.lang.Runtime;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.ProcessBuilder;
import java.lang.Process;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entiteti.Pesma;
import entiteti.Korisnik;
import entiteti.Slusao;

import java.util.Timer;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.Format;
import java.util.Locale;
import java.util.TimerTask;
//import java.util.Date;
/**
 *
 * @author Dusan
 */


public class Main {
    
 /*   private static class MyTimeTask extends TimerTask
{

    public void run()
    {
        String s = "daft punk ouverture";
                try {
                    Runtime.getRuntime().exec("java -jar youtube.jar "+s);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }       
    }
    
} */
    private static Korisnik korisnickiId;
       
    
    @Resource(lookup = "Queue")
    static Queue queue;
    @Resource(lookup = "Topic")
    static Topic topic;
    @Resource(lookup = "jms/__defaultConnectionFactory")
    static ConnectionFactory connectionFactory;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args1) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MuzickiUredjajPU");
        EntityManager em = emf.createEntityManager();
        
       // int idR = Integer.parseInt(args[0]);
        System.out.println("Startovan muzicki uredjaj ");
        
        JMSContext context = connectionFactory.createContext();
        context.setClientID("MuzickiUredjaj");
        JMSConsumer consumer = context.createDurableConsumer(topic, "MuzickiUredjaj", "id = 1", false);
        JMSProducer producer = context.createProducer();
        
        while(true){
    //        int sekunde = (int)(Math.random() * 6 + 5);
            

        /*        String string = "2019-02-12 01:34:00";
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                    try {
                        java.util.Date date = format.parse(string);
                        java.util.Date date2 = format.parse("2019-02-12 01:36:00");
                        System.out.println(date);
                   
                        Timer timer = new Timer();
                        
                        timer.schedule(new MyTimeTask(), date);
                        timer.schedule(new MyTimeTask(), date2);
                    } catch (ParseException ex) {}
                    */
                setKorisnik("Dusan"); 
                
                
        /*    if(true){
                String s = "daft punk ouverture";
                try {
                    Runtime.getRuntime().exec("java -jar youtube.jar "+s);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }           
                break;
            }    */
                    
            Message message = consumer.receive();
            
            if(message instanceof TextMessage){
                try {
              //      ObjectMessage objectMessage = (ObjectMessage) message;
              //      Zadatak zadatak = (Zadatak) objectMessage.getObject();
                    TextMessage textMessage = (TextMessage) message;
                    String imePesme = textMessage.getText();
                    
                    reprodukcija(imePesme);
                  /*  String string = "2019-02-11 17:00:00";
                    DateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
                    try {
                        java.util.Date date = format.parse(string);
                        System.out.println(date);
                    } catch (ParseException ex) {} */
                    
                    
               //     int resenje = zadatak.radi();
                    
                    Thread.sleep(100);
                    
                    ObjectMessage objectMessage = context.createObjectMessage();
                    
                    Query query = em.createQuery("SELECT p FROM Pesma as p WHERE p.naziv = :name");
                    query.setParameter("name", imePesme);
                    
                    if(query.getResultList().isEmpty()){
                        em.getTransaction().begin();
                        Pesma pesma = new Pesma();
                        pesma.setNaziv(imePesme);
                        em.persist(pesma);
                        em.getTransaction().commit();
                    }
                     
                    query = em.createQuery("SELECT p FROM Pesma as p WHERE p.naziv = :name");
                    query.setParameter("name", imePesme);
                    
                    Pesma rezultat = (Pesma) query.getResultList().get( query.getResultList().size()-1 );
                    
                    em.getTransaction().begin();
                    Slusao slusao = new Slusao();
                    slusao.setIdP(rezultat);
                    slusao.setIdK(korisnickiId);
                    em.persist(slusao);
                    em.getTransaction().commit();
                    
                    
                    objectMessage.setObject(rezultat);
                    objectMessage.setIntProperty("id", 2);
             //       objectMessage.setStringProperty("poslao", "MuzickiUredjaj");
             //       textMessage2.setStringProperty("imeZadatka", imeZadatka);
                    producer.send(topic, objectMessage);
                    System.out.println("Poslat je primarni kljuc za pesmu '" + imePesme + "' sa vrednoscu " +rezultat.getPesmaId());
                } catch (JMSException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
                                        
        }
      //  em.close();
     //   emf.close();
        
        
    }
    
     public static void setKorisnik(String novi){
        Korisnik broj;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MuzickiUredjajPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT k FROM Korisnik as k WHERE k.ime = :name");
        query.setParameter("name", novi);
        if (query.getResultList().isEmpty())
        {
            Korisnik noviKorisnik = new Korisnik();
            noviKorisnik.setIme(novi);
            em.getTransaction().begin();
            em.persist(noviKorisnik);
            em.getTransaction().commit();
            
            query = em.createQuery("SELECT k FROM Korisnik as k WHERE k.ime = :name");
            query.setParameter("name", novi);
            
        }
        broj = (Korisnik)query.getResultList().get(0);
        System.out.println("Korisnicki id je: "+broj.getKorisnikId()+" a ime je: "+broj.getIme());
        korisnickiId = broj;
        em.close();
        emf.close();
        
    }
     public static Collection<Pesma> pretraga(String korisnik){
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("MuzickiUredjajPU");
         EntityManager em = emf.createEntityManager();
         Query query = em.createQuery("SELECT p FROM Pesma as p, Slusao as s, Korisnik as k WHERE p.pesmaId = s.idP AND k.korisnikId = s.idK AND k.ime = :name");
         query.setParameter("name", korisnik);
         Collection<Pesma> rezultat = query.getResultList();
         System.out.println("Korisnik "+korisnik+" je slusao sledece pesme:");
         for(Pesma p: rezultat){
             System.out.println(p.getNaziv());
         }
         em.close();
         emf.close();
         return rezultat;
     }
     public static void reprodukcija(String naziv){
         try {
                    Runtime.getRuntime().exec("java -jar youtube.jar "+naziv);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }      
     }
    
}
