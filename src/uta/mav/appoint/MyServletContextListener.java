/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.mav.appoint;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author oguni
 */
@WebListener
public class MyServletContextListener implements ServletContextListener{
    private ScheduledExecutorService sched;
    
    
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        sched = Executors.newSingleThreadScheduledExecutor();
        sched.scheduleAtFixedRate(new TextMessageNotificationTask(), 0, 300, TimeUnit.SECONDS);
        sched.scheduleAtFixedRate(new CheckWaitListTask(), 0, 300, TimeUnit.SECONDS);
    }
 
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
         sched.shutdown();
    }
}
