package pe.edu.upn.encuesta.controller;

import pe.edu.upn.encuesta.util.JsfUtil;
import pe.edu.upn.encuesta.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pe.edu.upn.encuesta.domain.PreguntaRespuesta;
import pe.edu.upn.encuesta.facade.PreguntaRespuestaFacade;

@Named("preguntaRespuestaController")
@SessionScoped
public class PreguntaRespuestaController implements Serializable {

    @EJB
    private pe.edu.upn.encuesta.facade.PreguntaRespuestaFacade ejbFacade;
    private List<PreguntaRespuesta> items = null;
    private PreguntaRespuesta selected;

    public PreguntaRespuestaController() {
    }

    public PreguntaRespuesta getSelected() {
        return selected;
    }

    public void setSelected(PreguntaRespuesta selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PreguntaRespuestaFacade getFacade() {
        return ejbFacade;
    }

    public PreguntaRespuesta prepareCreate() {
        selected = new PreguntaRespuesta();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PreguntaRespuestaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PreguntaRespuestaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PreguntaRespuestaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PreguntaRespuesta> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public PreguntaRespuesta getPreguntaRespuesta(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PreguntaRespuesta> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PreguntaRespuesta> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PreguntaRespuesta.class)
    public static class PreguntaRespuestaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PreguntaRespuestaController controller = (PreguntaRespuestaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "preguntaRespuestaController");
            return controller.getPreguntaRespuesta(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PreguntaRespuesta) {
                PreguntaRespuesta o = (PreguntaRespuesta) object;
                return getStringKey(o.getIdPreguntaRespuesta());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PreguntaRespuesta.class.getName()});
                return null;
            }
        }

    }

}
