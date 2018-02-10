<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pregunta_Respuesta</title>
    </head>
    <body>
    <center><h1>Agregar Nueva Pregunta_Respuesta</h1></center>
    <form name="frmRegistrar" action="ServletPregunta_Respuesta">
        <table>
            <tr>
                <td>IdPregunta_Respuesta</td>
                <td><input type="text" name="txtidpre_res"></td>
            </tr>
            <tr>
                <td>Respuesta</td>
                <td><input type="text" name="txtrpta"></td>
            </tr>
            <tr>
                <td>IdEncuenta_Resultado</td>
                <td><input type="text" name="txtidenc_res"></td>
            </tr>
            <tr>
                <td>IdPregunta</td>
                <td><input type="text" name="txtidPreg"></td>
            </tr> 
            <tr>
                <td colspan="2">
                    <input type="submit" name="btngrabar" value="Grabar">
                </td>             
                
            </tr>


        </table>

    </form>
</body>
</html>
