package org.springframework.test.context.junit4;

import java.sql.SQLException;

import javax.naming.NamingException;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.indra.util.jackson.HibernateAwareObjectMapper;
import com.indra.web.listener.WebServletContextListener;

public class JUnit4Test extends AbstractJUnit4Test implements ApplicationContextAware {

    private static final Logger log = Logger.getLogger(JUnit4Test.class);
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        WebServletContextListener.setApplicationContext(applicationContext);
    }

    @Before
    public void init() {
        // setup();
        LOG = Logger.getLogger(AbstractJUnit4Test.class);
    }
    
    @BeforeClass
    public static void beforeSetup() {
        try {
            SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
            
            OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
            ocpds.setURL("jdbc:oracle:thin:@192.168.97.6:1521:xe");
            // ocpds.setURL("jdbc:oracle:thin:@192.168.97.7:1521:devora12");
            ocpds.setUser("APP_BALSEC");
            ocpds.setPassword("APP_BALSEC");
            
            builder.bind("jdbc/APP_BALSEC", ocpds);
            builder.activate();
        } catch(NamingException ex) {
            log.error("", ex);
        } catch (SQLException ex) {
            log.error("", ex);
        }
    }
    
    protected void prettyPrinterLazyEvit(Object object) {
        String json = "";
        HibernateAwareObjectMapper mapper = new HibernateAwareObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        ObjectWriter writer = mapper.writer();
        
        try {
            json = writer.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOG.error("", e);
        }
        
        LOG.info("\n" + json);
    }
}
