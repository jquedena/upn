<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Encuesta - Corporación Vasco - Persona</title>
    </head>
    <body>
    <center><h1>Registro de Personas</h1></center>
    <form name="frmRegistrar" action="ServletPersona">
        <table>
            <tr>
                <td>Codigo</td>
                <td><input type="text" name="txtcod"></td>
            </tr>
            <tr>
                <td>Nombre</td>
                <td><input type="text" name="txtnom"></td>
            </tr>
            <tr>
                <td>Apellidos</td>
                <td><input type="text" name="txtape"></td>
            </tr>
            <tr>
                <td>DNI</td>
                <td><input type="text" name="txtdni"></td>
            </tr>
            <tr>
                <td>Télefono</td>
                <td><input type="text" name="txttel"></td>
            </tr>
            <tr>
                <td>Dirección</td>
                <td><input type="text" name="txtcor"></td>
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
