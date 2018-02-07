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
                <td>IdTipo_Control</td>
                <td><input type="text" name="txtidTip_Con"></td>
            </tr>
            <tr>
                <td>Descripcion</td>
                <td><input type="text" name="txtdescrip"></td>
            </tr>
            

        </table>

    </form>
</body>
</html>
