package com.bbva.seguridad.bean;

import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pe.edu.upn.encuesta.entidad.Modulo;
import pe.edu.upn.encuesta.entidad.RolOpcion;

/**
 * 
 * @author JUAN
 *
 */
public class MenuUtil {

    private MenuUtil() {
    }
    
    public static String dibujarMenu(List<RolOpcion> opciones) {
        StringBuilder menuHTML = new StringBuilder();

        Map<Integer, Modulo> menus = new LinkedHashMap<Integer, Modulo>();
        Map<Integer, Modulo> submenus = new LinkedHashMap<Integer, Modulo>();

        for (RolOpcion opcion : opciones) {
            Modulo modulo = opcion.getModulo();
            if (modulo.getIdModulo() != null) {
                if (menus.get(modulo.getModulo().getId().intValue()) == null) {
                    menus.put(modulo.getModulo().getId().intValue(), modulo.getModulo());
                }
                // Si no se agrego previamente lo agrego
                if (submenus.get(modulo.getId().intValue()) == null) {
                    submenus.put(modulo.getId().intValue(), modulo);
                }
            }
        }

        int i = 1;
        Iterator<Entry<Integer, Modulo>> itMenu = menus.entrySet().iterator();
        
        
        menuHTML.append("<nav class=\"navbar navbar-default\">");
        menuHTML.append("<div class=\"container-fluid\">");
        menuHTML.append("<ul class=\"nav navbar-nav\">");
        
        while (itMenu.hasNext()) {
            Entry<Integer, Modulo> e = itMenu.next();
            Modulo menu = e.getValue();
            
            
//            <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
//            <li><a href="#">Link</a></li>
//            
//              
//              <ul class="dropdown-menu">
//                <li><a href="#">Action</a></li>
//                <li><a href="#">Another action</a></li>
//                <li><a href="#">Something else here</a></li>
//                <li role="separator" class="divider"></li>
//                <li><a href="#">Separated link</a></li>
//                <li role="separator" class="divider"></li>
//                <li><a href="#">One more separated link</a></li>
//              </ul>
//            </li>
//          </ul>
            
            
            
            
            menuHTML.append("<li class=\"dropdown\">");
            menuHTML.append("<a href=\"javascript:void(0)\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">");
            menuHTML.append("<div class=\"title title-ico title-ico-" + i + "\">" + reemplazaTildes(menu.getNombre()) + "</div>");
            menuHTML.append("</a>");
            menuHTML.append("<ul class=\"dropdown-menu\">");
            Iterator<Entry<Integer, Modulo>> itSubMenu = submenus.entrySet().iterator();
            while (itSubMenu.hasNext()) {
                Entry<Integer, Modulo> e1 = itSubMenu.next();
                Modulo subMenu = e1.getValue();
                if (subMenu.getIdModulo() == menu.getId()) {
                    menuHTML.append("<li id=\"submenu" + subMenu.getId() + "\">");
                    menuHTML.append("<a class=\"menuEvent\" id=\"href_submenu" + subMenu.getId() + "\" href=\"" + subMenu.getUrl() + "\">");
                    menuHTML.append(reemplazaTildes(subMenu.getNombre()));
                    menuHTML.append("</a>");
                    menuHTML.append("</li>");
                }
            }
            menuHTML.append("</ul>");
            
            i++;
        }

        menuHTML.append("</ul></div></nav>");
        return menuHTML.toString();
    }
    
    /**
     * 
     * @param opciones
     * @return
     */
    public static String dibujarMenuJQuery(List<RolOpcion> opciones) {
        StringBuilder menuHTML = new StringBuilder();

        Map<Integer, Modulo> menus = new LinkedHashMap<Integer, Modulo>();
        Map<Integer, Modulo> submenus = new LinkedHashMap<Integer, Modulo>();

        for (RolOpcion opcion : opciones) {
            Modulo modulo = opcion.getModulo();
            if (modulo.getIdModulo() != null) {
                if (menus.get(modulo.getModulo().getId().intValue()) == null) {
                    menus.put(modulo.getModulo().getId().intValue(), modulo.getModulo());
                }
                // Si no se agrego previamente lo agrego
                if (submenus.get(modulo.getId().intValue()) == null) {
                    submenus.put(modulo.getId().intValue(), modulo);
                }
            }
        }

        int i = 1;
        Iterator<Entry<Integer, Modulo>> itMenu = menus.entrySet().iterator();
        menuHTML.append("<table class=\"tblMenuL\"><tr>");
        while (itMenu.hasNext()) {
            Entry<Integer, Modulo> e = itMenu.next();
            Modulo menu = e.getValue();
            
            menuHTML.append("<td>");
            menuHTML.append("<a id=\"menu" + i + "\" class=\"fg-button " + (i == 1 ? "fg-button-ltr" : "") + "\" href=\"#\">");
            menuHTML.append("<div class=\"title title-ico title-ico-" + i + "\">" + reemplazaTildes(menu.getNombre()) + "</div>");
            menuHTML.append("</a>");
            menuHTML.append("<div class=\"hide\">");
            menuHTML.append("<ul>");
            Iterator<Entry<Integer, Modulo>> itSubMenu = submenus.entrySet().iterator();
            while (itSubMenu.hasNext()) {
                Entry<Integer, Modulo> e1 = itSubMenu.next();
                Modulo subMenu = e1.getValue();
                if (subMenu.getIdModulo() == menu.getId()) {
                	menuHTML.append("<li id=\"submenu" + subMenu.getId() + "\">");
                	menuHTML.append("<a class=\"fg-menu-link\" id=\"href_submenu" + subMenu.getId() + "\" href=\"" + subMenu.getUrl() + "\">");
                	menuHTML.append(reemplazaTildes(subMenu.getNombre()));
                	menuHTML.append("</a>");
                    menuHTML.append("</li>");
                }
            }
            menuHTML.append("</ul>");
            menuHTML.append("</div>");
            menuHTML.append("</td>");
            i++;
        }
        menuHTML.append("</tr></table>");
        return menuHTML.toString();
    }

    /**
     * 
     * @param cadena
     * @return
     */
    public static String reemplazaTildes(String cadena) {
        return cadena.replaceAll("\u00ED", "&iacute;")
                .replaceAll("\u00FA", "&uacute;")
                .replaceAll("\u00F3", "&oacute;")
                .replaceAll("\u00E9", "&eacute;")
                .replaceAll("\u00E1", "&aacute;")
                .replaceAll("\u00F1", "&ntilde;")
                .replaceAll("\u00CD", "&Iacute;")
                .replaceAll("\u00DA", "&Uacute;")
                .replaceAll("\u00D3", "&Oacute;")
                .replaceAll("\u00C9", "&Eacute;")
                .replaceAll("\u00C1", "&Aacute;")
                .replaceAll("\u00D1", "&Ntilde;");
    }

}