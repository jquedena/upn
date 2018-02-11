<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Encuesta - Corporación Vasco - Encuesta</title>
    </head>
    <body>
    <center><h1>Registro de Encuestas</h1></center>
    <form name="frmRegistrar" action="ServletPregunta_Respuesta" method="post">
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
