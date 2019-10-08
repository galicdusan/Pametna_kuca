/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alarm;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entiteti.Alarm;
import entiteti.Pesma;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.Calendar;

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
    
    private static String nazivPesme = "Toma Zdravkovic - Prokleta nedelja";
    private static Alarm alarm;
    
    private static class MyTimeTask extends TimerTask
{

    public void run()
    {
        try {
            JMSContext context = connectionFactory.createContext();
            context.setClientID("Alarm1");
            JMSProducer producer = context.createProducer();
            TextMessage textMessage = context.createTextMessage();
            textMessage.setText(nazivPesme);
            textMessage.setIntProperty("id", 1);
            producer.send(topic, textMessage);
            System.out.println("Poslat je zahtev za pesmu sa nazivom: "+nazivPesme);
            context.close();
        } catch (JMSException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AlarmPU");
        EntityManager em = emf.createEntityManager();
        System.out.println("Startovan alarm ");
        
        JMSContext context = connectionFactory.createContext();
        context.setClientID("Alarm");
        JMSConsumer consumer = context.createDurableConsumer(topic, "Alarm", "id = 2", false);
        JMSProducer producer = context.createProducer();
        Timer timer = new Timer();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        alarm = new Alarm();
        
        while(true){
         //   int sekunde = (int)(Math.random() * 6 + 5);
         
         ArrayList<java.util.Date> ponudjeno = ponudjenaVremena();
         for (java.util.Date d: ponudjeno){
             System.out.println(d);
         }
        /* String vreme1 = "2019-02-12 16:25:00";
                    long perioda1 = 0;
       
                    try {
                        java.util.Date date = format.parse(vreme1);
                        System.out.println(date);   
                        
                        alarm.setVremeAktivacije(date);
                        alarm.setPerioda((int)perioda1);
                        
                        if (perioda1 == 0){
                            timer.schedule(new MyTimeTask(), date); 
                        }
                        else{
                            timer.schedule(new MyTimeTask(), date, perioda1);
                        }                      
                    } catch (ParseException ex) {} */
         
         
            Message message = consumer.receive();
            
            if(message instanceof TextMessage){
                try {
                //    ObjectMessage objectMessage = (ObjectMessage) message;
                //    Zadatak zadatak = (Zadatak) objectMessage.getObject();
                //    String imeZadatka = objectMessage.getStringProperty("imeZadatka");
                //    int resenje = zadatak.radi();
                TextMessage textMessage = (TextMessage) message;
              
                    String vreme = textMessage.getText();
                    long perioda = textMessage.getLongProperty("perioda");
       
                    try {
                        java.util.Date date = format.parse(vreme);
                        System.out.println(date);   
                        
                        alarm.setVremeAktivacije(date);
                        alarm.setPerioda((int)perioda);
                        
                        if (perioda == 0){
                            timer.schedule(new MyTimeTask(), date); 
                        }
                        else{
                            timer.schedule(new MyTimeTask(), date, perioda);
                        }                      
                    } catch (ParseException ex) {}
                
                                
                    Thread.sleep(100);
                    
          /*          TextMessage textMessage2 = context.createTextMessage();
                    textMessage2.setText("Resenje: " + resenje);
                    textMessage2.setIntProperty("idR", idR);
                    textMessage2.setStringProperty("imeZadatka", imeZadatka);
                    producer.send(queue, textMessage2);
                    System.out.println("Poslato je resenje za zadatak '" + imeZadatka + "' sa vrednoscu " + resenje); */
                } catch (JMSException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (message instanceof ObjectMessage){
                try {
                    ObjectMessage objectMessage = (ObjectMessage)message;
                    Pesma pesma = (Pesma)objectMessage.getObject();
                    alarm.setIdP(pesma);
                    
                    Query query = em.createQuery("SELECT a FROM Alarm as a WHERE a.vremeAktivacije = :datum AND a.idP = :pesma");
                    query.setParameter("datum",alarm.getVremeAktivacije());
                    query.setParameter("pesma", alarm.getIdP());
                    
                    if(query.getResultList().isEmpty()){
                        em.getTransaction().begin();
                        em.persist(alarm);
                        em.getTransaction().commit();
                    }
                    query = em.createQuery("SELECT a FROM Alarm as a WHERE a.vremeAktivacije = :datum AND a.idP = :pesma");
                    query.setParameter("datum",alarm.getVremeAktivacije());
                    query.setParameter("pesma", alarm.getIdP());
                    
                    Alarm rezultat = (Alarm)query.getResultList().get(0);
                    ObjectMessage objectMessage2 = context.createObjectMessage();
                    objectMessage2.setObject(rezultat);
                    objectMessage2.setIntProperty("id", 3);
                    producer.send(topic, objectMessage2);
                    System.out.println("Poslat je alarm sa pocetkom u '" + rezultat.getVremeAktivacije() + "' i nazivom pesme " + rezultat.getIdP().getNaziv());
                    
                    
                    alarm = new Alarm();
                    
                    
                } catch (JMSException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
        }
        
    }
    public static void zadajNazivPesme(String imePesme){
        nazivPesme = imePesme;
    }
    public static void navijAlarm(String vreme){
        try {
            Timer timer = new Timer();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            java.util.Date date = format.parse(vreme);
            alarm.setVremeAktivacije(date);
            alarm.setPerioda(0);
            timer.schedule(new MyTimeTask(), date);
            
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void navijPeriodicniAlarm(String vreme, long perioda){
        try {
            Timer timer = new Timer();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            java.util.Date date = format.parse(vreme);
            alarm.setVremeAktivacije(date);
            alarm.setPerioda((int)perioda);
            timer.schedule(new MyTimeTask(), date, perioda);
            
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ArrayList<java.util.Date> ponudjenaVremena(){
        ArrayList<java.util.Date> rezultat = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        rezultat.add( cal.getTime() );
        cal.add(Calendar.MINUTE, 30);
        rezultat.add( cal.getTime() );
        cal.add(Calendar.MINUTE, 30);
        rezultat.add( cal.getTime() );
        cal.add(Calendar.MINUTE, 30);
        rezultat.add( cal.getTime() );
        return rezultat;
    }
    
}
