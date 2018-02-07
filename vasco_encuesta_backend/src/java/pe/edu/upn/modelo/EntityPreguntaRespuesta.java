package pe.edu.upn.modelo;

public class EntityPreguntaRespuesta {
    //atributos
    private int IdPre_Res;
    private String Rpta;
    private int IdEnc_Res;
    private int IdPre;
   
    //metodos Get and Set
    public int getIdPre_Res() {
        return IdPre_Res;
    }

    public void setIdPre_Res(int IdPre_Res) {
        this.IdPre_Res = IdPre_Res;
    }

    public String getRpta() {
        return Rpta;
    }

    public void setRpta(String Rpta) {
        this.Rpta = Rpta;
    }

    public int getIdEnc_Res() {
        return IdEnc_Res;
    }

    public void setIdEnc_Res(int IdEnc_Res) {
        this.IdEnc_Res = IdEnc_Res;
    }

    public int getIdPre() {
        return IdPre;
    }

    public void setIdPre(int IdPre) {
        this.IdPre = IdPre;
    }


    

    public void getIdPre_Res(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getRpta(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getIdEnc_Res(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getIdPre(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
