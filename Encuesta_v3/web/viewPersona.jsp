<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Encuesta - Corporación Vasco - Persona</title>
    </head>
    <body>
    <center><h1>Registro de Personas</h1></center>
    <form name="frmRegistrar" action="ServletPersona" method="post">
        <table>
            <tr>
                <td>Nombre</td>
                <td><input type="text" name="txtnom" required></td>
            </tr>
            <tr>
                <td>Apellido Paterno</td>
                <td><input type="text" name="txtapePat" required></td>
            </tr>
            <tr>
                <td>Apellido Materno</td>
                <td><input type="text" name="txtapeMat" required></td>
            </tr>
            <tr>
                <td>DNI</td>
                <td><input type="text" name="txtdni" required minlength="8" maxlength="8"></td>
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
                    <a href="index.html"><input type="button" value="Cancelar"></a>
                </td>             
            </tr>
        </table>
    </form>
</body>
</html>
